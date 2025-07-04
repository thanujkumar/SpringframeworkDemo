package org.tk.acc_cus.validation;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
//https://www.ecbs.org/iban/norway-bank-account-number.html

/**
 * HISTORY LESSON
 * The Norwegian account number system was introduced in 1967 for automatic data processing of checks and other documents.
 * Norwegian Bankers’ Association and the Savings Banks Association in Norway decided on using an eleven-digit number for account representation.
 * <p>
 * FORMAT
 * Digits of an account number are grouped into three groups, usually with a small distance or a dot between each group.
 * It is not uncommon to leave these out.
 * <p>
 * Using dots the format looks like this:
 * <p>
 * XXXX.YY.ZZZZC
 * <p>
 * Where XXXX indicates the bank register number and identifies both the bank and branch.
 * <p>
 * YY indicates the account type. Earlier, banks used these digits for identifying account types and it was relatively standardized.
 * As an example, 10 and 19 were used for payroll accounts (peoples current accounts). Nowadays the use is far less standardized,
 * some banks do not use these two digits to identify specific account types. Now, this ain’t the complete truth.
 * From what I have found out, the digits 00 are used for inter banking accounts. This is because the transactions
 * to / from such accounts is part of a special treatment of clearing systems.
 * <p>
 * ZZZZ indicates the account number itself. In most cases this is the only thing that distinguishes the accounts.
 * <p>
 * C is a check digit that is calculated on the basis of all 10 previous digits.The calculation is performed by  Mod 11 using 5432765432.
 */

public class ValidateAccountNumber {
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
    private static final int I10 = 10;
    private static final int I11 = 11;
    private static final int I17 = 17;
    // Int constant for magic number 18
    private static final int I18 = 18;

    private static final int[] MODULO11 = {ValidateAccountNumber.I6, ValidateAccountNumber.I5, ValidateAccountNumber.I4,
            ValidateAccountNumber.I3, ValidateAccountNumber.I2, ValidateAccountNumber.I7,
            ValidateAccountNumber.I6, ValidateAccountNumber.I5, ValidateAccountNumber.I4,
            ValidateAccountNumber.I3, ValidateAccountNumber.I2,
            ValidateAccountNumber.I7, ValidateAccountNumber.I6, ValidateAccountNumber.I5,
            ValidateAccountNumber.I4, ValidateAccountNumber.I3, ValidateAccountNumber.I2};


    public boolean isValid(final String accountNumber) {
        boolean result = false;
        if ((accountNumber.startsWith("0000") && !isZeroString(accountNumber))
                || accountNumber.startsWith("0500") || accountNumber.startsWith("0800")) {
            result = validateUsingModel10(getWorking18DigitString(removeOrgId(accountNumber)));
        } else if (!accountNumber.startsWith("05") && accountNumber.substring(ValidateAccountNumber.I4, ValidateAccountNumber.I6).equals("00")) {
            result = validateUsingModel11(getWorking18DigitString(removeOrgId(accountNumber)));
        } else {
            result = validateUsingModel11(getWorking18DigitString(accountNumber));
        }
        return result;
    }

    protected boolean validateUsingModel10(final String cdvNumber) {
        Integer checkSum = 0;
        for (int i = 0; i < ValidateAccountNumber.I17; i++) {
            if ((i % 2) == 0) {
                checkSum += ValidateAccountNumber.I2 * Integer.parseInt(cdvNumber.substring(i, ValidateAccountNumber.I1 + i));
            } else {
                checkSum += Integer.parseInt(cdvNumber.substring(i, ValidateAccountNumber.I1 + i));
            }
        }
        Integer checkDigit = ValidateAccountNumber.I10 - (checkSum % ValidateAccountNumber.I10);
        return cdvNumber.substring(ValidateAccountNumber.I17).equals(checkDigit.toString());
    }

    protected String removeOrgId(final String accountNumber) {
        return accountNumber.substring(4);
    }

    public boolean isValidAccountNumbers(final List<String> accountNumbers) {
        for (String accnNumber : accountNumbers) {
            if (!(isValid(accnNumber))) {
                return false;
            }
        }
        return true;
    }

    protected String getWorking18DigitString(final String cdvNumber) {
        if (!StringUtils.isNumeric(cdvNumber)) {
            throw new RuntimeException("Not a valid account number");
        }
        return StringUtils.leftPad(cdvNumber, ValidateAccountNumber.I18, "0");
    }

    protected boolean validateUsingModel11(final String cdvNumber) {
        Integer checkSum = 0, checkDigit = 0;
        for (int i = 0; i < ValidateAccountNumber.I17; i++) {
            checkSum += ValidateAccountNumber.MODULO11[i] * Integer.valueOf(cdvNumber.substring(i, ValidateAccountNumber.I1 + i));
        }
        if ((checkSum % ValidateAccountNumber.I11) == ValidateAccountNumber.I1) {
            return false;
        } else {
            if (!((checkSum % ValidateAccountNumber.I11) == ValidateAccountNumber.I0)) {
                checkDigit = ValidateAccountNumber.I11 - (checkSum % ValidateAccountNumber.I11);
            }
        }
        return cdvNumber.substring(ValidateAccountNumber.I17).equals(checkDigit.toString());
    }

    private static boolean isZeroString(final String stringToCheck) {
        return StringUtils.isNotBlank(stringToCheck) && StringUtils.isEmpty(stringToCheck.replace("0", ""));
    }

    public static void main(String[] args) throws IOException {
        Path out = Paths.get("Accounts_21257300000.csv");
        PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(out));
        ValidateAccountNumber validateAccountNumber = new ValidateAccountNumber();
        //long l = 21257300000l;
        long l = 40687950000l;
        boolean valid = false;
        int count = 0;
        for (long x = l; x < 40687999999l; x++) {
            valid = validateAccountNumber.isValid("" + x);
            if (valid) {
                count++;
                printWriter.println(x);
            }
            System.out.println(x + "=" + valid);
        }

        printWriter.close();
        System.out.println("Valid Numbers: "+ count);
    }
}

