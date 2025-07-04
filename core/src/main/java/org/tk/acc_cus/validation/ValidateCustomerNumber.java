package org.tk.acc_cus.validation;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//https://www.progsbase.com/algowidgets/norwegian-personal-identification-number-online/
//https://www.skatteetaten.no/en/person/national-registry/birth-and-name-selection/children-born-in-norway/national-id-number/

public class ValidateCustomerNumber {

    // Int constant for magic number 0
    private static final int I0 = 0;
    // Int constant for magic number 1
    private static final int I1 = 1;
    // Int constant for magic number 2
    private static final int I2 = 2;
    // Int constant for magic number 3
    private static final int I3 = 3;
    // Int constant for magic number 4
    private static final int I4 = 4;
    // Int constant for magic number 5
    private static final int I5 = 5;
    // Int constant for magic number 5
    private static final int I6 = 6;
    // Int constant for magic number 6
    private static final int I7 = 7;
    private static final int I11 = 11;
    private static final int I17 = 17;
    private static final int I18 = 18;


    private static final int[] MODULO11 = {ValidateCustomerNumber.I6, ValidateCustomerNumber.I5, ValidateCustomerNumber.I4,
            ValidateCustomerNumber.I3, ValidateCustomerNumber.I2, ValidateCustomerNumber.I7,
            ValidateCustomerNumber.I6, ValidateCustomerNumber.I5, ValidateCustomerNumber.I4,
            ValidateCustomerNumber.I3, ValidateCustomerNumber.I2,
            ValidateCustomerNumber.I7, ValidateCustomerNumber.I6, ValidateCustomerNumber.I5,
            ValidateCustomerNumber.I4, ValidateCustomerNumber.I3, ValidateCustomerNumber.I2};

    public boolean isValidCustomerNumbers(final List<String> customerNumber) {
        for (String custNo : customerNumber) {
            if (!(validateUsingModel11(getWorking18DigitString(custNo)))) {
                return false;
            }
        }
        return true;
    }

    private String getWorking18DigitString(final String cdvNumber) {
        if (!StringUtils.isNumeric(cdvNumber)) {
            throw new RuntimeException("Not a valid account number");
        }
        return StringUtils.leftPad(cdvNumber, ValidateCustomerNumber.I18, "0");
    }

    private boolean validateUsingModel11(final String cdvNumber) {
        Integer checkSum = 0, checkDigit = 0;
        for (int i = 0; i < ValidateCustomerNumber.I17; i++) {
            checkSum += ValidateCustomerNumber.MODULO11[i] * Integer.parseInt(cdvNumber.substring(i, ValidateCustomerNumber.I1 + i));
        }
        if ((checkSum % ValidateCustomerNumber.I11) == ValidateCustomerNumber.I1) {
            return false;
        } else {
            if (!((checkSum % ValidateCustomerNumber.I11) == ValidateCustomerNumber.I0)) {
                checkDigit = ValidateCustomerNumber.I11 - (checkSum % ValidateCustomerNumber.I11);
            }
        }
        return cdvNumber.substring(ValidateCustomerNumber.I17).equals(checkDigit.toString());
    }

    public static void main(String[] args) throws IOException {

        //TODO - This is not generating valid numbers

        ValidateCustomerNumber validateCustomerNumber = new ValidateCustomerNumber();
        //first 18 digit conversion from 11 digit and then validate
        System.out.println(validateCustomerNumber.validateUsingModel11(validateCustomerNumber.getWorking18DigitString("01129999981")));

        Path out = Paths.get("Customer_01015000000.csv");//from 01(day)-01(month)-1950(year)
        final PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(out));

        DateRangeIterator dateRangeIterator = new DateRangeIterator(LocalDate.parse("1950-01-01"), LocalDate.parse("1950-12-31"));
        final AtomicLong counter = new AtomicLong(0);

        dateRangeIterator.forEachRemaining(date -> {
            //date is 6 digits (ddMMyy) and we iterate next 5 digits from 0 to 99999 with left padding
            System.out.println("Generating valid customer number for date (yyyy-MM-dd) - " + date);
            String pad11Digit = "";
            boolean valid = false;
            for (int i = 0; i < 99999; i++) {
                pad11Digit = dateRangeIterator.getddMMyyFormat() + StringUtils.leftPad("" + i, 5, '0');
                valid = validateCustomerNumber.validateUsingModel11(validateCustomerNumber.getWorking18DigitString((pad11Digit)));
                if (valid) {
                    counter.incrementAndGet();
                    printWriter.println(pad11Digit);
                    System.out.println( date + " = " + pad11Digit + " -- " + valid);
                }
            }
        });


        printWriter.close();
        System.out.println("Valid Customer Numbers: " + counter.get());
    }
}
