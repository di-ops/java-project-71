package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class TreeDifference {

    public static List<Map<String, Object>> build(Map<String, Object> content1, Map<String, Object> content2) {
        Set<Object> params = new TreeSet<>();
        params.addAll(content1.keySet());
        params.addAll(content2.keySet());
        List<Map<String, Object>> differences = new ArrayList<>();
        params.forEach(p -> {
            Map<String, Object> pair = new HashMap<>();
            if (content1.containsKey(p) && content2.containsKey(p)) {
                if (Objects.equals(content1.get(p), content2.get(p))) {
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
        return differences;
    }

}
