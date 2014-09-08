package org.scenarioo.api.rules;

import org.scenarioo.api.exception.IllegalCharacterException;

/**
 * Checks certain strings for validity.
 * 
 * @see {@link #checkIdentifier(String)}.
 * @see {@link #checkLabel(String)};
 */
public class CharacterChecker {
	
	/**
	 * Checks the identifier for invalid characters. Currently, only slashes and backslashes are disallowed.
	 * 
	 * @param identifier
	 *            E.g. a branch name or a page name.
	 * 
	 * @throws IllegalCharacterException
	 *             If a / or \ is found in the identifier.
	 */
	public static void checkIdentifier(final String identifier) {
		if (identifier == null) {
			return;
		}
		
		if (identifier.contains("/") || identifier.contains("\\")) {
			throw new IllegalCharacterException("Identifier " + identifier + " contains illegal characters.");
		}
	}
	
	/**
	 * Checks a label for invalid characters.
	 * 
	 * @param label
	 *            A label string, valid or invalid.
	 * 
	 * @throws IllegalCharacterException
	 *             If the label string is <code>null</code> or if it does not match the RegEx
	 *             <code>^[ a-zA-Z0-9_-]+$</code>.
	 */
	public static void checkLabel(final String label) {
		if (label == null || !label.matches("^[ a-zA-Z0-9_-]+$")) {
			throw new IllegalCharacterException("Label " + label + " contains illegal characters.");
		}
	}
	
}
