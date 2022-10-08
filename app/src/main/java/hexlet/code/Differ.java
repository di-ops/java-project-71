package hexlet.code;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        Map<String, String> fromFile1 = Parser.parse(file1);
        Map<String, String> fromFile2 = Parser.parse(file2);
        return difference(fromFile1, fromFile2);
    }

    private static String difference(Map<String, String> content1, Map<String, String> content2) {
        Set<String> params = new TreeSet<>();
        params.addAll(content1.keySet());
        params.addAll(content2.keySet());
        List<Map<String, Object>> differences = new ArrayList<>();
        params.forEach(p -> {
            Map<String, Object> pair = new HashMap<>();
            if (content1.containsKey(p) && content2.containsKey(p)) {
                if (content1.get(p).equals(content2.get(p))) {
                    pair.put("key", p);
                    pair.put("value", content1.get(p));
                    pair.put("differenceType", "unchanged");
                } else {
                    pair.put("key", p);
                    pair.put("oldValue", content1.get(p));
                    pair.put("newValue", content2.get(p));
                    pair.put("differenceType", "updated");
                }
                differences.add(pair);
                return;
            }
            if (content1.containsKey(p)) {
                pair.put("key", p);
                pair.put("value", content1.get(p));
                pair.put("differenceType", "removed");
                differences.add(pair);
                return;
            }
            if (content2.containsKey(p)) {
                pair.put("key", p);
                pair.put("value", content2.get(p));
                pair.put("differenceType", "added");
                differences.add(pair);
            }
        });
        return Formatter.format(differences, "stylish");
    }
}
