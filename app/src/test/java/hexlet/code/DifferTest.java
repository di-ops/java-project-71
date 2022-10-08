package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;


class DifferTest {

    @Test
    void generateJson() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/json1.json",
                "src/test/resources/json2.json",
                "stylish");
        String expected = Files.readString(Path.of("src/test/resources/stylish.txt"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateYaml() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/yaml1.yaml",
                "src/test/resources/yaml2.yaml",
                "stylish");
        String expected = Files.readString(Path.of("src/test/resources/stylish.txt"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateJsonWithArray() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/json_with_array1.json",
                "src/test/resources/json_with_array2.json",
                "stylish");
        String expected = Files.readString(Path.of("src/test/resources/stylish_with_array.txt"));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generateYamlWithArray() throws Exception {
        String actual = Differ.generate(
                "src/test/resources/yaml_with_array1.yaml",
                "src/test/resources/yaml_with_array2.yaml",
                "stylish");
        String expected = Files.readString(Path.of("src/test/resources/stylish_with_array.txt"));

        Assertions.assertEquals(expected, actual);
    }
}
