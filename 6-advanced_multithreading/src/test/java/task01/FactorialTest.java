package task01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task01.factorial.FactorialService;

import java.math.BigInteger;

public class FactorialTest {
    FactorialService service;

    @BeforeEach
    public void init() {
        service = new FactorialService();
    }

    @Test
    public void factorialFJPTest() {
        Assertions.assertEquals(BigInteger.valueOf(6), service.getFactorialWithFJP(4, BigInteger.valueOf(3)));
        Assertions.assertEquals(BigInteger.valueOf(1), service.getFactorialWithFJP(4, BigInteger.valueOf(0)));
        Assertions.assertEquals(BigInteger.valueOf(1), service.getFactorialWithFJP(4, BigInteger.valueOf(1)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.getFactorialWithFJP(4, BigInteger.valueOf(-1)));
    }

    @Test
    public void factorialWithoutFJPTest() {
        Assertions.assertEquals(BigInteger.valueOf(6), service.getFactorialWitoutFJP(BigInteger.valueOf(3)));
        Assertions.assertEquals(BigInteger.valueOf(1), service.getFactorialWitoutFJP(BigInteger.valueOf(0)));
        Assertions.assertEquals(BigInteger.valueOf(1), service.getFactorialWitoutFJP(BigInteger.valueOf(1)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.getFactorialWitoutFJP(BigInteger.valueOf(-1)));
    }

    @Test
    public void compareImpl() {
        long time1 = System.nanoTime();
        BigInteger fjFactorial = service.getFactorialWithFJP(4,BigInteger.valueOf(1));
        long time2 = System.nanoTime();
        BigInteger factorial = service.getFactorialWitoutFJP(new BigInteger("1"));
        long time3 = System.nanoTime();
        Assertions.assertEquals(fjFactorial, factorial);
        System.out.println("FJP: " + (time2 - time1) + " without FJP: " + (time3 - time2));
    }
}
