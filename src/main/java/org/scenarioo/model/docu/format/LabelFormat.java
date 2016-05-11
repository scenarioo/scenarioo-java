package org.scenarioo.model.docu.format;

import org.apache.commons.lang3.StringUtils;
import org.scenarioo.api.exception.IllegalCharacterException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Label format for labels
 */
public class LabelFormat {

    /**
     * Allowed characters to be used in labels (all other characters will be replaced automaticaly).
     *
     * Spaces are allowed in labels (as opposed to identifiers where it is not)
     */
    public static final String ALLOWED_CHARACTERS_REGEXP = "A-Za-z0-9 _\\-";

    /**
     * Calculate a string that can be used as a label from any string to use in `id`-fields.
     *
     * All diacritics and accents will be replaced.
     *
     * @param text text to sanitize to be useful as a label
     * @return the sanitized label that can be used
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

    public static List<String> sanitizeAll(Collection<? extends String> labels) {
        List<String> result = new LinkedList<String>();
        for (String label : labels) {
            result.add(sanitize(label));
        }
        return result;
    }

    public static boolean isValid(String label) {
        return StringUtils.isBlank(label) || label.matches("^[" + ALLOWED_CHARACTERS_REGEXP + "]+$");
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
