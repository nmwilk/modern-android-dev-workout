package com.nmwilkinson.rxjavaworkout.model;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by neil on 10/04/16.
 */
public class DateRange {
    @SuppressLint("SimpleDateFormat") // for use with API - not UI.
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final int ONE_WEEK_MS = (7 * 24 * 60 * 60 * 1000);

    private final String startDate;
    private final String endDate;

    private DateRange(final Date from, final Date to) {
        this.startDate = DATE_FORMAT.format(from);
        this.endDate = DATE_FORMAT.format(to);
    }

    public static DateRange lastWeek() {
        final Date to = new Date();
        final Date from = new Date(to.getTime() - ONE_WEEK_MS);
        return new DateRange(from, to);
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
