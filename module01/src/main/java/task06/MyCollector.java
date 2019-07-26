package task06;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyCollector implements Collector<ClassA, Map<String, Integer>, Map<String, Integer>> {
    private Set<Characteristics> characteristics;

    public MyCollector() {
        characteristics = Collections.singleton(Characteristics.CONCURRENT);
    }

    public MyCollector(Set<Characteristics> characteristics) {
        this.characteristics = characteristics;
    }

    @Override
    public Supplier<Map<String, Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, Integer>, ClassA> accumulator() {
        return (classAMap, classA) -> classAMap.put(classA.getThisString(), classA.getNumber());
    }

    @Override
    public BinaryOperator<Map<String, Integer>> combiner() {
        return (map1, map2) -> Stream.of(map1, map2).flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Function<Map<String, Integer>, Map<String, Integer>> finisher() {
        return classAIntegerMap -> classAIntegerMap
                .entrySet()
                .stream()
                .filter(e -> e.getValue() % 2 == 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }
}