package org.tk.acc_cus.validation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Iterator;

public class DateRangeIterator implements Iterator {

    private LocalDate fromDate, toDate;
    private final SimpleDateFormat formattedDate = new SimpleDateFormat("ddMMyy");

    /**
     * format yyyy-MM-dd.
     * <p>
     * Like 1950-01-01 to 1950-12-31
     * @param from - from date
     * @param  to  - to date
     */
    public DateRangeIterator(LocalDate from, LocalDate to) {
        fromDate = from;
        toDate = to;
    }

    @Override
    public boolean hasNext() {
        return fromDate.isBefore(toDate);
    }

    @Override
    public Object next() {
        fromDate =  fromDate.plusDays(1);
        return fromDate;
    }

    public String getddMMyyFormat() {
        return formattedDate.format(Date.from(fromDate.atStartOfDay().toInstant(ZoneOffset.UTC)));
    }
}
