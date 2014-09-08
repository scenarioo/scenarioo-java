package org.scenarioo.api.rules;

import org.scenarioo.api.exception.IllegalCharacterException;

/**
 * @see {@link #checkIdentifier(String)}.
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
			throw new IllegalCharacterException("identifier " + identifier + " contains illegal characters");
		}
	}
	
}
