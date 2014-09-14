package org.scenarioo.model.docu.entities;

import org.scenarioo.model.docu.entities.generic.Details;

/**
 * Scenarioo documentation object that may be detailed by adding applications specific key value properties to its
 * details content.
 * 
 * See {@link Details} for documentation what can be used as values for details properties.
 */
public interface Detailable {
	
	/**
	 * Add application specific details as key-value-data-items.
	 * 
	 * See {@link Details} for what can be used as values.
	 */
	public Details addDetail(final String key, final Object value);
	
	/**
	 * Get the key value map containing all the application specific details properties.
	 * 
	 * @return the details, never null!
	 */
	public Details getDetails();
	
}
