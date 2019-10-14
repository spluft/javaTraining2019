package task01;

import java.util.HashMap;
import java.util.Map;

public class MyMap {
    private static Map<Integer, Integer> unsafeMap = new HashMap<>();

    public MyMap(Map<Integer, Integer> unsafeMap) {
        this.unsafeMap = unsafeMap;
    }

    public void putToUnsafeMap(Integer key, Integer value) {
        unsafeMap.put(key, value);
    }

    public Integer getSumFromUnsafeMap() {
        Integer sum = 0;

        for (Integer num : unsafeMap.values()) {
            sum += num;
        }

        return sum;
    }

}
