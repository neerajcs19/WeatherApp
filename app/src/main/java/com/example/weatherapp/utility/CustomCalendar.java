package com.example.weatherapp.utility;

import java.time.LocalDateTime;

/**
 * Some calendar functionality, may not be requried anymore with the new LocalDateTime classes
 */
public class CustomCalendar {

    /**
     * Calculate the clock time from the UTC
     * This does not use the new LocalDateTime classes, yet
     *
     * @param utc raw UTC time
     * @return Clock time in HH:MM format
     */
    public static String calculateClockTimeFromUTC(long utc) {
        // 1s
        // 1min = 60s
        // 1h = 60min = 3600s
        // 1d = 24h = 1440min = 86400s

        long hours1 = utc % 86400;
        long hours2 = hours1 / 3600;

        long mins1 = utc % 3600;
        long mins2 = mins1 / 60;


        if(mins2 == 0) {
            return hours2+":00";
        }
        else {
            return hours2+":"+mins2;

        }
    }

    /**
     * Calculate a date from the UTC using the new LocalDateTime classes
     * For that reason alone a higher Android version is required.
     *
     * @param hours offset in hours
     * @return Current time plus the offset in hours
     */
    public static String calculateDateFromUTC(long hours) {
        LocalDateTime localDate = LocalDateTime.now();
        localDate = localDate.plusHours(hours+3);
        return localDate.getDayOfMonth() + "." + localDate.getMonthValue() + "." + localDate.getYear();
    }
}