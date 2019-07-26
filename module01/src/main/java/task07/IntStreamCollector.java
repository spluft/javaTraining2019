package task07;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntStreamCollector implements Collector<Integer, List<Integer>, MathStats> {
    @Override
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Integer>, Integer> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (list1, list2) -> Stream.concat(list1.stream(),list2.stream()).collect(Collectors.toList());
    }

    @Override
    public Function<List<Integer>, MathStats> finisher() {
        return list -> {
            return new MathStats(
                    list.stream().mapToInt(Integer::intValue).sum(),
                    list.stream().count(),
                    list.stream().mapToInt(Integer::intValue).average().getAsDouble(),
                    list.stream().mapToInt(Integer::intValue).min().getAsInt(),
                    list.stream().mapToInt(Integer::intValue).max().getAsInt()
            );
        };

    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.singleton(Characteristics.CONCURRENT);
    }
}
