package org.scenarioo.model.docu.entities.base;

import org.scenarioo.model.docu.format.IdentifierFormat;

/**
 * Basic class for all identifiable scenarioo objects, that all have the abbility to add additional documentation informations as properties and have an id.
 *
 * @param <EntityType> the concrete type that implements this abstract class (type of this)
 */
public abstract class ScenariooEntity<EntityType extends ScenariooEntity> extends ScenariooBaseObject<EntityType> {

    protected String id;

    public String getId() {
        return id;
    }

    public EntityType setId(String id) {
        this.id = IdentifierFormat.sanitize(id);
        return current();
    }

}
