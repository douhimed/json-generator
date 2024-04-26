package org.adex.utils;

import java.util.function.Supplier;

public final class BooleanUtils {

    public static final Supplier<Boolean> GENERATE = () -> Math.random() > .5;

    private BooleanUtils() {
        throw new IllegalStateException("Utility class");
    }

}
