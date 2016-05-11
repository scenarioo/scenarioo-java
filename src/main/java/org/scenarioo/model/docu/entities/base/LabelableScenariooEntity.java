package org.scenarioo.model.docu.entities.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.scenarioo.api.rules.CharacterChecker;
import org.scenarioo.api.rules.Preconditions;

/**
 * Base class for special scenarioo entities that can be labeled by attaching labels.
 *
 * @see Labels
 *
 * @param <EntityType> the concrete type that implements this abstract class (type of this)
 */
public abstract class LabelableScenariooEntity<EntityType extends  LabelableScenariooEntity> extends ScenariooEntity<EntityType> {

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected Labels labels = new Labels();

	/**
	 * (optional) Add a label to this object.
	 * 
	 * @param label
	 *            A label string, which must only contain alpha-numeric characters, spaces, underscores and dashes. See
	 *            {@link CharacterChecker#checkLabel(String)}.
	 * @return The object itself to chain forther calls on it.
	 */
	public EntityType addLabel(final String label) {
		labels.add(label);
		return current();
	}
	
	/**
	 * Get all labels of this object.
	 * 
	 * @return The set of labels, never null!
	 */
	public Labels getLabels() {
		return labels;
	}
	
	/**
	 * (optional) Can be used to replace the whole labels object (which is not allowed to be null).
	 * 
	 * We recommend to use the {@link #addLabel} method directly instead to add labels.
	 * 
	 * @param labels
	 *            The labels to set (never null!). Furthermore, only certain characters are allowed. See
	 *            {@link #addLabel(String)}.
	 * @return the entity itself to chain further setter calls on it
	 */
	public EntityType setLabels(final Labels labels) {
		Preconditions.checkNotNull(labels, "Labels not allowed to set to null");
		this.labels = labels;
		return current();
	}

}
