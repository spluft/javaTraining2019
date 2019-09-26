package task01;

import java.util.Map;

public class ThreadSafeMapWithSync extends MyMap{
    private Map<Integer, Integer> unsafeMap;

    public ThreadSafeMapWithSync(Map<Integer, Integer> unsafeMap) {
        super(unsafeMap);
        this.unsafeMap = unsafeMap;
    }

    public void putToMap(Integer key, Integer value) {
        synchronized (this) {
            super.putToUnsafeMap(key, value);
        }
    }

    public Integer getSumFromMap() {
        synchronized (this) {
            return super.getSumFromUnsafeMap();
        }
    }


}
