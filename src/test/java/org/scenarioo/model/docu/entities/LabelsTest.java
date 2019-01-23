package org.scenarioo.model.docu.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.scenarioo.api.exception.IllegalCharacterException;

class LabelsTest {
	
	@Test
	void singleLabelsCanBeAddedInAChainedFashion() {
		Labels labels = new Labels().addLabel("test-1").addLabel("test-2");
		assertEquals(2, labels.getLabels().size());
	}
	
	@Test
	void aSetOfLabelCanBeSetAtOnce() {
		Labels labels = new Labels();
		Set<String> labelsToSet = new LinkedHashSet<String>();
		labelsToSet.add("valid");
		labelsToSet.add("valid 2");
		
		labels.setLabels(labelsToSet);
		
		assertEquals(2, labels.getLabels().size());
	}
	
	@Test
	void ifAnInvalidSingleLabelIsAdded_anExceptionIsThrown() {
		try {
			Labels labels = new Labels();
			labels.addLabel("test-1").addLabel("test.2");
			fail();
		} catch (IllegalCharacterException e) {
			assertEquals("Label test.2 contains illegal characters.", e.getMessage());
		}
	}
	
	@Test
	void ifASetOfLabelsContainingInvalidCharactersIsSet_anExceptionIsThrown() {
		try {
			Labels labels = new Labels();
			Set<String> labelsToSet = new LinkedHashSet<String>();
			labelsToSet.add("valid");
			labelsToSet.add(".invalid");
			labels.setLabels(labelsToSet);
			fail();
		} catch (IllegalCharacterException e) {
			assertEquals("Label .invalid contains illegal characters.", e.getMessage());
		}
	}
	
	@Test
	void ifANullSetOfLabelsIsSet_anExceptionIsThrown() {
		try {
			Labels labels = new Labels();
			labels.setLabels(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("Labels must not be null.", e.getMessage());
		}
	}
	
}
