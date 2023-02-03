package org.scenarioo.api.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IdentifierSanitizerTest {
	
	@Test
	void nullIdentifiersStayNull() {
		assertNull(IdentifierSanitizer.sanitize(null));
	}
	
	@Test
	void slashesAreReplacedWithUnderscores() {
		assertEquals("test_identifier__", IdentifierSanitizer.sanitize("test/identifier//"));
	}
	
	@Test
	void backslashesAreReplacedWithUnderscores() {
		assertEquals("test_identifier__", IdentifierSanitizer.sanitize("test\\identifier\\\\"));
	}
	
}
