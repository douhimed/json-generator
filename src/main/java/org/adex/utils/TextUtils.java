package org.adex.utils;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class TextUtils {

    private static final String LOREM = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
            Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.""".replaceAll("\\s+", StringUtils.SPACE);

    private static final String[] LOREM_WORDS = LOREM.split(StringUtils.SPACE);

    private TextUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String generate(int min, int max) {
        if (max <= 2)
            throw new IllegalArgumentException("Max must be greater than or equal to two");

        return IntStream.range(0, Math.min(max, LOREM_WORDS.length))
                .mapToObj(i -> LOREM_WORDS[RandomUtils.RANDOM_NUM.applyAsInt(0, LOREM_WORDS.length - 1)].stripIndent())
                .collect(Collectors.joining(StringUtils.SPACE));
    }

    public static String generate(int min, int max, boolean upperCase) {
        final String text = generate(min, max);
        return upperCase ? text.toUpperCase() : text;
    }

    public static String generate(int max) {
        return generate(0, max);
    }

    public static String generate(int max, boolean upperCase) {
        final String text = generate(0, max);
        return upperCase ? text.toUpperCase() : text;
    }

}
