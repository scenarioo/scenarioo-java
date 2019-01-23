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

import static org.junit.jupiter.api.Assertions.*;
import static org.scenarioo.api.TestConstants.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.scenarioo.api.files.ScenarioDocuFiles;
import org.scenarioo.model.docu.entities.Branch;
import org.scenarioo.model.docu.entities.Build;
import org.scenarioo.model.docu.entities.Labels;
import org.scenarioo.model.docu.entities.Page;
import org.scenarioo.model.docu.entities.Scenario;
import org.scenarioo.model.docu.entities.Step;
import org.scenarioo.model.docu.entities.StepDescription;
import org.scenarioo.model.docu.entities.StepHtml;
import org.scenarioo.model.docu.entities.StepMetadata;
import org.scenarioo.model.docu.entities.UseCase;
import org.scenarioo.model.docu.entities.generic.Details;
import org.scenarioo.model.docu.entities.generic.ObjectDescription;
import org.scenarioo.model.docu.entities.generic.ObjectList;
import org.scenarioo.model.docu.entities.generic.ObjectReference;
import org.scenarioo.model.docu.entities.screenAnnotations.ScreenAnnotation;
import org.scenarioo.model.docu.entities.screenAnnotations.ScreenAnnotationClickAction;
import org.scenarioo.model.docu.entities.screenAnnotations.ScreenAnnotationStyle;

/**
 * Some smoke tests for the Scenarioo generator API.
 */
class ScenarioDocuWriterAndReaderTest {
	
	private static final String TEST_DETAILS_VERSION_KEY = "version";
	
	/**
	 * Objects under test
	 */
	private ScenarioDocuWriter writer;
	private ScenarioDocuReader reader;
	
	/**
	 * docuFiles is used for checking some of the written files.
	 */
	private ScenarioDocuFiles docuFiles;
	
	@BeforeEach
	void setUp() {
		TEST_ROOT_DIRECTORY.mkdirs();
		writer = new ScenarioDocuWriter(TEST_ROOT_DIRECTORY, TEST_BRANCH_NAME, TEST_BUILD_NAME);
		reader = new ScenarioDocuReader(TEST_ROOT_DIRECTORY);
		docuFiles = new ScenarioDocuFiles(TEST_ROOT_DIRECTORY);
	}
	
	@AfterAll
	static void tearDown() {
		deleteDirectory(TEST_ROOT_DIRECTORY);
	}
	
	private static void deleteDirectory(final File testRootDirectory) {
		try {
			FileUtils.deleteDirectory(testRootDirectory);
		} catch (IOException e) {
			throw new RuntimeException("Could not delete test data directory", e);
		}
	}
	
	@Test
	void write_and_read_branch_description() {
		
		// GIVEN: a typical branch
		Branch branch = new Branch();
		branch.setName(TEST_BRANCH_NAME);
		branch.setDescription("just a simple development branch, might as well be the trunk.");
		
		// WHEN: the branch was saved.
		writer.saveBranchDescription(branch);
		writer.flush();
		
		// THEN: the branch can be loaded successfully and correctly
		Branch branchFromFile = reader.loadBranch(TEST_BRANCH_NAME);
		assertEquals(TEST_BRANCH_NAME, branchFromFile.getName(), "expected branch name");
		assertEquals(branch.getDescription(), branchFromFile.getDescription(), "expected branch description");
		
	}
	
	@Test
	void write_and_read_build_description() {
		
		// GIVEN: a typical build
		Build build = new Build();
		build.setName(TEST_BUILD_NAME);
		build.setDate(new Date());
		build.setRevision("10123");
		build.setStatus("success");
		build.getDetails().addDetail(TEST_DETAILS_VERSION_KEY, "1.0.1");
		
		// WHEN: the build was saved.
		writer.saveBuildDescription(build);
		writer.flush();
		
		// THEN: the build can be loaded successfully and correctly
		Build buildFromFile = reader.loadBuild(TEST_BRANCH_NAME, TEST_BUILD_NAME);
		assertEquals(TEST_BUILD_NAME, buildFromFile.getName(), "expected build name");
		assertEquals(build.getDate(), buildFromFile.getDate(), "expected date");
		assertEquals(build.getRevision(), buildFromFile.getRevision(), "expected revision");
		assertEquals(build.getStatus(), buildFromFile.getStatus(), "expected state");
		assertEquals(build.getDetails().getProperties(), buildFromFile.getDetails()
				.getProperties(), "expected details properties");
		
	}
	
