package org.adex.utils;

import java.util.function.BooleanSupplier;

public final class BooleanUtils {

    public static final BooleanSupplier GENERATE = () -> Math.random() > .5;

    private BooleanUtils() {
        throw new IllegalStateException("Utility class");
    }

}
