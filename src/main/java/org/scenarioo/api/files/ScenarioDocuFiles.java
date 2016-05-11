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

package org.scenarioo.api.files;

import java.io.File;
import java.text.NumberFormat;
import java.util.List;

import org.scenarioo.api.util.files.FilesUtil;
import org.scenarioo.model.docu.format.IdentifierFormat;

/**
 * Represents the file structure of the documentation.
 */
public class ScenarioDocuFiles {

	private static final String DIRECTORY_NAME_STEPS = "steps";

	private static final String DIRECTORY_NAME_STEPS_HTML = "html";

	private static final String DIRECTORY_NAME_STEPS_SCREENSHOTS = "screenshots";
	
	private static final String FILE_NAME_SCENARIO = "scenario.json";
	
	private static final String FILE_NAME_CASE = "usecase.json";
	
	private static final String FILE_NAME_BUILD = "build.json";
	
	private static final String FILE_NAME_BRANCH = "branch.json";
	
	private static NumberFormat THREE_DIGIT_NUM_FORMAT = createNumberFormatWithMinimumIntegerDigits(3);
	
	private final File rootDirectory;
	
	public ScenarioDocuFiles(final File rootDirectory) {
		this.rootDirectory = rootDirectory;
	}
	
	public void assertRootDirectoryExists() {
		if (!rootDirectory.exists()) {
			throw new IllegalArgumentException("Directory for docu content generation does not exist: "
					+ rootDirectory.getAbsolutePath());
		}
	}
	
	public File getRootDirectory() {
		return rootDirectory;
	}
	
	public File getBranchDirectory(final String branchId) {
		File branchDirectory = new File(rootDirectory, IdentifierFormat.sanitize(branchId));
		return branchDirectory;
	}
	
	public File getBranchFile(final String branchId) {
		return new File(getBranchDirectory(branchId), FILE_NAME_BRANCH);
	}
	
	public List<File> getBranchFiles() {
		return FilesUtil.getListOfFilesFromSubdirs(rootDirectory, FILE_NAME_BRANCH);
	}
	
	public File getBuildDirectory(final String branchId, final String buildId) {
		File buildDirectory = new File(getBranchDirectory(branchId), IdentifierFormat.sanitize(buildId));
		return buildDirectory;
	}
	
	public File getBuildFile(final String branchId, final String buildId) {
		return new File(getBuildDirectory(branchId, buildId), FILE_NAME_BUILD);
	}
	
	public List<File> getBuildFiles(final String branchId) {
		return FilesUtil.getListOfFilesFromSubdirs(getBranchDirectory(branchId), FILE_NAME_BUILD);
	}
	
	public File getUseCaseDirectory(final String branchId, final String buildId, final String useCaseId) {
		File branchDirectory = new File(getBuildDirectory(branchId, buildId), IdentifierFormat.sanitize(useCaseId));
		return branchDirectory;
	}
	
	public File getUseCaseFile(final String branchId, final String buildId, final String useCaseId) {
		return new File(getUseCaseDirectory(branchId, buildId, useCaseId), FILE_NAME_CASE);
	}
	
	public List<File> getUseCaseFiles(final String branchId, final String buildId) {
		return FilesUtil.getListOfFilesFromSubdirs(getBuildDirectory(branchId, buildId), FILE_NAME_CASE);
	}
	
	public File getScenarioDirectory(final String branchId, final String buildId, final String useCaseId,
			final String scenarioId) {
		File branchDirectory = new File(getUseCaseDirectory(branchId, buildId, useCaseId),
				IdentifierFormat.sanitize(scenarioId));
		return branchDirectory;
	}
	
	public File getScenarioFile(final String branchId, final String buildId, final String useCaseId,
			final String scenarioId) {
		return new File(getScenarioDirectory(branchId, buildId, useCaseId, scenarioId), FILE_NAME_SCENARIO);
	}
	
	public List<File> getScenarioFiles(final String branchId, final String buildId, final String useCaseId) {
		return FilesUtil.getListOfFilesFromSubdirs(getUseCaseDirectory(branchId, buildId, useCaseId),
				FILE_NAME_SCENARIO);
	}
	
	public File getStepsDirectory(final String branchId, final String buildId, final String useCaseId,
			final String scenarioId) {
		File branchDirectory = new File(getScenarioDirectory(branchId, buildId, useCaseId, scenarioId),
				DIRECTORY_NAME_STEPS);
		return branchDirectory;
	}
	
	public File getStepFile(final String branchId, final String buildId, final String useCaseId,
			final String scenarioId, final int stepIndex) {
		return new File(getStepsDirectory(branchId, buildId, useCaseId, scenarioId),
				THREE_DIGIT_NUM_FORMAT.format(stepIndex) + ".xml");
	}
	
	public List<File> getStepFiles(final String branchId, final String buildId, final String useCaseId,
			final String scenarioId) {
		return FilesUtil.getListOfFiles(getStepsDirectory(branchId, buildId, useCaseId, scenarioId));
	}
	
	public File getScreenshotsDirectory(final String branchId, final String buildId,
			final String useCaseId, final String scenarioId) {
		return new File(getScenarioDirectory(branchId, buildId, useCaseId, scenarioId),
				DIRECTORY_NAME_STEPS_SCREENSHOTS);
	}

	public File getHtmlDirectory(final String branchId, final String buildId,
										final String useCaseId, final String scenarioId) {
		return new File(getScenarioDirectory(branchId, buildId, useCaseId, scenarioId),
				DIRECTORY_NAME_STEPS_HTML);
	}

	/**
	 * @param fileFormatEnding the file format postfix ending (usually `.png` or `.jpg`)
	 * @return A {@link File} object pointing to the image file of the step screenshot. The method does not care whether
	 *         the file actually exists. That is where you have to store your image file of the screenshot belonging to this step.
	 */
	public File getScreenshotFile(final String branchId, final String buildId,
			final String useCaseId, final String scenarioId, final int stepIndex, final String fileFormatEnding) {
		return new File(getScreenshotsDirectory(branchId, buildId, useCaseId, scenarioId),
				THREE_DIGIT_NUM_FORMAT.format(stepIndex) + fileFormatEnding);
	}

	public File getScreenshotFilePng(final String branchId, final String buildId,
								  final String useCaseId, final String scenarioId, final int stepIndex) {
		return getScreenshotFile(branchId, buildId, useCaseId, scenarioId, stepIndex, ".png");
	}

	public File getScreenshotFileJpg(final String branchId, final String buildId,
									 final String useCaseId, final String scenarioId, final int stepIndex) {
		return getScreenshotFile(branchId, buildId, useCaseId, scenarioId, stepIndex, ".jpeg");
	}

	public File getStepHtmlFile(final String branchId, final String buildId,
									 final String useCaseId, final String scenarioId, final int stepIndex) {
		return new File(getScreenshotsDirectory(branchId, buildId, useCaseId, scenarioId),
				THREE_DIGIT_NUM_FORMAT.format(stepIndex) + ".html");
	}

	private static NumberFormat createNumberFormatWithMinimumIntegerDigits(
			final int minimumIntegerDigits) {
		final NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		numberFormat.setMinimumIntegerDigits(minimumIntegerDigits);
		return numberFormat;
	}
	
}
