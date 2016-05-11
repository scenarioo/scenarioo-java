package org.scenarioo.model.docu.mapper;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.scenarioo.model.docu.entities.DocuObject;
import org.scenarioo.model.docu.entities.base.DocuObjectMap;

import java.util.Collection;
import java.util.List;

/**
 * Convert list of DocuObjects to DocuObjectMap for easier access of objects through labelKey
 */
public class ListToDocuObjectMapConverter implements Converter<DocuObjectMap, Collection<DocuObject>> {

    @Override
    public Collection<DocuObject> convert(DocuObjectMap value) {
        return value.values();
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructSimpleType(DocuObjectMap.class, new JavaType[]{});
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructCollectionType(Collection.class, DocuObject.class);
    }
}
