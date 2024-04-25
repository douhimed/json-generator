package org.adex.utils;

public final class BooleanUtils {

    private BooleanUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean generate() {
        return RandomUtils.RANDOM_NUM.apply(0, 100) % 2 == 0;
    }
}
