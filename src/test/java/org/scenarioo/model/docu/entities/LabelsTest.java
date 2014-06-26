package org.scenarioo.model.docu.entities;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

public class LabelsTest {
	
	@Test
	public void addChaining() {
		Labels labels = new Labels();
		labels.add("test-1").add("test-2");
		
		assertEquals(2, labels.toSet().size());
	}
	
	@Test
	public void set() {
		Labels labels = new Labels();
		Set<String> labelsToSet = new LinkedHashSet<String>();
		labelsToSet.add("valid");
		labelsToSet.add("valid 2");
		
		labels.set(labelsToSet);
		
		assertEquals(2, labels.toSet().size());
	}
	
	@Test
	public void validation() {
		assertTrue(Labels.isValidLabel("test-1"));
		assertTrue(Labels.isValidLabel("test 1"));
		assertTrue(Labels.isValidLabel("tEst_1"));
		
		assertFalse(Labels.isValidLabel("test.1"));
		assertFalse(Labels.isValidLabel("test_1 è"));
		assertFalse(Labels.isValidLabel("t,est"));
	}
	
	@Test(expected=RuntimeException.class)
	public void immediateValidationForAdd() {
		Labels labels = new Labels();
		labels.add("test-1").add("test.2");
	}
	
	@Test(expected=RuntimeException.class)
	public void immediateValidationForSet() {
		Labels labels = new Labels();
		Set<String> labelsToSet = new LinkedHashSet<String>();
		labelsToSet.add("valid");
		labelsToSet.add(".invalid");
		labels.set(labelsToSet);
	}
}
