package org.adex.services;

import org.adex.inputs.ObjectMetaData;
import org.adex.inputs.ObjectType;
import org.adex.utils.RandomUtils;

import java.util.*;

import static org.adex.utils.StringUtils.*;

public final class JsonGenerator {

    private JsonGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static String generate(ObjectMetaData obj) {
        List<String> lines = new ArrayList<>();
        generate(obj, lines, true);
        return OPEN_CURLY_BRACKET + String.join(COMMA, lines) + CLOSE_CURLY_BRACKET;
    }

    private static void generate(ObjectMetaData obj, List<String> lines, boolean addColumnName) {
        if (ObjectType.isLeaf(obj.getType())) {
            final String line = obj.build(addColumnName);
            lines.add(line);
        } else {
            final boolean isArray = ObjectType.isArray(obj.getType());
            List<String> childLines = new ArrayList<>();
            for (ObjectMetaData child : obj.getChildren()) {
                generate(child, childLines, !isArray);
            }
            if (isArray) {
                final Integer randomSize = RandomUtils.RANDOM_NUM.apply(obj.getMin(), obj.getMin());
                final String repeatedChildLine = OPEN_BRACKET + String.join(COMMA, Collections.nCopies(randomSize, childLines.getFirst())) + CLOSE_BRACKET;
                lines.add(obj.getName() + repeatedChildLine);
            } else {
                lines.add(new StringBuilder(addColumnName ? obj.getName() : EMPTY)
                        .append(OPEN_CURLY_BRACKET)
                        .append(String.join(COMMA, childLines))
                        .append(CLOSE_CURLY_BRACKET).toString());
            }
        }
    }

}
