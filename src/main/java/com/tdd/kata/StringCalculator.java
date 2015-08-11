package com.tdd.kata;

import java.util.regex.Pattern;

/**
 * Created by smestry on 11/08/2015.
 */
public final class StringCalculator {

    private StringCalculator() {}

    //Refactoring comma and newline as the default delimiters
    private final static String DEFAULT_DELIMITERS = ",|\n";

    private final static String[] REGEX_LITERALS = new String[] {"?","*"};

    public static Long add(final String numbers) throws RuntimeException {
        if (!isValidInput(numbers)) throw new RuntimeException("Please input valid String of numbers");

        if (numbers.isEmpty())   return 0L;

        // Extracting the delimiter from the input string
        String delimiter = extractDelimiter(numbers);

        // Cleanse the input numbers
        String numbersString = parseInputString(numbers);

        String[] numberArr = numbersString.split(delimiter);

        // Refactored the method and created a separate method for total calculation.
        return calculateTotal(numberArr);
    }

    private static Long calculateTotal(final String[] numberArr) {
        if (numberArr.length == 0) {
            if (isStringAValidNumber(numberArr[0])) return Long.valueOf(numberArr[0]);
            else throw new RuntimeException("Please input valid String of numbers");
        } else {
            Long sum = 0L;
            for (String n : numberArr) {
                if (isStringAValidNumber(n)) sum = sum + Long.valueOf(n.trim());
                else throw new RuntimeException("Please input valid String of numbers");
            }
            return sum;
        }
    }

    private static String parseInputString(final String numbersWithDelimiterString) {
        if (!numbersWithDelimiterString.startsWith("//")) return numbersWithDelimiterString;
        else return numbersWithDelimiterString.substring(numbersWithDelimiterString.indexOf("\n") + 1);
    }

    private static String extractDelimiter(final String numbersWithDelimiterString) {
        if (!numbersWithDelimiterString.startsWith("//")) return DEFAULT_DELIMITERS;
        else return parseDelimiter(numbersWithDelimiterString);
    }

    private static String parseDelimiter(String tempStr) {
        for (String r : REGEX_LITERALS) {
            if (tempStr.contains(r)) tempStr = tempStr.replace(r, "\\" + r);
        }
       return  (DEFAULT_DELIMITERS + "|" + (tempStr.substring(
                tempStr.indexOf("[") + 1, tempStr.indexOf("]"))));
    }

    private static boolean isValidInput(final String numbers) {
        if (numbers == null) return false;
        return true;
    }

    private static boolean isStringAValidNumber(final String number) {
        Boolean isValid = null;
        try {
            Long.parseLong(number.trim());
            isValid = true;
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }
}
