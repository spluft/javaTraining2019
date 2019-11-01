package task02.tests;

import org.junit.Test;
import task02.Calculator;

import java.math.BigInteger;

public class NegativeTest {

    private Calculator calculator = new Calculator();
    private static final double delta = 0.01d;

    @Test(expected = IllegalArgumentException.class)
    public void dividingToZeroMustThrowException_int_case() {
        calculator.divide(2, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dividingToZeroMustThrowException_double_case() {
        calculator.divide(2.5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rootDouble_degree_is_not_negative() {
        calculator.root(10.2, -5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void powerDouble_degree_is_not_negativ() {
        calculator.power(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isPrimeExpectExceptionForNegativeInput() {
        calculator.isPrime(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isZeroPrimeExpectException() {
        calculator.isPrime(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fiboArgumentLessThanZero() {
        calculator.fastFibonacciDoubling(-5);
    }
}
