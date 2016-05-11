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
import java.util.List;

import org.scenarioo.api.files.ObjectFromDirectory;
import org.scenarioo.api.files.ScenarioDocuFiles;
import org.scenarioo.model.docu.entities.Branch;
import org.scenarioo.model.docu.entities.Build;
import org.scenarioo.model.docu.entities.Scenario;
import org.scenarioo.model.docu.entities.Step;
import org.scenarioo.model.docu.entities.UseCase;
import org.scenarioo.writer.utils.JsonUtil;

/**
 * Gives access to the geenrated scenarioo docu files in the filesystem.
 */
public class ScenarioDocuReader {
	
	
	private final ScenarioDocuFiles docuFiles;
	
	public ScenarioDocuReader(final File rootDirectory) {
		this.docuFiles = new ScenarioDocuFiles(rootDirectory);
	}
	
	public Branch loadBranch(final String branchId) {
		File file = docuFiles.getBranchFile(branchId);
		return JsonUtil.load(Branch.class, file);
	}
	
	public List<Branch> loadBranches() {
		List<File> branchFiles = docuFiles.getBranchFiles();
		return JsonUtil.loadListOfFiles(branchFiles, Branch.class);
	}
	
	public Build loadBuild(final String branchId, final String buildId) {
		File file = docuFiles.getBuildFile(branchId, buildId);
		return JsonUtil.load(Build.class, file);
	}
	
	/**
	 * This method was changed in in Version 2.0 of the API to return a list of ObjectFromDirectory&lt;Build&gt; instead
	 * of a list of BuildLinks. This was done because BuildLinks belongs to the Server now and is not part of the API
	 * anymore.
	 */
	public List<ObjectFromDirectory<Build>> loadBuilds(final String branchId) {
		List<File> buildFiles = docuFiles.getBuildFiles(branchId);
		return JsonUtil.loadListOfFilesWithDirNames(buildFiles, Build.class);
	}
	
	public List<UseCase> loadUsecases(final String branchId, final String buildId) {
		List<File> files = docuFiles.getUseCaseFiles(branchId, buildId);
		return JsonUtil.loadListOfFiles(files, UseCase.class);
	}
	
	public UseCase loadUsecase(final String branchId, final String buildId, final String useCaseId) {
		File file = docuFiles.getUseCaseFile(branchId, buildId,
				useCaseId);
		return JsonUtil.load(UseCase.class, file);
	}
	
	public List<Scenario> loadScenarios(final String branchId, final String buildId, final String useCaseId) {
		List<File> files = docuFiles.getScenarioFiles(branchId, buildId,
				useCaseId);
		return JsonUtil.loadListOfFiles(files, Scenario.class);
	}
	
	public Scenario loadScenario(final String branchId, final String buildId, final String useCaseId,
			final String scenarioId) {
		File file = docuFiles.getScenarioFile(branchId, buildId,
				useCaseId, scenarioId);
		return JsonUtil.load(Scenario.class, file);
	}
	
	public List<Step> loadSteps(final String branchId, final String buildId, final String useCaseId,
			final String scenarioId) {
		List<File> files = docuFiles.getStepFiles(branchId, buildId,
				useCaseId, scenarioId);
		return JsonUtil.loadListOfFiles(files, Step.class);
	}
	
	public Step loadStep(final String branchId, final String buildId, final String useCaseId,
			final String scenarioId, final int stepIndex) {
		File file = docuFiles.getStepFile(branchId, buildId,
				useCaseId, scenarioId, stepIndex);
		return JsonUtil.load(Step.class, file);
	}
	
	/**
	 * Screenshot files are simply provided by path, the REST service will take care of streaming it.
	 */
	public File getScreenshotFile(final String branchId, final String buildId, final String useCaseId,
			final String scenarioId, final String imageName) {
		return new File(docuFiles.getScreenshotsDirectory(branchId, buildId,
				useCaseId, scenarioId), imageName);
	}
	
}
