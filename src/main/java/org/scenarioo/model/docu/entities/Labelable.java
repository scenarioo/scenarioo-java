package org.scenarioo.model.docu.entities;

/**
 * Object that may be labeled with labels.
 * 
 * A label is a string to categorize the objects with (kind of simple tags).
 */
public interface Labelable {
	
	/**
	 * (optional) Add a label to this object.
	 * 
	 * @param label
	 *            a unique string that identifies this label
	 * @return the set containing all the labels, can be used to chain additional method calls for adding multiple
	 *         labels.
	 */
	public Labels addLabel(final String label);
	
	/**
	 * Get all labels of this object.
	 * 
	 * @return the set of labels, never null!
	 */
	public Labels getLabels();
	
	/**
	 * (optional) Can be used to replace the whole labels object (which is not allowed to be null).
	 * 
	 * We recommend to use the {@link #addLabel} method directly instead to add labels.
	 * 
	 * @param labels
	 *            the labels to set (never null!)
	 */
	public void setLabels(final Labels labels);
	
}
