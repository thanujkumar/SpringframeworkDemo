package org.tk.acc_cus.validation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class VerifyDateRangeIterator {

    static SimpleDateFormat formattedDate = new SimpleDateFormat("ddMMyy");

    public static void main(String[] args) {
        LocalDate januaryFirst = LocalDate.parse("2014-01-01");//yyyy-MM-dd
        LocalDate januarySecond = januaryFirst.plusDays(1);
        System.out.println(formattedDate.format(Date.from(januaryFirst.atStartOfDay().toInstant(ZoneOffset.UTC))));
        //System.out.println(formattedDate.format(januarySecond));

        DateRangeIterator iterator = new DateRangeIterator(LocalDate.parse("1950-01-01"), LocalDate.parse("1951-12-31"));

        iterator.forEachRemaining(x -> {
            System.out.println(x +" -> " + iterator.getddMMyyFormat());
        });
    }


}
