package com.tdd.kata;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by smestry on 11/08/2015.
 *
 * Create a simple String calculator with a method int Add(string numbers)
 * The method can take 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example “” or “1” or “1,2”
 * Start with the simplest test case of an empty string and move to 1 and two numbers
 */
public class StringCalculatorTest {

    @Test
    public final void when3ValidNumbersArePassed() {
        Long sum = StringCalculator.add("1,2,3");
        Assert.assertTrue(sum == 6);
    }

    @Test
    public final void when2ValidNumbersArePassed() {
        Long sum = StringCalculator.add("1,2");
        Assert.assertTrue(sum == 3);
    }

    @Test
    public final void whenValidNumberIsPassed() {
        Long sum = StringCalculator.add("1");
        Assert.assertTrue(sum == 1);
    }

    @Test
    public final void whenEmptyStringIsPassed() {
        Long sum = StringCalculator.add("");
        Assert.assertTrue(sum == 0);
    }

    @Test
    public final void whenNumbersPassedWithSpaces() {
        Long sum = StringCalculator.add("1, 2 , 4, 5");
        Assert.assertTrue(sum == 12);
    }

    @Test(expected = RuntimeException.class)
    public final void whenNullStringIsPassed() {
        StringCalculator.add(null);
    }

    @Test(expected = RuntimeException.class)
    public final void whenStringValuesArePassed() {
        StringCalculator.add("1,X");
    }
}
