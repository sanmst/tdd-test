package com.tdd.kata;

/**
 * Created by smestry on 11/08/2015.
 *
 * Requirement 1: The method can take 0, 1 or 2 numbers separated by comma (,).
 */
public final class StringCalculator {

    private StringCalculator() {}

    public static Long add(final String numbers) throws RuntimeException {
        if (!isValidInput(numbers)) throw new RuntimeException("Please input valid String of numbers");

        if (numbers.isEmpty())   return 0L;

        // Updated the regular expression to include newline as a delimiter.
        String[] numberArr = numbers.split(",|\n");

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
