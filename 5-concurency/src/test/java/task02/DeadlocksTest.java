package task02;

import org.junit.jupiter.api.Test;

public class DeadlocksTest {

    @Test
    public void DeadlockTest() throws InterruptedException {
        App.runThreads();
    }
}
