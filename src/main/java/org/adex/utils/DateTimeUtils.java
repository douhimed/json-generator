package org.adex.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public final class DateTimeUtils {

    public static final Supplier<LocalDate> GET_PAST_DATE = () -> LocalDate.now().minusDays(RandomUtils.RANDOM.nextInt(365) + 1L);
    public static final Supplier<LocalDate> GET_FUTURE_DATE = () -> LocalDate.now().plusDays(RandomUtils.RANDOM.nextInt(365) + 1L);

    private DateTimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalTime generateTime() {
        final ThreadLocalRandom current = ThreadLocalRandom.current();
        return LocalTime.of(current.nextInt(0, 24),
                current.nextInt(0, 60),
                current.nextInt(0, 60));
    }

}
