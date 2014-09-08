package org.scenarioo.api.rules;

import static org.junit.Assert.*;

import org.junit.Test;
import org.scenarioo.api.exception.IllegalCharacterException;

public class CharacterCheckerTest {
	
	@Test
	public void nullIdentifiers_areValid() {
		CharacterChecker.checkIdentifier(null);
	}
	
	@Test
	public void stringsWithoutASlashOrBackslash_areValid() {
		CharacterChecker.checkIdentifier("Some5!*%&?+String");
	}
	
	@Test
	public void stringsWithASlash_resultInAnException() {
		try {
			CharacterChecker.checkIdentifier("abc/def");
			fail();
		} catch (IllegalCharacterException e) {
			assertEquals("identifier abc/def contains illegal characters", e.getMessage());
		}
	}
	
	@Test
	public void stringsWithABackslash_resultInAnException() {
		try {
			CharacterChecker.checkIdentifier("abc\\def");
			fail();
		} catch (IllegalCharacterException e) {
			assertEquals("identifier abc\\def contains illegal characters", e.getMessage());
		}
	}
	
}