	@Test
	void write_and_read_case_description() {
		
		// GIVEN: a typical use case
		UseCase usecase = new UseCase();
		usecase.setName(TEST_CASE_NAME);
		usecase.setDescription("this is a typical use case with a decription");
		usecase.setStatus("success");
		usecase.getDetails().addDetail("webtestName", "UseCaseWebTest");
		usecase.addLabel("label1").addLabel("label2");
		
		// WHEN: the usecase was saved.
		writer.saveUseCase(usecase);
		writer.flush();
		
		// THEN: the usecase can be loaded successfully and correctly
		UseCase usecaseFromFile = reader.loadUsecase(TEST_BRANCH_NAME, TEST_BUILD_NAME, TEST_CASE_NAME);
		assertEquals(TEST_CASE_NAME, usecaseFromFile.getName(), "expected case name");
		assertEquals(usecase.getDescription(), usecaseFromFile.getDescription(), "expected case desription");
		assertEquals(usecase.getStatus(), usecaseFromFile.getStatus(), "expected case state");
		assertEquals(usecase.getDetails().getProperties(), usecaseFromFile.getDetails()
				.getProperties(), "expected details properties");
		assertExpectedLabels(usecaseFromFile.getLabels(), "label1", "label2");
	}
	
	@Test
	void write_and_read_scenario_description() {
		
		// GIVEN: a typical scenario
		Scenario scenario = new Scenario();
		scenario.setName(TEST_SCENARIO_NAME);
		scenario.setDescription("this is a typical scenario with a decription");
		scenario.setStatus("success");
		scenario.getDetails().addDetail("userRole", "customer");
		scenario.addLabel("label1").addLabel("label2");
		
		// WHEN: the scenario was saved.
		writer.saveScenario(TEST_CASE_NAME, scenario);
		writer.flush();
		
		// THEN: the scenario can be loaded successfully and correctly
		Scenario scenarioFromFile = reader.loadScenario(TEST_BRANCH_NAME, TEST_BUILD_NAME, TEST_CASE_NAME,
				TEST_SCENARIO_NAME);
		assertEquals(TEST_SCENARIO_NAME, scenarioFromFile.getName(), "expected scneario name");
		assertEquals(scenario.getDescription(), scenarioFromFile.getDescription(), "expected scenario desription");
		assertEquals(scenario.getStatus(), scenarioFromFile.getStatus(), "expected scenario state");
		assertEquals(scenario.getDetails().getProperties(), scenarioFromFile
				.getDetails().getProperties(), "expected scenario details properties");
		assertExpectedLabels(scenarioFromFile.getLabels(), "label1", "label2");
		
	}
	
	/**
	 * This test is to test that nothing is required to set somehow, that is not mandatory for saving and loading a step
	 * correctly.
	 */
	@Test
	void write_and_read_step_minimal() {

		// GIVEN: a typical step with only minimal required fields beeing set
		Step step = new Step();
		StepDescription stepDescription = new StepDescription();
		stepDescription.setIndex(TEST_STEP_INDEX);
		stepDescription.setScreenshotFileName("001.png");
		step.setStepDescription(stepDescription);

		// WHEN: the step was saved.
		writer.saveStep(TEST_CASE_NAME, TEST_SCENARIO_NAME, step);
		writer.flush();

		// THEN: the step can be loaded successfully and correctly
		Step stepFromFile = reader.loadStep(TEST_BRANCH_NAME, TEST_BUILD_NAME, TEST_CASE_NAME, TEST_SCENARIO_NAME,
				TEST_STEP_INDEX);
		assertEquals(TEST_STEP_INDEX, stepFromFile.getStepDescription().getIndex(), "expected step index");
		assertEquals("", stepFromFile.getStepDescription().getTitle(), "expected empty step title");
		assertEquals("", stepFromFile.getStepDescription().getStatus(), "expected no status");
		assertEquals(0, stepFromFile.getStepDescription().getDetails().getProperties().size(),
			"expected no step step description details properties");
		assertEquals(0, stepFromFile.getMetadata().getDetails().getProperties().size(),
			"expected no step metadata details properties");
		assertEquals(0, stepFromFile.getStepDescription().getLabels().size(), "expected no labels");
		assertNull(stepFromFile.getPage(), "expected no page");

	}

