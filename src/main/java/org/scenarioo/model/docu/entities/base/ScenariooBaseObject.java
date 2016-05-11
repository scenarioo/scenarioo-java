package org.scenarioo.model.docu.entities.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.scenarioo.model.docu.entities.DocuObject;

/**
 * Base class for all scenarioo objects that can have `DocuObject`s as properties attached.
 *
 * @param <EntityType> the concrete type that implements this abstract class (type of this)
 */
public class ScenariooBaseObject<EntityType extends ScenariooBaseObject> {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected DocuObjectMap properties = new DocuObjectMap();

    public ScenariooBaseObject() {}

    /**
     * The casted this object`.
     * Encapsulate cast warning in casting this to entity type.
     *
     * @return this object cast to its concrete entity type
     */
    protected EntityType current() {
        return (EntityType)this;
    }


    public DocuObjectMap getProperties() {
        return properties;
    }

    public EntityType setProperties(DocuObjectMap properties) {
        this.properties = properties;
        return current();
    }

    /**
     * Add a simple property with a string value
     * @param labelKey the key of the value
     * @param value the value
     * @return properties object to chain further properties beeing added
     */
    public DocuObject addProperty(String labelKey, String value) {
        return properties.add(labelKey, value);
    }

    /**
     * Add a structured object value as a property value (needs label key to be set!)
     * @param object the object value with a required labelKey
     * @return this entity object to chain further calls on this entity
     */
    public EntityType addProperty(DocuObject object) {
        properties.add(object);
        return current();
    }



}
