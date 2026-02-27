package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> void writeList(String path, List<T> data) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(path), data);
        } catch (IOException e) {
            throw new RuntimeException("Error writing JSON file", e);
        }
    }

    public static <T> List<T> readList(String path, Class<T> clazz) {
        try {
            File file = new File(path);

            if (!file.exists()) return List.of();

            CollectionType type = mapper.getTypeFactory()
                    .constructCollectionType(List.class, clazz);

            return mapper.readValue(file, type);

        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
    }
}