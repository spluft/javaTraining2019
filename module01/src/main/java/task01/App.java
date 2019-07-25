package task01;

public class App {
    public static void main(String[] args) {
        executeTask(() -> {
            new TaskUpdater().summury();
            System.out.println("updating 1...");
        });

        executeTask(() -> {
            new TaskUpdater().summury();
            System.out.println("updating 2...");
        });
    }

    private static void executeTask(Runnable threadLogic) {
        new Thread(threadLogic).start();
    }
}
