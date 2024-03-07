package com.capgemini.framework.utils;

import java.time.Duration;

public class DurationUtils {
    private DurationUtils() {
    }

    public static String getReadableDuration(Duration d) {
        var days = d.toDays();
        d = d.minusDays(days);
        var hours = d.toHours();
        d = d.minusHours(hours);
        var minutes = d.toMinutes();
        d = d.minusMinutes(minutes);
        var seconds = d.getSeconds();
        d = d.minusSeconds(seconds);
        var millis = d.toMillis();

        var result = ((days == 0 ? "" : days + "d ") + (hours == 0 ? "" : hours + "h ") + (minutes == 0 ? "" : minutes + "m ") + (seconds == 0 ? "" : seconds + "s ") + (millis == 0 ? "" : millis + "ms")).trim();
        if (result.isEmpty()) {
            return "0ms";
        }
        return result;
    }
}