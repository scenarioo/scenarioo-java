package org.scenarioo.api;

import static org.junit.Assert.*;
import static org.scenarioo.api.TestConstants.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.scenarioo.api.exception.IllegalCharacterException;
import org.scenarioo.model.docu.entities.Page;
import org.scenarioo.model.docu.entities.Scenario;
import org.scenarioo.model.docu.entities.Step;
import org.scenarioo.model.docu.entities.StepDescription;
import org.scenarioo.model.docu.entities.StepMetadata;
import org.scenarioo.model.docu.entities.UseCase;
import org.scenarioo.model.docu.entities.generic.Details;
import org.scenarioo.model.docu.entities.generic.ObjectDescription;

/**
 * <p>
 * Slashes are not allowed in all fields that are used as IDs. The reason for this is that they could be used in URL
 * encoding in URLs where they are not allowed by Tomcat for security reasons. Therefore slashes and backslashes are not
 * allowed in the following fields:
 * </p>
 * <ul>
 * <li>branch name</li>
 * <li>build name</li>
 * <li>use case name</li>
 * <li>scenario name</li>
 * <li>page name</li>
 * <li>object type</li>
 * <li>object name</li>
 * </ul>
 */
public class ForbiddenSlashesTest {
	
	private static final String ILLEGAL_NAME = "/illegal";
	private static final String LEGAL_NAME = "legal";
	
	private final File dataDirectory = new File("tmp");
	private ScenarioDocuWriter scenarioDocuWriter;
	
	@Before
	public void setupTest() {
		if (!dataDirectory.exists()) {
			dataDirectory.mkdirs();
		}
		scenarioDocuWriter = new ScenarioDocuWriter(dataDirectory, LEGAL_NAME, LEGAL_NAME);
	}
	
	@After
	public void cleanup() {
		deleteDirectory(TEST_ROOT_DIRECTORY);
	}
	
