package org.scenarioo.api;

import static org.scenarioo.api.TestConstants.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaveScreenshotTest {

	private static final String pngFileName = "/scenarioo-icon-unittest.png";
	private static byte[] imageAsByteArray;

	private ScenarioDocuWriter writer;

	@BeforeAll
	static void loadImage() {
		try {
			imageAsByteArray = FileUtils.readFileToByteArray(new File(SaveScreenshotTest.class.getResource(pngFileName)
					.getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	void setUp() {
		TEST_ROOT_DIRECTORY.mkdirs();
		writer = new ScenarioDocuWriter(TEST_ROOT_DIRECTORY, TEST_BRANCH_NAME, TEST_BUILD_NAME);
	}

	@Test
	void savePngScreenshot() {
		writer.saveScreenshotAsPng(TEST_CASE_NAME, TEST_SCENARIO_NAME, TEST_STEP_INDEX, imageAsByteArray);
		writer.flush();

		assertStoredFileContentEqualsOriginalImage();
	}

	private void assertStoredFileContentEqualsOriginalImage() {
		File file = writer.getScreenshotFile(TEST_CASE_NAME, TEST_SCENARIO_NAME, TEST_STEP_INDEX);
		byte[] fileAsByteArray;

		try {
			fileAsByteArray = FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			Assertions.fail("Could not read file " + file);
			e.printStackTrace();
			return;
		}

		Assertions.assertArrayEquals(imageAsByteArray, fileAsByteArray);
	}

}
