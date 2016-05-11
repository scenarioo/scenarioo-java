package org.scenarioo.writer.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.scenarioo.api.files.ObjectFromDirectory;
import org.scenarioo.model.docu.entities.Build;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates utility method for writing and reading JSON.
 */
public class JsonUtil {

    public static ObjectMapper mapper = createJsonMapper();

    public static <E> E parse(String jsonString, Class<E> entityClass) {
        try {
            return mapper.readValue(jsonString, entityClass);
        } catch (IOException e) {
            throw new RuntimeException("Could not parse object of type " + entityClass.getSimpleName() + "  from: " + jsonString, e);
        }
    }

    public static <E> E load(Class<E> entityClass, File file) {
        return load(file, entityClass);
    }

    public static <E> E load(File file, Class<E> entityClass) {
        try {
            return createJsonMapper().readValue(file, entityClass);
        } catch (IOException e) {
            throw new RuntimeException("Could not load object of type " + entityClass.getSimpleName() + " from file " + file.getAbsolutePath(), e);
        }
    }

    public static String stringify(Object object) throws IOException {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException("Could not stringify object of type " + object.getClass().getSimpleName() + ": " + object, e);
        }
    }

    public static void save(Object object, File file) {
        try {
            mapper.writeValue(file, object);
        } catch (IOException e) {
            throw new RuntimeException("Could not save object of type " + object.getClass().getSimpleName() + " to file " + file.getAbsolutePath(), e);
        }
    }

    private static ObjectMapper createJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }


    public static <T> List<T> loadListOfFiles(final List<File> files, final Class<T> targetClass) {
        List<T> result = new ArrayList<T>();
        for (File file : files) {
            result.add(load(targetClass, file));
        }
        return result;
    }

    /**
     * Read all passed files and unmarshall these to the given object type, also return the name of the directory the
     * object was loaded from.
     */
    public static <T> List<ObjectFromDirectory<T>> loadListOfFilesWithDirNames(final List<File> files, final Class<T> targetClass) {
        List<ObjectFromDirectory<T>> result = new ArrayList<ObjectFromDirectory<T>>();
        for (File file : files) {
            result.add(new ObjectFromDirectory<T>(load(targetClass, file), file.getParentFile().getName()));
        }
        return result;
    }
}
