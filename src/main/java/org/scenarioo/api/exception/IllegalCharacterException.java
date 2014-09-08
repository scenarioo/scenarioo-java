package org.scenarioo.api.exception;

/**
 * This is used when the API finds an illegal character in a restricted field. E.g. slashes / backslashes are not
 * allowed in ID fields.
 */
public class IllegalCharacterException extends RuntimeException {
	
	public IllegalCharacterException() {
		super();
	}
	
	public IllegalCharacterException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public IllegalCharacterException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	public IllegalCharacterException(final String message) {
		super(message);
	}
	
	public IllegalCharacterException(final Throwable cause) {
		super(cause);
	}
	
}
