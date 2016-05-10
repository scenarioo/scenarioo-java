package org.scenarioo.model.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sun.javafx.collections.MappingChange;
import org.scenarioo.model.format3.docu.entities.Properties;

import java.io.IOException;
import java.util.Map;

public class PropertiesSerializer extends JsonSerializer<Properties> {

    @Override
    public void serialize(final Properties properties, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
        provider.defaultSerializeValue(properties.values(), jgen);
    }

}
