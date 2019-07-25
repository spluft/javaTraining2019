package task04;

@FunctionalInterface
public interface ThreeFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}