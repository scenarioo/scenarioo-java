package org.scenarioo.api.rules;

import java.util.Map;
import java.util.Map.Entry;

import org.scenarioo.model.docu.entities.generic.Details;
import org.scenarioo.model.docu.entities.generic.ObjectDescription;
import org.scenarioo.model.docu.entities.generic.ObjectReference;

/**
 * Uses the {@link CharacterChecker} to recursively check all identifiers in a {@link Details} tree.
 */
public class DetailsChecker {
	
	public static void checkIdentifiers(final Details details) {
		if (details == null) {
			return;
		}
		
		Map<String, Object> properties = details.getProperties();
		for (Entry<String, Object> entry : properties.entrySet()) {
			Object value = entry.getValue();
			if (value instanceof ObjectDescription) {
				checkIdentifiers((ObjectDescription) value);
			} else if (value instanceof ObjectReference) {
				checkIdentifiers((ObjectReference) value);
			}
		}
	}
	
	private static void checkIdentifiers(final ObjectReference objectReference) {
		CharacterChecker.checkIdentifier(objectReference.getType());
		CharacterChecker.checkIdentifier(objectReference.getName());
	}
	
	private static void checkIdentifiers(final ObjectDescription objectDescription) {
		CharacterChecker.checkIdentifier(objectDescription.getType());
		CharacterChecker.checkIdentifier(objectDescription.getName());
		checkIdentifiers(objectDescription.getDetails());
	}
	
}
