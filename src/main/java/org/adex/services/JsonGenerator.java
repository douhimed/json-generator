package org.adex.services;

import java.util.*;

public final class JsonGenerator {

    private JsonGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static String generate(ObjectMetaData obj) {
        List<String> lines = new ArrayList<>();
        generate(obj, lines);
        return "{" + String.join(", ", lines) + "}";
    }

    private static void generate(ObjectMetaData obj, List<String> lines) {
        if (obj.isLeaf()) {
            lines.add(obj.getName() + ": " + obj.generateValue());
        } else {
            List<String> childLines = new ArrayList<>();
            for (ObjectMetaData child : obj.getChildren()) {
                generate(child, childLines);
            }
            lines.add(obj.getName() + ": {" + String.join(", ", childLines) + "}");
        }
    }

}
