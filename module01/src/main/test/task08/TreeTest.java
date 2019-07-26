package task08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TreeTest {
    private Tree tree = createTree();

    @Test
    public void testGetAllValue() {
        List<Integer> allValues = tree.getAllValues();
        assertThat(allValues)
                .isNotEmpty()
                .hasSize(7);
    }

    @Test
    public void testGetEvenAllValue() {
        List<Integer> allValues = tree.getEvenValues();
        assertThat(allValues)
                .isNotEmpty()
                .hasSize(4);
    }

    @Test
    public void testGetSumOfEvenValues() {
        Integer sumOfValues = tree.getSumOfEvenValues();
        assertEquals(sumOfValues, new Integer(36));
    }

    @Test
    public void testIsContain() {
        assertFalse(tree.isContains(13));
    }

    private Tree createTree() {
        Tree nullTree = null;
        return new Tree(0,
                new Tree(1,
                        new Tree(11, nullTree), new Tree(12, nullTree)),
                new Tree(2,
                        new Tree(21, nullTree), new Tree(22, nullTree)));
    }
}
