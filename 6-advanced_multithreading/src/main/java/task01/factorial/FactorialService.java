package task01.factorial;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

public class FactorialService {

    public BigInteger getFactorialWithFJP(Integer poolSize, BigInteger num) {
        ForkJoinPool pool = new ForkJoinPool(poolSize);
        BigInteger result = pool.invoke(new FactorialTask(num));

        return result;
    }

    public BigInteger getFactorialWitoutFJP(BigInteger num) {

        return new FactorialTask(num).getFactorialWithoutFJP();
    }
}