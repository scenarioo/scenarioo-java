package org.scenarioo.api.rules;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.scenarioo.api.exception.IllegalCharacterException;

class CharacterCheckerTest {
	
	@Test
	void checkIdentifier_nullIdentifiers_areValid() {
		CharacterChecker.checkIdentifier(null);
	}
	
	@Test
	void checkIdentifier_stringsWithoutASlashOrBackslash_areValid() {
		CharacterChecker.checkIdentifier("Some5!*%&?+String");
	}
	
	@Test
	void checkIdentifier_stringsWithASlash_resultInAnException() {
		try {
			CharacterChecker.checkIdentifier("abc/def");
			fail();
		} catch (IllegalCharacterException e) {
			assertEquals("Identifier abc/def contains illegal characters.", e.getMessage());
		}
	}
	
	@Test
	void checkIdentifier_stringsWithABackslash_resultInAnException() {
		try {
			CharacterChecker.checkIdentifier("abc\\def");
			fail();
		} catch (IllegalCharacterException e) {
			assertEquals("Identifier abc\\def contains illegal characters.", e.getMessage());
		}
	}
	
	@Test
	void checkIdentifier_forValidIdentifiers_returnsTheIdentifier() {
		String identifier = "valid";
		assertEquals(identifier, CharacterChecker.checkIdentifier(identifier));
	}
	
	@Test
	void checkLabel_aNullLabel_resultsInAnException() {
		try {
			CharacterChecker.checkLabel(null);
			fail();
		} catch (IllegalCharacterException e) {
			assertEquals("Label null contains illegal characters.", e.getMessage());
		}
	}
	
	@Test
	void checkLabel_aLabelContainingAllTypesOfAllowedCharacters_passesTheCheck() {
		CharacterChecker.checkLabel("abc_DEF-012 3456789");
	}
	
	@Test
	void checkLabel_aLabelWithASlash_resultsInAnException() {
		try {
			CharacterChecker.checkLabel("abc/def");
			fail();
		} catch (IllegalCharacterException e) {
			assertEquals("Label abc/def contains illegal characters.", e.getMessage());
		}
	}
	
	@Test
	void checkLabel_aLabelWithAnUmlaut_resultsInAnException() {
		try {
			CharacterChecker.checkLabel("äuäää");
			fail();
		} catch (IllegalCharacterException e) {
			assertEquals("Label äuäää contains illegal characters.", e.getMessage());
		}
	}
	
	@Test
	void checkLabel_forValidLabels_returnsTheLabel() {
		String label = "valid";
		assertEquals(label, CharacterChecker.checkLabel(label));
	}
	
}
