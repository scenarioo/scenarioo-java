package org.scenarioo.api.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class IdentifierSanitizerTest {
	
	@Test
	public void nullIdentifiersStayNull() {
		assertEquals(null, IdentifierSanitizer.sanitize(null));
	}
	
	@Test
	public void slashesAreReplacedWithUnderscores() {
		assertEquals("test_identifier__", IdentifierSanitizer.sanitize("test/identifier//"));
	}
	
	@Test
	public void backslashesAreReplacedWithUnderscores() {
		assertEquals("test_identifier__", IdentifierSanitizer.sanitize("test\\identifier\\\\"));
	}
	
}