	/**
	 * This test tests also all optional fields of a step, that they can be set and are loaded again properly.
	 */
	@Test
	void write_and_read_step_with_major_optional_fields() {
		
		// GIVEN: a typical step with all major optional fields also beeing set
		Step step = new Step();
		StepDescription stepDescription = new StepDescription();
		stepDescription.setIndex(TEST_STEP_INDEX);
		stepDescription.setTitle("Test Step");
		stepDescription.setStatus("success");
		stepDescription.addDetail("key1", "value1").addDetail("key2", "value2");
		stepDescription.addLabel("label1").addLabel("label2");
		step.setStepDescription(stepDescription);
		step.setHtml(new StepHtml("<html>just some page text</html>"));
		Page page = new Page("customer_overview.jsp");
		page.addLabel("page-label1").addLabel("page-label2");
		step.setPage(page);
		StepMetadata stepMetadata = new StepMetadata();
		stepMetadata.setVisibleText("just some page text");
		stepMetadata.getDetails().addDetail("mockedServicesConfiguration", "dummy_config_xy.properties");
		step.setMetadata(stepMetadata);
		
		// WHEN: the step was saved.
		writer.saveStep(TEST_CASE_NAME, TEST_SCENARIO_NAME, step);
		writer.flush();
		
		// THEN: the step can be loaded successfully and correctly
		Step stepFromFile = reader.loadStep(TEST_BRANCH_NAME, TEST_BUILD_NAME, TEST_CASE_NAME, TEST_SCENARIO_NAME,
				TEST_STEP_INDEX);
		assertEquals(TEST_STEP_INDEX, stepFromFile.getStepDescription().getIndex(), "expected step name");
		assertEquals(step.getStepDescription().getTitle(), stepFromFile.getStepDescription().getTitle(),
			"expected step desription");
		assertEquals(step.getStepDescription().getStatus(), stepFromFile.getStepDescription().getStatus(),
			"expected step status");
		assertEquals(step.getStepDescription().getDetails().getProperties(),
			stepFromFile.getStepDescription().getDetails().getProperties(),
			"expected step description details properties");
		assertExpectedLabels(stepFromFile.getStepDescription().getLabels(), "label1", "label2");
		assertEquals(step.getHtml().getHtmlSource(), stepFromFile.getHtml().getHtmlSource(), "expected step html");
		Page pageFromFile = stepFromFile.getPage();
		assertEquals(step.getPage().getName(), pageFromFile.getName(), "expected step page name");
		assertExpectedLabels(pageFromFile.getLabels(), "page-label1", "page-label2");
		assertEquals("just some page text", stepFromFile.getMetadata().getVisibleText(), "expected step visible text");
		assertEquals(step.getMetadata().getDetails().getProperties(),
				stepFromFile.getMetadata().getDetails().getProperties(), "expected step metadata details properties");

	}

	/**
	 * Test adding screen annotations to a step (which is optional too)
	 */
	@Test
	void write_and_read_step_with_screen_annotations() {

		// GIVEN: a typical step with only minimal required fields beeing set and two screen annotations
		Step step = new Step();
		StepDescription stepDescription = new StepDescription();
		stepDescription.setIndex(TEST_STEP_INDEX);
		stepDescription.setScreenshotFileName("001.png");
		step.setStepDescription(stepDescription);
		step.addScreenAnnotation(createMinimalScreenAnnotation());
		step.addScreenAnnotation(createFullScreenAnnotation());

		// WHEN: the step was saved.
		writer.saveStep(TEST_CASE_NAME, TEST_SCENARIO_NAME, step);
		writer.flush();

		// THEN: the step can be loaded successfully and correctly
		Step stepFromFile = reader.loadStep(TEST_BRANCH_NAME, TEST_BUILD_NAME, TEST_CASE_NAME, TEST_SCENARIO_NAME,
				TEST_STEP_INDEX);
		assertEquals(2, stepFromFile.getScreenAnnotations().size(), "expected number of screen annotations");
		assertMinimalScreenAnnotation(stepFromFile.getScreenAnnotations().get(0));
		assertFullScreenAnnotation(stepFromFile.getScreenAnnotations().get(1));

	}

