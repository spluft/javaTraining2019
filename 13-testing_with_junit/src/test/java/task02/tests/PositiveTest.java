package task02.tests;

import org.junit.Test;
import task02.Calculator;

import java.math.BigInteger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class PositiveTest {

    private Calculator calculator = new Calculator();
    private static final double delta = 0.01d;

    @Test
    public void addingTwoInts() {
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    public void addingTwoDoubles() {
        double result = calculator.add(2.3, 3.5);
        assertEquals(5.8d, result, delta);
    }

    @Test
    public void subtractionTwoInts() {
        int result = calculator.subtract(10, 5);
        assertEquals(5, result);
    }

    @Test
    public void subtractionTwoDoubles() {
        double result = calculator.subtract(10.3, 5.1);
        assertEquals(5.2d, result, delta);
    }

    @Test
    public void multiplyTwoInts() {
        long result = calculator.multiply(10, 130);
        assertEquals(1300, result);
    }

    @Test
    public void multiplyTwoDoubles() {
        double result = calculator.multiply(10.234, 130.123);
        System.out.println(result);
        assertEquals(1331.67, result, delta);
    }

    @Test
    public void dividingTwoInts() {
        double result = calculator.divide(13, 3);
        assertEquals(4.33, result, delta);
    }

    @Test
    public void dividingTwoDoubles() {
        double result = calculator.divide(15.3, 3.643);
        assertEquals(4.19, result, delta);
    }

    @Test
    public void rootDouble() {
        double result = calculator.root(9, 2);
        assertEquals(3.0d, result, 0.01d);
    }

    @Test
    public void rootZero() {
        double result = calculator.root(123d, 0);
        assertEquals(1d, result, delta);
    }

    @Test
    public void powerDouble() {
        double result = calculator.power(10.2, 2);
        assertEquals(104.03, result, delta);
    }

    @Test
    public void powerZero() {
        double result = calculator.power(123d, 0);
        assertEquals(1d, result, delta);
    }

    @Test()
    public void powerDouble_degree_is_not_negative() {
        calculator.power(10.2, -5);
    }

    @Test
    public void isOneNotPrime() {
        boolean prime = calculator.isPrime(1);
        assertFalse(prime);
    }

    @Test
    public void isTwoPrime() {
        boolean prime = calculator.isPrime(2);
        assertTrue(prime);
    }

    @Test
    public void isPrime_big_prime_number() {
        boolean prime = calculator.isPrime(2_147_483_647);
        assertTrue(prime);
    }

    @Test
    public void isNotPrime() {
        boolean prime = calculator.isPrime(1221);
        assertFalse(prime);
    }

    @Test(timeout=2000)
    public void isPrimeTimeout() {
        calculator.isPrime(Long.MAX_VALUE);
    }

    @Test
    public void fiboOne() {
        BigInteger fibo = calculator.fastFibonacciDoubling(1);
        assertThat(BigInteger.ONE, equalTo(fibo));
    }

    @Test
    public void fiboTwo() {
        BigInteger fibo = calculator.fastFibonacciDoubling(2);
        assertThat(BigInteger.ONE, equalTo(fibo));
    }

    @Test(timeout=2000)
    public void fiboCorrect() {
        BigInteger fibo = calculator.fastFibonacciDoubling(22);
        assertThat(BigInteger.valueOf(17711), equalTo(fibo));
    }
}
