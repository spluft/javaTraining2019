package task01;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ConcurrentMapTest {
    @Test
    public void workWithHashMap() throws InterruptedException {
        baseTest(new MyMap(new HashMap()));
    }

    @Test
    public void workWithConcurrentHashMap() {
        assertDoesNotThrow(() -> baseTest(new MyMap(new ConcurrentHashMap())));
    }

    @Test
    public void workWithSynchronizedMap() {
        assertDoesNotThrow(() -> baseTest(new MyMap(Collections.synchronizedMap(new HashMap<>()))));
    }

    @Test
    public void workWithThreadSafeMapWithSynch() {
        assertDoesNotThrow(() -> baseTest(new ThreadSafeMapWithSync(new HashMap<>())));
    }

    @Test
    public void workWithThreadSafeMapWithoutSynch() {
        assertDoesNotThrow(() -> baseTest(new ThreadSafeMapWithoutSync(new HashMap<>())));
    }

    private void baseTest(MyMap MyMap) throws InterruptedException {
        Thread addThread = new Thread(() -> adding(MyMap));
        Thread sumThread = new Thread(() -> getSum(MyMap));

        addThread.start();
        sumThread.start();

        addThread.join();
        sumThread.join();
    }

    private void adding(MyMap MyMap) {
        while (true) {
            MyMap.putToUnsafeMap(new Random().nextInt(1_000), new Random().nextInt(10));
        }
    }

    private Long getSum(MyMap MyMap) {
        while (true) {
            System.out.println(MyMap.getSumFromUnsafeMap());
        }
    }
}