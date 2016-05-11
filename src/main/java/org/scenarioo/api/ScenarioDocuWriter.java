/* scenarioo-api
 * Copyright (C) 2014, scenarioo.org Development Team
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * As a special exception, the copyright holders of this library give you 
 * permission to link this library with independent modules, according 
 * to the GNU General Public License with "Classpath" exception as provided
 * in the LICENSE file that accompanied this code.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.scenarioo.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.scenarioo.api.configuration.ScenarioDocuGeneratorConfiguration;
import org.scenarioo.api.exception.ScenarioDocuSaveException;
import org.scenarioo.api.exception.ScenarioDocuTimeoutException;
import org.scenarioo.api.files.ScenarioDocuFiles;
import org.scenarioo.api.rules.Preconditions;
import org.scenarioo.model.docu.entities.Branch;
import org.scenarioo.model.docu.entities.Build;
import org.scenarioo.model.docu.entities.Scenario;
import org.scenarioo.model.docu.entities.Step;
import org.scenarioo.model.docu.entities.UseCase;
import org.scenarioo.model.docu.entities.base.ScenariooEntity;
import org.scenarioo.model.docu.format.IdentifierFormat;
import org.scenarioo.writer.utils.JsonUtil;

/**
 * Generator to produce documentation files for a specific build.
 * 
 * The writer performs all save operations as asynchronous writes, to not block the webtests that are typically calling
 * the save operations to save documentation content.
 * 
 * An instance of such a writer needs to be closed after last write operation by using the method {@link #flush()}.
 * After calling {@link #flush()} once the writer can not be used anymore.
 */
public class ScenarioDocuWriter {
	
	private final ScenarioDocuFiles docuFiles;
	
	private final String branchId;
	
	private final String buildId;
	
	private final ExecutorService asyncWriteExecutor = newAsyncWriteExecutor();
	
	private final List<RuntimeException> caughtExceptions = new ArrayList<RuntimeException>();
	
	/**
	 * Initialize with directory inside which to generate the documentation contents.
	 * 
	 * @param destinationRootDirectory
	 *            the directory where the content should be generated (this directory must be precreated by you!).
	 * @param branchId
	 *            id of the branch we are generating content for (if the id is not explicitly set, you can use the name for id)
	 * @param buildId
	 *            id of the branch we are generating content for (if the id is not explicitly set, you can use the name for id)
	 */
	public ScenarioDocuWriter(final File destinationRootDirectory, final String branchId, final String buildId) {
		docuFiles = new ScenarioDocuFiles(destinationRootDirectory);
		this.branchId = IdentifierFormat.sanitize(branchId);
		this.buildId = IdentifierFormat.sanitize(buildId);
		createBuildDirectoryIfNotYetExists();
	}

