package org.adex.utils;

public final class NumberUtils {

    private NumberUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Number generate(int min, int max) {
        if(min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return RandomUtils.RANDOM_NUM.applyAsInt(min, max);
    }

    public static Number generate() {
        return generate(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

}
