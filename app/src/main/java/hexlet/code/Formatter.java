package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format (List<Map<String, Object>> difference, String format){
         return switch (format){
            case "stylish" -> formatStylish(difference);
            case "plain" -> "";
            default -> throw new RuntimeException("Unknown output format: " + format);
        };
    }

    private static String formatStylish (List<Map<String, Object>> differences){
        StringBuilder diff = new StringBuilder();
        diff.append("{\n");
        differences.forEach(e -> {
            String differenceType = e.get("differenceType").toString();
            switch (differenceType){
                case "unchanged" -> diff.append(String.format("    %s: %s%n", e.get("key"), e.get("value")));
                case "updated" -> {
                    diff.append(String.format("  - %s: %s%n", e.get("key"), e.get("oldValue")));
                    diff.append(String.format("  + %s: %s%n", e.get("key"), e.get("newValue")));
                }
                case "removed" -> diff.append(String.format("  - %s: %s%n", e.get("key"), e.get("value")));
                case "added" -> diff.append(String.format("  + %s: %s%n", e.get("key"), e.get("value")));
                default -> throw new RuntimeException("Intern exception. differenceType is null or unknown : " + e.get("differenceType"));
            }
        });
        diff.append("}");
        return diff.toString();
    }


}
