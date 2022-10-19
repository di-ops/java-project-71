package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String content, String fileExtension) throws Exception {
        return switch (fileExtension) {
            case "json" -> parseJson(content);
            case "yml", "yaml" -> parseYaml(content);
            default -> throw new RuntimeException("Unknown file extension: " + fileExtension);
        };
    }

    private static Map<String, Object> parseJson(String contentJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(contentJson, new TypeReference<>() {
        });
    }

    private static Map<String, Object> parseYaml(String contentYaml) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(contentYaml, new TypeReference<>() {
        });
    }
}
