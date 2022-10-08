package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(File file) throws Exception {
        String fileExtension = getFileExtension(file);
        return switch (fileExtension) {
            case "json" -> parseJson(file);
            case "yaml" -> parseYaml(file);
            default -> throw new RuntimeException("Unknown file extension: " + fileExtension);
        };
    }

    private static Map<String, Object> parseJson(File fileJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(fileJson, new TypeReference<>() {
        });
    }

    private static Map<String, Object> parseYaml(File fileYaml) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(fileYaml, new TypeReference<>() {
        });
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf + 1);
    }
}
