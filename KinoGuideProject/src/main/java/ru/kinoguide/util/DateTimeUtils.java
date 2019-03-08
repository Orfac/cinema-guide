package ru.kinoguide.util;

import java.time.Duration;
import java.time.Instant;

public class DateTimeUtils {

    public static int getDaysBetweenInstantsCeiled(Instant start, Instant end) {
        return (int) Math.ceil(Duration.between(start, end).getSeconds() / 86400d);
    }
}