	@Test
	public void creatingAScenarioDocuWriter_withAnIllegalBranchName_resultsInAnException() {
		try {
			new ScenarioDocuWriter(dataDirectory, ILLEGAL_NAME, LEGAL_NAME);
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void creatingAScenarioDocuWriter_withAnIllegalBuildName_resultsInAnException() {
		try {
			new ScenarioDocuWriter(dataDirectory, LEGAL_NAME, ILLEGAL_NAME);
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void writingAUseCase_withAnIllegalUseCaseName_resultsInAnException() {
		try {
			scenarioDocuWriter.saveUseCase(getUseCaseWithIllegalName());
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void writingAScenario_withAnIllegalUseCaseName_resultsInAnException() {
		try {
			scenarioDocuWriter.saveScenario(ILLEGAL_NAME, getScenarioWithName(LEGAL_NAME));
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void writingAScenario_withAnIllegalScenarioName_resultsInAnException() {
		try {
			scenarioDocuWriter.saveScenario(LEGAL_NAME, getScenarioWithName(ILLEGAL_NAME));
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void writingAStep_withAnIllegalUseCaseName_resultsInAnException() {
		try {
			scenarioDocuWriter.saveStep(ILLEGAL_NAME, LEGAL_NAME, getStepWithPageName(LEGAL_NAME));
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void writingAStep_withAnIllegalScenarioName_resultsInAnException() {
		try {
			scenarioDocuWriter.saveStep(LEGAL_NAME, ILLEGAL_NAME, getStepWithPageName(LEGAL_NAME));
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void writingAStep_withAnIllegalPageName_resultsInAnException() {
		try {
			scenarioDocuWriter.saveStep(LEGAL_NAME, LEGAL_NAME, getStepWithPageName(ILLEGAL_NAME));
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void writingAStep_withAnIllegalObjectTypeInMetadata_resultsInAnException() {
		try {
			scenarioDocuWriter.saveStep(LEGAL_NAME, LEGAL_NAME, getStepWithIllegalObjectTypeInMetadata());
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void writingAStep_withAnIllegalObjectNameInMetadata_resultsInAnException() {
		try {
			scenarioDocuWriter.saveStep(LEGAL_NAME, LEGAL_NAME, getStepWithIllegalObjectNameInMetadata());
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void writingAStep_withAnIllegalObjectTypeInStepDescription_resultsInAnException() {
		try {
			scenarioDocuWriter.saveStep(LEGAL_NAME, LEGAL_NAME, getStepWithIllegalObjectTypeInStepDescription());
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void writingAStep_withAnIllegalObjectNameInStepDescription_resultsInAnException() {
		try {
			scenarioDocuWriter.saveStep(LEGAL_NAME, LEGAL_NAME, getStepWithIllegalObjectNameInStepDescription());
			fail();
		} catch (IllegalCharacterException e) {
			assertException(e);
		}
	}
	
	@Test
	public void readingAUseCase_withAnIllegalBranchName_resultsInAnException() {
		// TODO [#321]
	}
	
	@Test
	public void readingAUseCase_withAnIllegalBuildName_resultsInAnException() {
		// TODO [#321]
	}
	
	@Test
	public void readingAUseCase_withAnIllegalUseCaseName_resultsInAnException() {
		// TODO [#321]
	}
	
	@Test
	public void readingAScenario_withAnIllegalScenarioName_resultsInAnException() {
		// TODO [#321]
	}
	
	@Test
	public void readingAStep_withAnIllegalPageName_worksAndSanitizesThePageName() {
		// TODO [#321]
	}
	
	@Test
	public void readingAStep_withAnIllegalObjectType_resultsInAnException() {
		// TODO [#321]
	}
	
	@Test
	public void readingAStep_withAnIllegalObjectName_resultsInAnException() {
		// TODO [#321]
	}
	
	private Scenario getScenarioWithName(final String scenarioName) {
		Scenario scenario = new Scenario();
		scenario.setName(scenarioName);
		return scenario;
	}
	
	private UseCase getUseCaseWithIllegalName() {
		UseCase useCase = new UseCase();
		useCase.setName(ILLEGAL_NAME);
		return useCase;
	}
	
	private Step getStepWithPageName(final String pageName) {
		Step step = new Step();
		step.setPage(getPageWithPageName(pageName));
		return step;
	}
	
	private Page getPageWithPageName(final String pageName) {
		Page page = new Page();
		page.setName(pageName);
		return page;
	}
	
	private Step getStepWithIllegalObjectTypeInMetadata() {
		Step step = getStepWithPageName(LEGAL_NAME);
		step.setMetadata(getStepMetadata(LEGAL_NAME, ILLEGAL_NAME));
		return step;
	}
	
	private Step getStepWithIllegalObjectTypeInStepDescription() {
		Step step = getStepWithPageName(LEGAL_NAME);
		step.setStepDescription(getStepDescription(ILLEGAL_NAME, LEGAL_NAME));
		return step;
	};
	
	private StepDescription getStepDescription(final String objectType, final String objectName) {
		StepDescription stepDescription = new StepDescription();
		stepDescription.addDetails("test", getObjectDescription(objectType, objectName));
		return stepDescription;
	}
	
	private Step getStepWithIllegalObjectNameInMetadata() {
		Step step = getStepWithPageName(LEGAL_NAME);
		step.setMetadata(getStepMetadata(ILLEGAL_NAME, LEGAL_NAME));
		return step;
	}
	
	private Step getStepWithIllegalObjectNameInStepDescription() {
		Step step = getStepWithPageName(LEGAL_NAME);
		step.setStepDescription(getStepDescription(LEGAL_NAME, ILLEGAL_NAME));
		return step;
	}
	
	private StepMetadata getStepMetadata(final String objectType, final String ObjectName) {
		StepMetadata stepMetadata = new StepMetadata();
		stepMetadata.setDetails(getDetailsWithObject(objectType, ObjectName));
		return stepMetadata;
	}
	
	private Details getDetailsWithObject(final String objectType, final String objectName) {
		Details details = new Details();
		details.addDetail("test", getObjectDescription(objectType, objectName));
		return details;
	}
	
	private Object getObjectDescription(final String objectType, final String objectName) {
		return new ObjectDescription(objectType, objectName);
	}
	
	private void assertException(final IllegalCharacterException e) {
		assertEquals("Identifier " + ILLEGAL_NAME + " contains illegal characters.", e.getMessage());
	}
	
	private static void deleteDirectory(final File testRootDirectory) {
		try {
			FileUtils.deleteDirectory(TEST_ROOT_DIRECTORY);
		} catch (IOException e) {
			throw new RuntimeException("Could not delete test data directory", e);
		}
	}
	
}
