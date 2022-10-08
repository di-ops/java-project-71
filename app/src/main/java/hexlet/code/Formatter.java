package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, Object>> differences, String format) {
        return switch (format) {
            case "stylish" -> formatStylish(differences);
            case "plain" -> formatPlain(differences);
            default -> throw new RuntimeException("Unknown output format: " + format);
        };
    }

    private static String formatStylish(List<Map<String, Object>> differences) {
        StringBuilder diff = new StringBuilder();
        diff.append("{\n");
        differences.forEach(e -> {
            String differenceType = e.get("differenceType").toString();
            switch (differenceType) {
                case "unchanged" -> diff.append(String.format("    %s: %s%n", e.get("key"), e.get("value")));
                case "updated" -> {
                    diff.append(String.format("  - %s: %s%n", e.get("key"), e.get("oldValue")));
                    diff.append(String.format("  + %s: %s%n", e.get("key"), e.get("newValue")));
                }
                case "removed" -> diff.append(String.format("  - %s: %s%n", e.get("key"), e.get("value")));
                case "added" -> diff.append(String.format("  + %s: %s%n", e.get("key"), e.get("value")));
                default -> throw new RuntimeException("Intern exception. differenceType is null or unknown : "
                        + e.get("differenceType"));
            }
        });
        diff.append("}");
        return diff.toString();
    }

    private static String formatPlain(List<Map<String, Object>> differences) {
        StringBuilder diff = new StringBuilder();
        differences.forEach(e -> {
            String differenceType = e.get("differenceType").toString();
            switch (differenceType) {
                case "unchanged" -> { }
                case "updated" -> diff.append(String.format("Property '%s' was updated. From %s to %s\n",
                        e.get("key"), valueToPlainFormat(e.get("oldValue")), valueToPlainFormat(e.get("newValue"))));
                case "removed" -> diff.append(String.format("Property '%s' was removed\n", e.get("key")));
                case "added" -> diff.append(String.format("Property '%s' was added with value: %s\n",
                        e.get("key"), valueToPlainFormat(e.get("value"))));
                default -> throw new RuntimeException("Intern exception. differenceType is null or unknown : "
                        + e.get("differenceType"));
            }
        });
        return diff.toString().trim();
    }

    private static String valueToPlainFormat(Object o) {
        if (o instanceof String) {
            return String.format("'%s'", o);
        }
        if (o instanceof List || o instanceof Map) {
            return "[complex value]";
        }
        if (o == null) {
            return "null";
        }
        return o.toString();
    }
}
