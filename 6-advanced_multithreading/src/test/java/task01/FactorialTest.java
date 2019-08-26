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
    public void factorialTest() {
        Assertions.assertEquals(BigInteger.valueOf(6), service.getFactorialWithFJP(4, BigInteger.valueOf(3)));
        Assertions.assertEquals(BigInteger.valueOf(1), service.getFactorialWithFJP(4, BigInteger.valueOf(0)));
        Assertions.assertEquals(BigInteger.valueOf(1), service.getFactorialWithFJP(4, BigInteger.valueOf(1)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.getFactorialWithFJP(4, BigInteger.valueOf(-1)));
    }
}