	/**
	 * Tests writing and reading of a scenario docu file containing some basic collections that need to be supported.
	 */
	@Test
	void write_and_read_generic_collections_in_details() {
		
		// GIVEN: any object containing collections in details
		Scenario scenario = new Scenario();
		scenario.setName(TEST_SCENARIO_NAME);
		scenario.setDescription("a scenario for testing collections in details");
		// List of Strings
		ObjectList<String> list = new ObjectList<String>();
		list.add("item1");
		list.add("item2");
		list.add("item3");
		scenario.getDetails().addDetail("list", list);
		// Details (further maps with key value pairs for structured objects)
		Details detailsMap = new Details();
		detailsMap.put("key1", "value1");
		detailsMap.put("key2", "value2");
		detailsMap.put("anyGenericObjectReference", new ObjectReference("serviceCall", "MainDB.getUsers"));
		detailsMap.put("anyGenericObject", new ObjectDescription("configuration",
				"my_dummy_mocks_configuration.properties"));
		scenario.getDetails().addDetail("map", detailsMap);
		
		// WHEN: the object was saved.
		writer.saveScenario(TEST_CASE_NAME, scenario);
		writer.flush();
		
		// THEN: the collections get loaded correctly again.
		Scenario scenarioFromFile = reader.loadScenario(TEST_BRANCH_NAME, TEST_BUILD_NAME, TEST_CASE_NAME,
				TEST_SCENARIO_NAME);
		assertEquals(list, scenarioFromFile.getDetails().getDetail("list"));
		assertEquals(detailsMap, scenarioFromFile.getDetails().getDetail("map"));
		assertEquals(scenario.getDetails(), scenarioFromFile.getDetails());
		
	}
	
	/**
	 * Test that the files are written asynchronously and that the flush method waits correctly for the last file to be
	 * written.
	 */
	@Test
	void async_write_of_multiple_files_and_flush() {
		
		// GIVEN: a lot of large steps to write, that have not yet been written
		Step[] steps = new Step[10];
		for (int index = 0; index < 10; index++) {
			steps[index] = createBigDataStepForLoadTestAsyncWriting(index + 1);
		}
		File expectedFileForStep10 = docuFiles.getStepFile(TEST_BRANCH_NAME, TEST_BUILD_NAME, TEST_CASE_NAME,
				TEST_SCENARIO_NAME, 10);
		assertFalse(expectedFileForStep10.exists());
		
		// WHEN: saving those steps,
		for (int index = 0; index < 10; index++) {
			writer.saveStep(TEST_CASE_NAME, TEST_SCENARIO_NAME, steps[index]);
		}
		
		// THEN: the files are not created directly but asynchronously. flush will wait until all save's are finished.
		assertFalse(expectedFileForStep10.exists());
		writer.flush();
		assertTrue(expectedFileForStep10.exists());
		
	}
	
	/**
	 * Create a screen annotation with only the required fields set.
	 */
	private ScreenAnnotation createMinimalScreenAnnotation() {
		return new ScreenAnnotation(100, 150, 90, 10);
	}

	private void assertMinimalScreenAnnotation(final ScreenAnnotation screenAnnotation) {
		assertEquals(100, screenAnnotation.getRegion().getX(), "Expected x coordinate");
		assertEquals(150, screenAnnotation.getRegion().getY(), "Expected y coordinate");
		assertEquals(90, screenAnnotation.getRegion().getWidth(), "Expected width");
		assertEquals(10, screenAnnotation.getRegion().getHeight(), "Expected height");
		assertEquals("", screenAnnotation.getScreenText(), "Expected no text");
		assertEquals("", screenAnnotation.getDescription(), "Expected no description");
		assertEquals(screenAnnotation.getStyle(), ScreenAnnotationStyle.DEFAULT, "Expected default style");
		assertNull(screenAnnotation.getClickAction(), "Expected no click action");
		assertNull(screenAnnotation.getClickActionUrl(), "Expected no click action url");
		assertNull(screenAnnotation.getClickActionText(), "Expected no click action text");
		assertEquals(0, screenAnnotation.getDetails().size(), "Expected no details");
	}

