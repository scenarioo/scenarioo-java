package org.scenarioo.api.rules;

/**
 * Helper class to check some preconditions.
 */
public class Preconditions {
	
	public static void checkNotNull(final Object object, final String message) {
		if (object == null) {
			throw new NullPointerException(message);
		}
	}

	public static void checkEquals(final Object obj1, final Object obj2, String message) {
		if (!obj1.equals(obj2)) {
			throw new IllegalArgumentException(message);
		}
	}
	
}
