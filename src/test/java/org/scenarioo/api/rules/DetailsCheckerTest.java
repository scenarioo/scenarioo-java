package org.scenarioo.api.rules;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.scenarioo.api.exception.IllegalCharacterException;
import org.scenarioo.model.docu.entities.generic.Details;
import org.scenarioo.model.docu.entities.generic.ObjectDescription;

class DetailsCheckerTest {
	
	private static final String ILLEGAL_IDENTIFIER = "/illegal";
	private static final String LEGAL_IDENTIFIER = "legal";
	
	@Test
	void ifDetailsIsNull_theCheckPasses() {
		DetailsChecker.checkIdentifiers(null);
	}
	
	@Test
	void ifAllIdentifiersAreValid_theCheckPasses() {
		DetailsChecker.checkIdentifiers(getDetailsWithValidIdentifiers());
	}
	
	@Test
	void ifThereIsAnInvalidObjectType_anExceptionIsThrown() {
		try {
			DetailsChecker.checkIdentifiers(getDetailsWithInvalidObjectType());
			fail();
		} catch (IllegalCharacterException e) {
			assertEquals("Identifier /illegal contains illegal characters.", e.getMessage());
		}
	}
	
	@Test
	void ifThereIsAnInvalidObjectName_anExceptionIsThrown() {
		try {
			DetailsChecker.checkIdentifiers(getDetailsWithInvalidObjectName());
			fail();
		} catch (IllegalCharacterException e) {
			assertEquals("Identifier /illegal contains illegal characters.", e.getMessage());
		}
	}
	
	private Details getDetailsWithInvalidObjectType() {
		Details details = getDetailsWithValidIdentifiers();
		details.addDetail("invalid", getObjectDescriptionWithInvalidObjectType());
		return details;
	}
	
	private Details getDetailsWithInvalidObjectName() {
		Details details = getDetailsWithValidIdentifiers();
		details.addDetail("invalid", getObjectDescriptionWithInvalidObjectName());
		return details;
	}
	
	private ObjectDescription getObjectDescriptionWithInvalidObjectType() {
		return new ObjectDescription(ILLEGAL_IDENTIFIER, LEGAL_IDENTIFIER);
	}
	
	private ObjectDescription getObjectDescriptionWithInvalidObjectName() {
		ObjectDescription objectDescription = new ObjectDescription(LEGAL_IDENTIFIER, LEGAL_IDENTIFIER);
		objectDescription.addDetail("illegal", createObjectDescriptionWithInvalidObjectName());
		return objectDescription;
	}
	
	private ObjectDescription createObjectDescriptionWithInvalidObjectName() {
		return new ObjectDescription(LEGAL_IDENTIFIER, ILLEGAL_IDENTIFIER);
	}
	
	private Details getDetailsWithValidIdentifiers() {
		Details details = new Details();
		details.addDetail("test", createObjectDescriptionWithValidIdentifiers());
		return details;
	}
	
	private ObjectDescription createObjectDescriptionWithValidIdentifiers() {
		return new ObjectDescription(LEGAL_IDENTIFIER, LEGAL_IDENTIFIER);
	}
	
}