	private void generateMissingId(ScenariooEntity<?> entity, String defaultIdentifierOrName) {
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(IdentifierFormat.sanitize(defaultIdentifierOrName));
		}
	}
	
	/**
	 * Save the branch description to appropriate directory
	 * 
	 * @param branch
	 *            the branch description to write.
	 */
	public void saveBranchDescription(final Branch branch) {
		generateMissingId(branch, branchId);
		Preconditions.checkEquals(branch.getId(), branchId, "Id field on branch is '" + branch.getId() + "' but writer is for writing to branch directory of branch with id='" + branchId + "'.");
		executeAsyncWrite(new Runnable() {
			@Override
			public void run() {
				File branchFile = docuFiles.getBranchFile(branchId);
				JsonUtil.save(branch, branchFile);
			}
		});
	}
	
	/**
	 * Save the build description to appropriate directory
	 * 
	 * @param build
	 *            the build description to write
	 */
	public void saveBuildDescription(final Build build) {
		generateMissingId(build, buildId);
		Preconditions.checkEquals(build.getId(), buildId, "Id field on build is '" + build.getId() + "' but writer is for writing to build directory with id='" + buildId + "'.");
		executeAsyncWrite(new Runnable() {
			@Override
			public void run() {
				File buildFile = docuFiles.getBuildFile(branchId, buildId);
				JsonUtil.save(build, buildFile);
			}
		});
	}
	
	/**
	 * Save the use case description to appropriate directory and file
	 * 
	 * @param useCase
	 *            the use case description to write
	 */
	public void saveUseCase(final UseCase useCase) {
		generateMissingId(useCase, useCase.getName());
		executeAsyncWrite(new Runnable() {
			@Override
			public void run() {
				File useCaseFile = docuFiles.getUseCaseFile(branchId, buildId, useCase.getId());
				createParentDirectoryIfNotYetExists(useCaseFile);
				JsonUtil.save(useCase, useCaseFile);
			}
		});
	}
	
	public void saveScenario(final UseCase useCase, final Scenario scenario) {
		generateMissingId(useCase, useCase.getName());
		saveScenario(useCase.getId(), scenario);
	}
	
	public void saveScenario(final String useCaseId, final Scenario scenario) {
		generateMissingId(scenario, scenario.getName());
		executeAsyncWrite(new Runnable() {
			@Override
			public void run() {
				File scenarioFile = docuFiles.getScenarioFile(branchId, buildId, useCaseId, scenario.getId());
				createParentDirectoryIfNotYetExists(scenarioFile);
				JsonUtil.save(scenario, scenarioFile);
			}
		});
	}
	
	public void saveStep(final UseCase useCase, final Scenario scenario, final Step step) {
		generateMissingId(useCase, useCase.getName());
		generateMissingId(scenario, scenario.getName());
		saveStep(useCase.getId(), scenario.getId(), step);
	}
	
	/**
	 * The page property of the step is optional, but it is recommended to use it.
	 * Page names are a central part of Scenarioo.
	 */
	public void saveStep(final String useCaseId, final String scenarioId, final Step step) {
		executeAsyncWrite(new Runnable() {
			@Override
			public void run() {
				File stepFile = docuFiles.getStepFile(branchId, buildId, useCaseId, scenarioId, step.getIndex());
				createParentDirectoryIfNotYetExists(stepFile);
				JsonUtil.save(step, stepFile);
			}
		});
	}

	/**
	 * In case you want to define your screenshot names differently than by step name, you can save it on your own, into
	 * the following directory for a scenario.
	 */
	public File getScreenshotsDirectory(final String useCaseId, final String scenarioId) {
		return docuFiles.getScreenshotsDirectory(branchId, buildId, useCaseId, scenarioId);
	}
	
	/**
	 * Get the file name of the file where the screenshot of a step is stored (for any supported image format, pass the correct ending)
	 */
	public File getScreenshotFile(final String useCaseId, final String scenarioId, final int stepIndex, final String fileFormatEnding) {
		return docuFiles.getScreenshotFile(branchId, buildId, useCaseId, scenarioId, stepIndex, fileFormatEnding);
	}

	/**
	 * Get the file name of the file where the screenshot of a step is stored as Png file.
	 */
	public File getScreenshotFilePng(final String useCaseId, final String scenarioId, final int stepIndex) {
		return docuFiles.getScreenshotFile(branchId, buildId, useCaseId, scenarioId, stepIndex, ".png");
	}
	
	/**
	 * Save the provided PNG image as a PNG file into the correct default file location for a step.
	 * 
	 * In case you want to use another image format (e.g. JPEG) or just want to define the image file names for your
	 * scenarios differently, you can do this by using one of the getScreenshotFile methods and save the file on your own to the provided path.
	 * 
	 * @param pngScreenshot
	 *            Screenshot in PNG format.
	 */
	public void saveScreenshotAsPng(final String useCaseId, final String scenarioId, final int stepIndex, final byte[] pngScreenshot) {
		executeAsyncWrite(new Runnable() {
			@Override
			public void run() {
			final File screenshotFile = docuFiles.getScreenshotFilePng(branchId, buildId, useCaseId, scenarioId, stepIndex);
			try {
				FileUtils.writeByteArrayToFile(screenshotFile, pngScreenshot);
			} catch (IOException e) {
				throw new RuntimeException("Could not write image: " + screenshotFile.getAbsolutePath(), e);
			}
			}
		});
	}

	/**
	 * Save the html source of a step into a separate file for a step
     */
	public void saveStepHtml(final String useCaseId, final String scenarioId, final int stepIndex, final String htmlSource) {
		executeAsyncWrite(new Runnable() {
			@Override
			public void run() {
				final File htmlFile = docuFiles.getStepHtmlFile(branchId, buildId, useCaseId, scenarioId, stepIndex);
				try {
					FileUtils.writeStringToFile(htmlFile, htmlSource);
				} catch (IOException e) {
					throw new RuntimeException("Could not write html for step: " + htmlFile.getAbsolutePath(), e);
				}
			}
		});
	}
	
	/**
	 * Finish asynchronous writing of all saved files. This has to be called in the end, to ensure all data saved in
	 * this generator is written to the filesystem.
	 * 
	 * Will block until writing has finished or timeout occurs.
	 * 
	 * @throws ScenarioDocuSaveException
	 *             if any of the save commands throwed an exception during asynchronous execution.
	 * @throws ScenarioDocuTimeoutException
	 *             if waiting for the saving beeing finished exceeds the configured timeout
	 */
	public void flush() {
		int timeoutInSeconds = ScenarioDocuGeneratorConfiguration.INSTANCE
				.getTimeoutWaitingForWritingFinishedInSeconds();
		asyncWriteExecutor.shutdown();
		try {
			boolean terminated = asyncWriteExecutor.awaitTermination(timeoutInSeconds, TimeUnit.SECONDS);
			if (!terminated) {
				asyncWriteExecutor.shutdownNow();
				throw new ScenarioDocuTimeoutException(
						"Timeout occured while waiting for docu files to be written. Writing of files took too long.");
			}
		} catch (InterruptedException e) {
			throw new RuntimeException("Async writing of scenarioo docu files was interrupted", e);
		}
		if (!caughtExceptions.isEmpty()) {
			throw new ScenarioDocuSaveException(caughtExceptions);
		}
	}
	
	private File getBuildDirectory() {
		return docuFiles.getBuildDirectory(branchId, buildId);
	}
	
	private File getUseCaseDirectory(final String useCaseName) {
		return docuFiles.getUseCaseDirectory(branchId, buildId, useCaseName);
	}
	
	private File getScenarioDirectory(final String useCaseName, final String scenarioName) {
		return docuFiles.getScenarioDirectory(branchId, buildId, useCaseName, scenarioName);
	}
	
	private File getScenarioStepsDirectory(final String useCaseName, final String scenarioName) {
		return docuFiles.getStepsDirectory(branchId, buildId, useCaseName, scenarioName);
	}
	
	private void createBuildDirectoryIfNotYetExists() {
		createDirectoryIfNotYetExists(getBuildDirectory());
	}
	
	private void createParentDirectoryIfNotYetExists(final File file) {
		docuFiles.assertRootDirectoryExists();
		File directory = file.getParentFile();
		createDirectoryIfNotYetExists(directory);
	}

	private void createDirectoryIfNotYetExists(File directory) {
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}
	
	private void executeAsyncWrite(final Runnable writeTask) {
		asyncWriteExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					writeTask.run();
				} catch (RuntimeException e) {
					caughtExceptions.add(e);
				}
			}
		});
	}
	
	/**
	 * Creates an executor that queues the passed tasks for execution by one single additional thread. The excutor will
	 * start to block further executions as soon as more than the configured write tasks are waiting for execution.
	 */
	private static ExecutorService newAsyncWriteExecutor() {
		return new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(
				ScenarioDocuGeneratorConfiguration.INSTANCE.getAsyncWriteBufferSize()));
	}
	
}
