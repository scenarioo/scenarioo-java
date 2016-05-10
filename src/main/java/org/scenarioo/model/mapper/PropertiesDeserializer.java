package org.scenarioo.model.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.scenarioo.model.format3.docu.entities.DocuObject;
import org.scenarioo.model.format3.docu.entities.Properties;

import java.io.IOException;
import java.util.List;

public class PropertiesDeserializer extends JsonDeserializer<Properties> {

    @Override
    public Properties deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        List<DocuObject> entries = ctxt.readValue(jp, ctxt.getTypeFactory().constructCollectionType(List.class, DocuObject.class));
        return new Properties(entries);
    }

}
