package org.scenarioo.model.docu.entities.base;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.scenarioo.api.rules.Preconditions;
import org.scenarioo.model.docu.entities.DocuObject;
import org.scenarioo.writer.mapper.DocuObjectMapDeserializer;
import org.scenarioo.writer.mapper.ListToDocuObjectMapConverter;

/**
 * DocuObjectMap encapsulates a list of objects with convenience method for fast and easy access and adding docu objects.
 *
 * DocuObjects are mapped in a hash map based on their `labelKey` to access a object through its `labelKey`.
 */
@JsonDeserialize(using = DocuObjectMapDeserializer.class)
@JsonSerialize(converter = ListToDocuObjectMapConverter.class)
public class DocuObjectMap implements Map<String, DocuObject> {

    private Map<String, DocuObject> objects = new LinkedHashMap<String, DocuObject>();

    public DocuObjectMap() {
    }

    public DocuObjectMap(Collection<DocuObject> objects) {
        for (DocuObject obj : objects) {
            add(obj);
        }
    }

    /**
     * Add a DocuObject to this map.
     * @param object the object to add (precondition: labelKey is mandatory to be set to a unique value on this map!)
     */
    public void add(DocuObject object) {
        Preconditions.checkNotNull(object, "DocuObjectMap: Not allowed to add null object");
        Preconditions.checkNotNull(object.getLabelKey(), "DocuObjectMap: Object added must have a labelKey!");
        objects.put(object.getLabelKey(), object);
    }

    /**
     * Factory method to quickly add a simple value as a DocuObject with `labelKey` and `value` to the map.
     *
     * The returned DocuObject can be used to set more optional properties on the object.
     *
     * @param labelKey the label key of the value
     * @param value the value to add
     * @return the object that encapsulates the added value for setting additional optional properties on the added docu object
     */
    public DocuObject add(String labelKey, String value) {
        DocuObject object = new DocuObject(value);
        object.setLabelKey(labelKey);
        add(object);
        return object;
    }

    public DocuObject get(String labelKey) {
        return objects.get(labelKey);
    }

    @Override
    public int size() {
        return objects.size();
    }

    @Override
    public boolean isEmpty() {
        return objects.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return objects.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return objects.containsValue(value);
    }

    @Override
    public DocuObject get(Object key) {
        return objects.get(key);
    }

    @Override
    public DocuObject put(String key, DocuObject value) {
        return objects.put(key, value);
    }

    @Override
    public DocuObject remove(Object key) {
        return objects.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends DocuObject> m) {
        objects.putAll(m);
    }

    @Override
    public void clear() {
        objects.clear();
    }

    @Override
    public Set<String> keySet() {
        return objects.keySet();
    }

    @Override
    public Collection<DocuObject> values() {
        return objects.values();
    }

    @Override
    public Set<Entry<String, DocuObject>> entrySet() {
        return objects.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocuObjectMap that = (DocuObjectMap) o;
        return objects != null ? objects.equals(that.objects) : that.objects == null;
    }

    @Override
    public int hashCode() {
        return objects.hashCode();
    }

}
