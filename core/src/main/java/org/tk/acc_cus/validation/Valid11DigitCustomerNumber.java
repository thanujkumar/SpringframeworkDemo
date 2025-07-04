package org.tk.acc_cus.validation;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

//https://www.skatteetaten.no/en/person/national-registry/birth-and-name-selection/children-born-in-norway/national-id-number/
//https://www.progsbase.com/algowidgets/norwegian-personal-identification-number-online/

// Downloaded from https://repo.progsbase.com - Code Developed Using progsbase and modified to my needs.

public class Valid11DigitCustomerNumber {


    public static void main(String[] args) throws IOException {
        System.out.println(IsValidNorwegianPersonalIdentificationNumber("10061270707".toCharArray()));

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
                valid = IsValidNorwegianPersonalIdentificationNumber(pad11Digit.toCharArray());
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

    public static String padTo11DigitString(String acctNo) {
        return StringUtils.leftPad(acctNo, 11, "0");
    }

    public static boolean IsValidNorwegianPersonalIdentificationNumber(char[] fnummer) {
        boolean gyldig;
        double d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11;
        datetimeDate aDatetimeDate;
        double k1, k2;
        NumberReference failures;
        StringReference errorMessage;

        errorMessage = CreateStringReference("".toCharArray());

        if (fnummer.length == 11d) {
            failures = CreateNumberReference(0d);

            d1 = CharToNumberWithCheck(fnummer[0], failures);
            d2 = CharToNumberWithCheck(fnummer[1], failures);
            d3 = CharToNumberWithCheck(fnummer[2], failures);
            d4 = CharToNumberWithCheck(fnummer[3], failures);
            d5 = CharToNumberWithCheck(fnummer[4], failures);
            d6 = CharToNumberWithCheck(fnummer[5], failures);
            d7 = CharToNumberWithCheck(fnummer[6], failures);
            d8 = CharToNumberWithCheck(fnummer[7], failures);
            d9 = CharToNumberWithCheck(fnummer[8], failures);
            d10 = CharToNumberWithCheck(fnummer[9], failures);
            d11 = CharToNumberWithCheck(fnummer[10], failures);

            if (failures.numberValue == 0d) {
                aDatetimeDate = GetDateFromNorwegianPersonalIdentificationNumber(fnummer, failures);

                if (datetimeIsValidDate(aDatetimeDate, errorMessage)) {
                    if (failures.numberValue == 0d) {
                        k1 = d1 * 3d + d2 * 7d + d3 * 6d + d4 * 1d + d5 * 8d + d6 * 9d + d7 * 4d + d8 * 5d + d9 * 2d;
                        k1 = k1 % 11d;
                        if (k1 != 0d) {
                            k1 = 11d - k1;
                        }
                        if (k1 == 10d) {
                            failures.numberValue = failures.numberValue + 1d;
                        }

                        k2 = d1 * 5d + d2 * 4d + d3 * 3d + d4 * 2d + d5 * 7d + d6 * 6d + d7 * 5d + d8 * 4d + d9 * 3d + k1 * 2d;
                        k2 = k2 % 11d;
                        if (k2 != 0d) {
                            k2 = 11d - k2;
                        }
                        if (k2 == 10d) {
                            failures.numberValue = failures.numberValue + 1d;
                        }

                        if (k1 == d10 && k2 == d11 && failures.numberValue == 0d) {
                            gyldig = true;
                        } else {
                            gyldig = false;
                        }
                    } else {
                        gyldig = false;
                    }
                } else {
                    gyldig = false;
                }
            } else {
                gyldig = false;
            }
        } else {
            gyldig = false;
        }

        return gyldig;
    }

    public static datetimeDate GetDateFromNorwegianPersonalIdentificationNumber(char[] fnummer, NumberReference failures) {
        datetimeDate aDatetimeDate;
        double individnummer;
        double day, month, year;
        double d1, d2, d3, d4, d5, d6, d7, d8, d9;

        aDatetimeDate = new datetimeDate();

        d1 = CharToNumberWithCheck(fnummer[0], failures);
        d2 = CharToNumberWithCheck(fnummer[1], failures);
        d3 = CharToNumberWithCheck(fnummer[2], failures);
        d4 = CharToNumberWithCheck(fnummer[3], failures);
        d5 = CharToNumberWithCheck(fnummer[4], failures);
        d6 = CharToNumberWithCheck(fnummer[5], failures);
        d7 = CharToNumberWithCheck(fnummer[6], failures);
        d8 = CharToNumberWithCheck(fnummer[7], failures);
        d9 = CharToNumberWithCheck(fnummer[8], failures);

        if (failures.numberValue == 0d) {
            /* Individnummer*/
            individnummer = d7 * 100d + d8 * 10d + d9;

            /* Make date*/
            day = d1 * 10d + d2;
            month = d3 * 10d + d4;
            year = d5 * 10d + d6;

            if (individnummer >= 0d && individnummer <= 499d) {
                year = year + 1900d;
            } else if (individnummer >= 500d && individnummer <= 749d && year >= 54d && year <= 99d) {
                year = year + 1800d;
            } else if (individnummer >= 900d && individnummer <= 999d && year >= 40d && year <= 99d) {
                year = year + 1900d;
            } else if (individnummer >= 500d && individnummer <= 999d && year >= 0d && year <= 39d) {
                year = year + 1900d;
            } else {
                failures.numberValue = failures.numberValue + 1d;
            }

            aDatetimeDate.year = year;
            aDatetimeDate.month = month;
            aDatetimeDate.day = day;
        }

        return aDatetimeDate;
    }

    public static double CharToNumberWithCheck(char c, NumberReference failures) {
        double val;

        if (charIsNumber(c)) {
            val = nGetNumberFromNumberCharacterForBase(c, 10d);
        } else {
            val = 0d;
            failures.numberValue = failures.numberValue + 1d;
        }

        return val;
    }


    static public class NumberReference {
        public double numberValue;
    }


    static public class StringReference {
        public char[] string;
    }


    public static NumberReference CreateNumberReference(double value) {
        NumberReference ref;
        ref = new NumberReference();
        ref.numberValue = value;

        return ref;
    }


    public static StringReference CreateStringReference(char[] value) {
        StringReference ref;
        ref = new StringReference();
        ref.string = value;

        return ref;
    }


    public static char[] nGetDigitCharacterTable() {
        char[] numberTable;
        numberTable = new char[36];

        numberTable[0] = '0';
        numberTable[1] = '1';
        numberTable[2] = '2';
        numberTable[3] = '3';
        numberTable[4] = '4';
        numberTable[5] = '5';
        numberTable[6] = '6';
        numberTable[7] = '7';
        numberTable[8] = '8';
        numberTable[9] = '9';
        numberTable[10] = 'A';
        numberTable[11] = 'B';
        numberTable[12] = 'C';
        numberTable[13] = 'D';
        numberTable[14] = 'E';
        numberTable[15] = 'F';
        numberTable[16] = 'G';
        numberTable[17] = 'H';
        numberTable[18] = 'I';
        numberTable[19] = 'J';
        numberTable[20] = 'K';
        numberTable[21] = 'L';
        numberTable[22] = 'M';
        numberTable[23] = 'N';
        numberTable[24] = 'O';
        numberTable[25] = 'P';
        numberTable[26] = 'Q';
        numberTable[27] = 'R';
        numberTable[28] = 'S';
        numberTable[29] = 'T';
        numberTable[30] = 'U';
        numberTable[31] = 'V';
        numberTable[32] = 'W';
        numberTable[33] = 'X';
        numberTable[34] = 'Y';
        numberTable[35] = 'Z';

        return numberTable;
    }

    public static double nGetNumberFromNumberCharacterForBase(char c, double base) {
        char[] numberTable;
        double i;
        double position;
        numberTable = nGetDigitCharacterTable();
        position = 0d;

        for (i = 0d; i < base; i = i + 1d) {
            if (numberTable[(int) (i)] == c) {
                position = i;
            }
        }

        return position;
    }

    static public class datetimeDate {
        public double year;
        public double month;
        public double day;
    }


    public static boolean datetimeIsLeapYear(double year) {
        boolean itIsLeapYear;
        if (DivisibleBy(year, 4d)) {
            if (DivisibleBy(year, 100d)) {
                if (DivisibleBy(year, 400d)) {
                    itIsLeapYear = true;
                } else {
                    itIsLeapYear = false;
                }
            } else {
                itIsLeapYear = true;
            }
        } else {
            itIsLeapYear = false;
        }

        return itIsLeapYear;
    }


    public static double[] datetimeGetDaysInMonth(double year) {
        double[] daysInMonth;
        daysInMonth = new double[(int) (1d + 12d)];

        daysInMonth[0] = 0d;
        daysInMonth[1] = 31d;

        if (datetimeIsLeapYear(year)) {
            daysInMonth[2] = 29d;
        } else {
            daysInMonth[2] = 28d;
        }
        daysInMonth[3] = 31d;
        daysInMonth[4] = 30d;
        daysInMonth[5] = 31d;
        daysInMonth[6] = 30d;
        daysInMonth[7] = 31d;
        daysInMonth[8] = 31d;
        daysInMonth[9] = 30d;
        daysInMonth[10] = 31d;
        daysInMonth[11] = 30d;
        daysInMonth[12] = 31d;

        return daysInMonth;
    }

    public static boolean datetimeIsValidDate(datetimeDate date, StringReference errorMessage) {
        boolean valid;
        double[] daysInMonth;
        double daysInThisMonth;
        if (date.year >= 1752d) {
            if (date.month >= 1d && date.month <= 12d) {
                daysInMonth = datetimeGetDaysInMonth(date.year);
                daysInThisMonth = daysInMonth[(int) (date.month)];
                if (date.day >= 1d && date.day <= daysInThisMonth) {
                    valid = true;
                } else {
                    valid = false;
                    errorMessage.string = "The month does not have the given day number.".toCharArray();
                }
            } else {
                valid = false;
                errorMessage.string = "Month must be between 1 and 12, inclusive.".toCharArray();
            }
        } else {
            valid = false;
            errorMessage.string = "Gregorian calendar was not in general use before 1752.".toCharArray();
        }

        return valid;
    }


    public static boolean DivisibleBy(double a, double b) {
        return ((a % b) == 0d);
    }


    public static boolean charIsNumber(char character) {
        boolean isNumber;
        isNumber = false;
        if (character == '0') {
            isNumber = true;
        } else if (character == '1') {
            isNumber = true;
        } else if (character == '2') {
            isNumber = true;
        } else if (character == '3') {
            isNumber = true;
        } else if (character == '4') {
            isNumber = true;
        } else if (character == '5') {
            isNumber = true;
        } else if (character == '6') {
            isNumber = true;
        } else if (character == '7') {
            isNumber = true;
        } else if (character == '8') {
            isNumber = true;
        } else if (character == '9') {
            isNumber = true;
        }

        return isNumber;
    }


}



