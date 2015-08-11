package com.tdd.kata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by smestry on 11/08/2015.
 */
public final class StringCalculator {

    private StringCalculator() {}

    //Refactoring comma and newline as the default delimiters
    private final static String DEFAULT_DELIMITERS = ",|\n";

    private final static String[] REGEX_LITERALS = new String[] {"?","*","#"};

    // Check that the given number should at least be zero.
    // Numbers bigger than 1000 should be ignored.
    private final static Long MIN_LIMIT = 0L;
    private final static Long MAX_LIMIT = 1000L;

    // This is to extract multiple delimiters.
    private final static Pattern delimiterPattern = Pattern.compile("\\[(.*?)\\]");

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
            if (isStringAValidNumber(numberArr[0]).isEmpty()) return Long.valueOf(numberArr[0]);
            else throw new RuntimeException("Please input valid String of numbers");
        } else {
            Long sum = 0L;
            String message = "";
            for (String n : numberArr) {
                message = message + " " + isStringAValidNumber(n);
                if (message.trim().isEmpty()) sum = sum + filterBigNumbers(Long.valueOf(n.trim()), MAX_LIMIT);
            }
            if (!message.trim().isEmpty()) {
                throw new RuntimeException("Please input valid String of numbers: " + message);
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
        String retDelim = DEFAULT_DELIMITERS;

        for (String r : REGEX_LITERALS) {
            if (tempStr.contains(r)) tempStr = tempStr.replace(r, "\\" + r);
        }
        Matcher m = delimiterPattern.matcher(tempStr);
        while (m.find())    retDelim = retDelim + "|" + m.group(1);

        return retDelim;
    }

    private static boolean isValidInput(final String numbers) {
        if (numbers == null) return false;
        return true;
    }

    private static String isStringAValidNumber(final String number) {
        try {
            Long n = Long.parseLong(number.trim());
            if (lessThan(n, MIN_LIMIT)) return n + "";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    private static boolean lessThan(final Long number, Long minLimit) {
        return number < minLimit;
    }

    private static Long filterBigNumbers(final Long number, Long maxLimit) {
        if (lessThan(number, maxLimit)) return number;
        else return 0L;
    }
}