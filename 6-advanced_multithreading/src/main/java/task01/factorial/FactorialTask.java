package task01.factorial;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<BigInteger> {

    private static final Logger LOG = LogManager.getLogger(FactorialTask.class);

    private BigInteger index;
    private final String ONE = "1";

    public FactorialTask(BigInteger index) throws IllegalArgumentException {
        if (index.intValue() < 0) {
            LOG.info("Value must be positive. " + index.toString() + " is negative.");
            throw new IllegalArgumentException("Value must be positive. " + index.toString() + " is negative.");
        }

        this.index = index;
    }

    @Override
    protected BigInteger compute() {

        if (index.intValue() < 2) {
            return new BigInteger(ONE);
        }

        FactorialTask task = new FactorialTask(index.subtract(new BigInteger(ONE)));
        task.fork();

        return index.multiply(task.join());
    }

    public BigInteger getFactorialWithoutFJP() {

        if(index.intValue() < 2) {
            return new BigInteger(ONE);
        }

        return index.multiply(new FactorialTask(index.subtract(new BigInteger(ONE))).getFactorialWithoutFJP());
    }

}