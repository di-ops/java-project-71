package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;


class DifferTest {


    @Test
    void generate_json() throws Exception {
        String actual = Differ.generate("src/test/resources/json1.json", "src/test/resources/json2.json");
        String expected = Files.readString(Path.of("src/test/resources/stylish.txt"));

        Assertions.assertEquals(expected, actual);
    }
}