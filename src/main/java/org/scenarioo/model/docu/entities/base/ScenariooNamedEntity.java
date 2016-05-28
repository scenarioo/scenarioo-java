package org.scenarioo.model.docu.entities.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.scenarioo.api.rules.Preconditions;
import org.scenarioo.model.docu.entities.Step;

/**
 * Base class for named scenarioo entities that additionaly have a name and a description field.
 *
 * @param <EntityType> the concrete type that implements this abstract class (type of this)
 */
public abstract class ScenariooNamedEntity<EntityType extends ScenariooNamedEntity> extends ScenariooEntity<EntityType> {

	private String name;

	private String description = "";

	public ScenariooNamedEntity() {
		super();
	}

	public ScenariooNamedEntity(String name) {
		this.name = name;
	}

	public ScenariooNamedEntity(String name, String description) {
		this.name = name;
		this.description = description;
	}


	public String getName() {
		return name;
	}

	public EntityType setName(String name) {
		this.name = name;
		return current();
	}

	public String getDescription() {
		return description;
	}

	public EntityType setDescription(String description) {
		this.description = description;
		return current();
	}

}
