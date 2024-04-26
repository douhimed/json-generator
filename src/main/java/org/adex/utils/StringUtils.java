package org.adex.utils;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.adex.utils.RandomUtils.RANDOM_CHAR;

public final class StringUtils {

    public static final String SPACE = " ";
    public static final String EMPTY = "";

    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    public static final String OPEN_CURLY_BRACKET = "{";
    public static final String CLOSE_CURLY_BRACKET = "}";
    public static final String COMMA = ",";
    public static final String COLON = ":";

    private StringUtils() {
        throw new IllegalStateException("Utility Class");
    }

    public static boolean isBlank(final CharSequence cs) {
        return Optional.ofNullable(cs).map(CharSequence::isEmpty).orElse(false);
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    public static String generate(int min, int max) {
        if (min > max || min < 0)
            throw new IllegalArgumentException("Min and Max must be greater than or equal to zero");

        return IntStream.range(0, RandomUtils.RANDOM_NUM.apply(min, max))
                .mapToObj($ -> RANDOM_CHAR.get())
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public static String generate(int min, int max, boolean upperCase) {
        final String value = generate(min, max);
        return upperCase ? value.toUpperCase() : value.toLowerCase();
    }

}
