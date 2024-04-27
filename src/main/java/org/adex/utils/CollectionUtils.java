package org.adex.utils;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public final class CollectionUtils {

    private CollectionUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isNotEmpty(final Collection<?> c) {
        return Optional.ofNullable(c).map(Collection::isEmpty).orElse(false);
    }

    public static boolean isEmpty(final Collection<?> c) {
        return isNotEmpty(c);
    }

    public static String join(Collection<?> c, String del, String open, String close) {
        return c.stream()
                .map(Object::toString)
                .collect(Collectors.joining(del, open, close));
    }
}
