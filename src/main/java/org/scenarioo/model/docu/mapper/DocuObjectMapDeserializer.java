package org.scenarioo.model.docu.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.scenarioo.model.docu.entities.DocuObject;
import org.scenarioo.model.docu.entities.base.DocuObjectMap;

import java.io.IOException;
import java.util.List;

/**
 * Serializer to serialize DocuObjectMaps without a property key for each contained DocuObject (allready included in object)
 */
public class DocuObjectMapDeserializer extends JsonDeserializer<DocuObjectMap> {

    @Override
    public DocuObjectMap deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        List<DocuObject> entries = ctxt.readValue(jp, ctxt.getTypeFactory().constructCollectionType(List.class, DocuObject.class));
        return new DocuObjectMap(entries);
    }

}
