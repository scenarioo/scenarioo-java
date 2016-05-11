package org.scenarioo.model.docu.format;

import org.apache.commons.lang3.StringUtils;
import org.scenarioo.api.exception.IllegalCharacterException;

/**
 * Helper methods for identifier string fields.
 */
public class IdentifierFormat {

    /**
     * Allowed characters to be used in identifiers (all other characters will be replaced automaticaly).
     */
    public static final String ALLOWED_CHARACTERS_REGEXP = "A-Za-z0-9_\\-";

    /**
     * Calculate a string that can be used as a identifier form any string to use in `id`-fields.
     *
     * All diacritics and accents and other unallowed characters will be replaced.
     *
     * @param text text to sanitize to be useful as an identifier
     * @return the sanitized identifier that can be used
     */
    public static String sanitize(String text) {

        if (text == null) {
            return null;
        }

        // 1. Remove diacritics
        text = StringUtils.stripAccents(text);

        // 2. replace slashes and backslashes by underscore
        text = text.replace("/", "_");
        text = text.replace("\\", "_");

        // 3. replace all unallowed characters by `-`
        text = text.replaceAll("[^" + ALLOWED_CHARACTERS_REGEXP + "]", "-");

        return text;
    }

    public static boolean isValid(String identifier) {
        return StringUtils.isBlank(identifier) || identifier.matches("^[" + ALLOWED_CHARACTERS_REGEXP + "]+$");
    }

    /**
     * Validate an identifier to be valid.
     *
     * @throws IllegalArgumentException if not valid
     */
    public static void assertValid(String identifier) {
        if (!isValid(identifier)) {
            throw new IllegalCharacterException("Invalid identifier: " + identifier);
        }
    }

}
