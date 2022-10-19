package hexlet.code;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatOutput) throws Exception {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        Map<String, Object> data1 = Parser.parse(getFileContent(file1), getFileExtension(file1));
        Map<String, Object> data2 = Parser.parse(getFileContent(file2), getFileExtension(file2));
        List<Map<String, Object>> differences = TreeDifference.build(data1, data2);
        return Formatter.format(differences, formatOutput);
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf + 1);
    }

    private static String getFileContent(File file) throws IOException {
        Path path = Paths.get(file.getPath());
        return Files.readString(path);
    }
}
