package task07;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntStreamColelctorTest {
    IntStreamCollector collector = new IntStreamCollector();

    @Test
    public void testIntCollector() {
        Stream<Integer> testStream = Stream.iterate(0, integer -> integer + 10).limit(1_000_000);
        MathStats actualMathStatistics = testStream.collect(collector);

        assertEquals(actualMathStatistics, getMathStatistics());
    }

    private MathStats getMathStatistics() {
        return new MathStats(653067456, 1000000L, 4999995.0, 0, 9999990);
    }

}
