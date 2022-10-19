package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;


class DifferTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    void generateJsonWithArrayInStylishFormat() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/json_with_array1.json",
                "src/test/resources/json_with_array2.json",
                "stylish");
        String expected = Files.readString(Path.of("src/test/resources/stylish_with_array.txt"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateYamlWithArrayInStylishFormat() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/yaml_with_array1.yaml",
                "src/test/resources/yaml_with_array2.yaml",
                "stylish");
        String expected = Files.readString(Path.of("src/test/resources/stylish_with_array.txt"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateJsonWithArrayInPlainFormat() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/json_with_array1.json",
                "src/test/resources/json_with_array2.json",
                "plain");
        String expected = Files.readString(Path.of("src/test/resources/plain_with_array.txt"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateYamlWithArrayInPlainFormat() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/yaml_with_array1.yaml",
                "src/test/resources/yaml_with_array2.yaml",
                "plain");
        String expected = Files.readString(Path.of("src/test/resources/plain_with_array.txt"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateJsonWithArrayInJsonFormat() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/json_with_array1.json",
                "src/test/resources/json_with_array2.json",
                "json");
        String expected = Files.readString(Path.of("src/test/resources/json_with_array.json"));
        Assertions.assertEquals(
                OBJECT_MAPPER.readValue(actual, new TypeReference<>() {
                }).toString(),
                OBJECT_MAPPER.readValue(expected, new TypeReference<>() {
                }).toString()
        );
    }

    @Test
    void generateYamlWithArrayInJsonFormat() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/yaml_with_array1.yaml",
                "src/test/resources/yaml_with_array2.yaml",
                "json");
        String expected = Files.readString(Path.of("src/test/resources/json_with_array.json"));
        Assertions.assertEquals(
                OBJECT_MAPPER.readValue(actual, new TypeReference<>() {
                }).toString(),
                OBJECT_MAPPER.readValue(expected, new TypeReference<>() {
                }).toString()
        );
    }

    @Test
    void generateJsonWithArrayInDefaultFormat() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/json_with_array1.json",
                "src/test/resources/json_with_array2.json");
        String expected = Files.readString(Path.of("src/test/resources/stylish_with_array.txt"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateYamlWithArrayInDefaultFormat() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/yaml_with_array1.yaml",
                "src/test/resources/yaml_with_array2.yaml");
        String expected = Files.readString(Path.of("src/test/resources/stylish_with_array.txt"));

        Assertions.assertEquals(expected, actual);
    }
}
