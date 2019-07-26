package task06;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MyCollectorTest {
    private Logger logger = LogManager.getLogger();

    @Test
    public void testCollector() {

        Map<String, Integer> stringMap = getListOfClassA().stream()
                .collect(new MyCollector());

        assertThat(stringMap)
                .isNotEmpty()
                .hasSize(2)
                .doesNotContainKey("string0")
                .doesNotContainKey("string1");
    }

    //make List<ClassA>
    private static List<ClassA> getListOfClassA() {
        List<ClassA> classAList = new ArrayList<>();
        classAList.add(new ClassA("string0", 3));
        classAList.add(new ClassA("string1", 5));
        classAList.add(new ClassA("string2", 4));
        classAList.add(new ClassA("string3", 4));
        return classAList;
    }
}
