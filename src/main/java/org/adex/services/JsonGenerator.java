package org.adex.services;

import org.adex.utils.RandomUtils;

import java.util.*;
import java.util.stream.Collectors;

public final class JsonGenerator {

    private JsonGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static String generate(ObjectMetaData obj) {
        List<String> lines = new ArrayList<>();
        generate(obj, lines, true);
        return "{" + String.join(", ", lines) + "}";
    }

    private static void generate(ObjectMetaData obj, List<String> lines, boolean addColumnName) {
        if (ObjectType.isLeaf(obj.getType())) {
            final String value = Objects.requireNonNull(obj.generateValue()).toString();
            final String line = addColumnName ? obj.getName() + ": " + value : value;
            lines.add(line);
        } else {
            final boolean isArray = ObjectType.isArray(obj.getType());
            List<String> childLines = new ArrayList<>();
            for (ObjectMetaData child : obj.getChildren()) {
                generate(child, childLines, !isArray);
            }
            if (isArray) {
                lines.add(obj.getName() + ": " + Collections.nCopies(RandomUtils.RANDOM_NUM.apply(obj.getMin(), obj.getMin()), childLines.getFirst())
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(",", "[", "]")));
            } else {
                lines.add(obj.getName() +
                        ": {" +
                            String.join(", ", childLines) +
                        "}");
            }
        }
    }

}
