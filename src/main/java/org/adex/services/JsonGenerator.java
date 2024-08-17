package org.adex.services;

import org.adex.inputs.ObjectMetaData;
import org.adex.inputs.ObjectType;
import org.adex.utils.RandomUtils;

import java.util.*;

import static org.adex.utils.CollectionUtils.*;
import static org.adex.utils.StringUtils.*;

public final class JsonGenerator {

    private JsonGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static String generate(ObjectMetaData obj) {
        List<String> lines = new ArrayList<>();
        generate(obj, lines, true);
        return join(lines, COMMA, OPEN_CURLY_BRACKET, CLOSE_CURLY_BRACKET);
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
                final int randomSize = RandomUtils.RANDOM_NUM.applyAsInt(obj.getMin(), obj.getMin());
                final List<String> elements = Collections.nCopies(randomSize, childLines.getFirst());
                final String repeatedChildLine = join(elements, COMMA, OPEN_BRACKET, CLOSE_BRACKET);
                lines.add(obj.getName() + repeatedChildLine);
            } else {
                lines.add((addColumnName ? obj.getName() : EMPTY) +
                        join(childLines, COMMA, OPEN_CURLY_BRACKET, CLOSE_CURLY_BRACKET));
            }
        }
    }

}
