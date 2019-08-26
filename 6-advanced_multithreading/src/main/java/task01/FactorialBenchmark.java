package task01;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import task01.factorial.FactorialService;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms1G", "-Xmx15G"})
@Warmup(iterations = 2)
@Measurement(iterations = 3)
public class FactorialBenchmark {
    @Param({"1"})
    private int num;

    static FactorialService service = new FactorialService();
    private static final int POOL_SIZE = 4;

    public static void main(String[] args) throws Exception {

        Options opt = new OptionsBuilder()
                .include(FactorialBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();

    }

    @Benchmark
    public void calculateFactorial(Blackhole bh) {

        bh.consume(service.getFactorialWithFJP(POOL_SIZE, BigInteger.valueOf(num)));
    }

    @Benchmark
    public void calculateSequentialStream(Blackhole bh) {

        bh.consume(service.getFactorialWitoutFJP(BigInteger.valueOf(num)));
    }
}