	/**
	 * Create a screen annotation with all the fields set.
	 */
	private ScreenAnnotation createFullScreenAnnotation() {
		ScreenAnnotation screenAnnotation = new ScreenAnnotation(200, 150, 90, 20);
		screenAnnotation.setScreenText("just a text");
		screenAnnotation.setTitle("title");
		screenAnnotation.setDescription("just a description");
		screenAnnotation.setStyle(ScreenAnnotationStyle.CLICK);
		screenAnnotation.setClickAction(ScreenAnnotationClickAction.TO_URL);
		screenAnnotation.setClickActionUrl("http://just-an-url.com");
		screenAnnotation.setClickActionText("just a click action text");
		screenAnnotation.addDetail("just a detail", "just a value");
		return screenAnnotation;
	}

	private void assertFullScreenAnnotation(final ScreenAnnotation screenAnnotation) {
		assertEquals(200, screenAnnotation.getRegion().getX(), "Expected x coordinate");
		assertEquals(150, screenAnnotation.getRegion().getY(), "Expected y coordinate");
		assertEquals(90, screenAnnotation.getRegion().getWidth(), "Expected width");
		assertEquals(20, screenAnnotation.getRegion().getHeight(), "Expected height");
		assertEquals("just a text", screenAnnotation.getScreenText(), "Expected screen text");
		assertEquals("title", screenAnnotation.getTitle(), "Expected title");
		assertEquals("just a description", screenAnnotation.getDescription(), "Expected description");
		assertEquals(ScreenAnnotationStyle.CLICK, screenAnnotation.getStyle(), "Expected style");
		assertEquals(ScreenAnnotationClickAction.TO_URL, screenAnnotation.getClickAction(), "Expected clickAction");
		assertEquals("http://just-an-url.com", screenAnnotation.getClickActionUrl(), "Expected clickActionUrl");
		assertEquals("just a click action text", screenAnnotation.getClickActionText(), "Expected clickActionText");
		assertEquals(1, screenAnnotation.getDetails().size(), "Expected details");
	}

	private Step createBigDataStepForLoadTestAsyncWriting(final int index) {
		
		Step step = new Step();
		
		// Description
		StepDescription stepDescription = new StepDescription();
		stepDescription.setIndex(index);
		stepDescription
				.setTitle("this is a step with a lot of data in it such that writing should take realy long for testing async writing");
		step.setStepDescription(stepDescription);
		
		// Metdata with a lot of details
		step.setMetadata(createBigMetadata());
		
		// Page
		step.setPage(new Page("test.jsp"));
		
		// HTML (lot of dummy data, just to generate big data for writing)
		step.setHtml(createBigHtml());
		
		return step;
	}
	
	/**
	 * Simply generate StepMetadata object with a lot of details to get a big step for load testing of writing.
	 */
	private StepMetadata createBigMetadata() {
		StepMetadata stepMetadata = new StepMetadata();
		for (int i = 0; i < 1000; i++) {
			stepMetadata.getDetails().addDetail("detail" + i,
					"just a detail to produce a lot of data that needs marshalling and writing.");
		}
		return stepMetadata;
	}
	
	/**
	 * Simply generate a big dummy html for load testing of writing.
	 */
	private StepHtml createBigHtml() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("<html><head></head><body>");
		builder.append("<p>This is just a dummy html code with lot of content to generate a lot of big data to write for load testing.<p>");
		for (int i = 0; i < 1000; i++) {
			builder.append("<div class=\"dummyParagraph").append(i)
				.append("\">This is just a dummy html code with lot of content to generate a lot of big data to write for load testing.</div>");
		}
		builder.append("</body></html>");
		
		StepHtml html = new StepHtml();
		html.setHtmlSource(builder.toString());
		return html;
		
	}
	
	private void assertExpectedLabels(final Labels labels, final String... expectedLabels) {
		assertEquals(expectedLabels.length, labels.size(), "Expected number of labels");
		for (String expectedLabel : expectedLabels) {
			assertTrue(labels.contains(expectedLabel),
				"expected label '" + expectedLabel + "' to be contained in labels.");
		}
	}
}
