package org.scenarioo.model.docu.entities;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;
import org.scenarioo.api.exception.IllegalCharacterException;
import org.scenarioo.model.docu.entities.base.Labels;

public class LabelsTest {
	
	@Test
	public void singleLabelsCanBeAddedInAChainedFashion() {
		Labels labels = new Labels().addLabel("test-1").addLabel("test-2");
		assertEquals(2, labels.getLabels().size());
	}
	
	@Test
	public void aSetOfLabelCanBeSetAtOnce() {
		Labels labels = new Labels();
		Set<String> labelsToSet = new LinkedHashSet<String>();
		labelsToSet.add("valid");
		labelsToSet.add("valid 2");
		
		labels.setLabels(labelsToSet);
		
		assertEquals(2, labels.getLabels().size());
	}
	
	@Test
	public void ifAnInvalidSingleLabelIsAdded_itGetsSanitized() {
		Labels labels = new Labels();
		labels.addLabel("test-1").addLabel("test.2");
		assertEquals(2, labels.getLabels().size());
		assertEquals("Second label expected to be sanitized", "test-2", labels.getLabels().toArray()[1]);
	}
	
	@Test
	public void ifASetOfLabelsContainingInvalidCharactersIsSet_itGetsSanititzed() {
		Labels labels = new Labels();
		Set<String> labelsToSet = new LinkedHashSet<String>();
		labelsToSet.add("valid");
		labelsToSet.add(".invalid");
		labels.setLabels(labelsToSet);
		assertEquals("Second label expected to be sanitized", "-invalid", labels.getLabels().toArray()[1]);
	}
	
	@Test
	public void ifANullSetOfLabelsIsSet_anExceptionIsThrown() {
		try {
			Labels labels = new Labels();
			labels.setLabels(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("Labels must not be null.", e.getMessage());
		}
	}
	
}
