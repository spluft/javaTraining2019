package task02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App {

    //I know that CopyOnWriteArrayList create new array during work, but i haven't big array on this app
    //Should I use CopyOnWriteArrayList or some locks or some atomics instead synchronized block?!
//    private static List<Integer> collection = new CopyOnWriteArrayList<>();
    private static List<Integer> collection = new ArrayList<>();

    public static void runThreads() throws InterruptedException {
        Thread thread1 = new Thread(infinityWritingRandomNumberToCollection);
        Thread thread2 = new Thread(printSumInCollection);
        Thread thread3 = new Thread(printSquareSumSquaresNumbersInCollection);

        thread1.start();
        thread2.start();
        thread3.start();

        thread2.join();
        thread3.join();
    }

    private static Runnable infinityWritingRandomNumberToCollection = () -> {
        while (true) {
            wait(1);
            synchronized (collection) {
                collection.add(new Random().nextInt(10));
            }
        }
    };

    private static Runnable printSumInCollection = () -> {
        wait(5);
        synchronized (collection) {
            int sum = collection.stream().reduce(0, Integer::sum);
            System.out.println("Summary is " + sum);
        }
    };

    private static Runnable printSquareSumSquaresNumbersInCollection = () -> {
        wait(5);
        synchronized (collection) {
            int sum = collection.stream()
                    .mapToInt(num -> num * num)
                    .reduce(0, Integer::sum);
            System.out.println("Square of squares is " + sum);
        }
    };

    private static void wait(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
