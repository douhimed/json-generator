package org.adex.utils;

import org.adex.services.ObjectMetaData;

import java.util.Collections;
import java.util.stream.Collectors;

public final class ArrayUtils {

    private ArrayUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String generate(int min, int max, ObjectMetaData obj) {
        return Collections.nCopies(RandomUtils.RANDOM_NUM.apply(min, max), obj.generateValue())
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }

}
