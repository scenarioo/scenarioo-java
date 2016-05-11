package org.scenarioo.model.docu.format;

import static org.junit.Assert.*;

import org.junit.Test;

public class IdentifierFormatTest {
	
	@Test
	public void nullIdentifiersStayNull() {
		assertEquals(null, IdentifierFormat.sanitize(null));
	}
	
	@Test
	public void slashesAreReplacedWithUnderscores() {
		assertEquals("test_identifier__", IdentifierFormat.sanitize("test/identifier//"));
	}
	
	@Test
	public void backslashesAreReplacedWithUnderscores() {
		assertEquals("test_identifier__", IdentifierFormat.sanitize("test\\identifier\\\\"));
	}

	@Test
	public void diacriticsAreReplaced() {
		assertEquals("aaaaa", IdentifierFormat.sanitize("äáàâã"));
		assertEquals("ooooo", IdentifierFormat.sanitize("öóòôõ"));
		assertEquals("uuuu", IdentifierFormat.sanitize("üúùû"));
		assertEquals("c", IdentifierFormat.sanitize("ç"));
	}


}
