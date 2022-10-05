package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        Map<String, String> fromFile1 = parse(file1);
        Map<String, String> fromFile2 = parse(file2);
        return difference(fromFile1, fromFile2);
    }

    private static Map<String, String> parse(File content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<>() {
        });
    }

    private static String difference(Map<String, String> content1, Map<String, String> content2) {
        Set<String> params = new TreeSet<>();
        params.addAll(content1.keySet());
        params.addAll(content2.keySet());
        StringBuilder diff = new StringBuilder();
        diff.append("{\n");
        params.forEach(p -> {
            if (content1.containsKey(p) && content2.containsKey(p)) {
                if (content1.get(p).equals(content2.get(p))) {
                    diff.append(String.format("    %s: %s%n", p, content1.get(p)));
                } else {
                    diff.append(String.format("  - %s: %s%n", p, content1.get(p)));
                    diff.append(String.format("  + %s: %s%n", p, content2.get(p)));
                }
                return;
            }
            if (content1.containsKey(p)) {
                diff.append(String.format("  - %s: %s%n", p, content1.get(p)));
                return;
            }
            if (content2.containsKey(p)) {
                diff.append(String.format("  + %s: %s%n", p, content2.get(p)));
            }
        });
        diff.append("}");
        return diff.toString();
    }
}
