package task01;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadSafeMapWithoutSync extends MyMap{
    private AtomicBoolean block = new AtomicBoolean(false);

    public ThreadSafeMapWithoutSync(Map<Integer, Integer> unsafeMap) {
        super(unsafeMap);
    }

    public void putToMap(Integer key, Integer value) {
        if (block.compareAndSet(false, true)) {
            super.putToUnsafeMap(key, value);
            block.set(false);
        }
    }

    public Integer getSumFromMap() {
        Integer sum = 0;

        if (block.compareAndSet(false, true)) {
            sum = super.getSumFromUnsafeMap();
            block.set(false);
        } else {
            sum = getSumFromMap();
        }

        return sum;
    }

}
