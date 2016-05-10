package org.scenarioo.model.format3.docu.entities;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.scenarioo.model.mapper.PropertiesDeserializer;
import org.scenarioo.model.mapper.PropertiesSerializer;

/**
 * Properties encapsulates a list of properties with convenience method for fast and easy access and adding properties.
 */
@JsonDeserialize(using = PropertiesDeserializer.class)
@JsonSerialize(using = PropertiesSerializer.class)
public class Properties implements Map<String, DocuObject> {

    private Map<String, DocuObject> properties = new LinkedHashMap<String, DocuObject>();

    public Properties() {
    }

    public Properties(Collection<DocuObject> entries) {
        for (DocuObject obj : entries) {
            add(obj);
        }
    }

    public void add(DocuObject object) {
        properties.put(object.getLabelKey(), object);
    }

    public DocuObject add(String labelKey, String value) {
        DocuObject object = new DocuObject(labelKey, value);
        add(object);
        return object;
    }

    public DocuObject get(String labelKey) {
        return properties.get(labelKey);
    }

    @Override
    public int size() {
        return properties.size();
    }

    @Override
    public boolean isEmpty() {
        return properties.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return properties.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return properties.containsValue(value);
    }

    @Override
    public DocuObject get(Object key) {
        return properties.get(key);
    }

    @Override
    public DocuObject put(String key, DocuObject value) {
        return properties.put(key, value);
    }

    @Override
    public DocuObject remove(Object key) {
        return properties.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends DocuObject> m) {
        properties.putAll(m);
    }

    @Override
    public void clear() {
        properties.clear();
    }

    @Override
    public Set<String> keySet() {
        return properties.keySet();
    }

    @Override
    public Collection<DocuObject> values() {
        return properties.values();
    }

    @Override
    public Set<Entry<String, DocuObject>> entrySet() {
        return properties.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Properties that = (Properties) o;
        return properties != null ? properties.equals(that.properties) : that.properties == null;
    }

    @Override
    public int hashCode() {
        return properties.hashCode();
    }

}
