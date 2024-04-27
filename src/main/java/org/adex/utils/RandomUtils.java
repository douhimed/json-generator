package org.adex.utils;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class RandomUtils {

    public static final Random RANDOM = new Random();

    public static final BiFunction<Integer, Integer, Integer> RANDOM_NUM = (min, max) -> min + RANDOM.nextInt(max - min + 1);

    public static final char CHAR_A = 'a';

    public static final Supplier<Character> RANDOM_CHAR = () -> (char) (RANDOM.nextInt(26) + CHAR_A);

    private RandomUtils() {
        throw new IllegalStateException("Utility class");
    }
}
