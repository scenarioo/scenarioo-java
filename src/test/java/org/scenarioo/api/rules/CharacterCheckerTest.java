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
		IllegalCharacterException e = assertThrows(IllegalCharacterException.class,
			() -> CharacterChecker.checkIdentifier("abc/def"));
		assertEquals("Identifier abc/def contains illegal characters.", e.getMessage());
	}
	
	@Test
	void checkIdentifier_stringsWithABackslash_resultInAnException() {
		IllegalCharacterException e = assertThrows(IllegalCharacterException.class,
			() -> CharacterChecker.checkIdentifier("abc\\def"));
		assertEquals("Identifier abc\\def contains illegal characters.", e.getMessage());
	}
	
	@Test
	void checkIdentifier_forValidIdentifiers_returnsTheIdentifier() {
		String identifier = "valid";
		assertEquals(identifier, CharacterChecker.checkIdentifier(identifier));
	}
	
	@Test
	void checkLabel_aNullLabel_resultsInAnException() {
		IllegalCharacterException e = assertThrows(IllegalCharacterException.class,
			() -> CharacterChecker.checkLabel(null));
		assertEquals("Label null contains illegal characters.", e.getMessage());
	}
	
	@Test
	void checkLabel_aLabelContainingAllTypesOfAllowedCharacters_passesTheCheck() {
		CharacterChecker.checkLabel("abc_DEF-012 3456789");
	}
	
	@Test
	void checkLabel_aLabelWithASlash_resultsInAnException() {
		IllegalCharacterException e = assertThrows(IllegalCharacterException.class,
			() -> CharacterChecker.checkLabel("abc/def"));
		assertEquals("Label abc/def contains illegal characters.", e.getMessage());
	}
	
	@Test
	void checkLabel_aLabelWithAnUmlaut_resultsInAnException() {
		IllegalCharacterException e = assertThrows(IllegalCharacterException.class,
			() -> CharacterChecker.checkLabel("äuäää"));
		assertEquals("Label äuäää contains illegal characters.", e.getMessage());
	}
	
	@Test
	void checkLabel_forValidLabels_returnsTheLabel() {
		String label = "valid";
		assertEquals(label, CharacterChecker.checkLabel(label));
	}
	
}
