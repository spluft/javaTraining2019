package task08;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

class Tree {

    private int value;
    private List<Tree> children = new LinkedList<>();

    public Tree() {
    }

    public Tree(int value, List<Tree> children) {
        super();
        this.value = value;
        this.children.addAll(children);
    }

    public Tree(int value, Tree... children) {
        this(value, asList(children));
    }

    public int getValue() {
        return value;
    }

    public List<Tree> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public Stream<Tree> flattened() {
        return Stream.concat(Stream.of(this), children.stream().filter(Objects::nonNull).flatMap(Tree::flattened));
    }

    //------
    public List<Integer> getAllValues() {
        return flattened()
                .map(Tree::getValue)
                .collect(Collectors.toList());
    }

    public List<Integer> getEvenValues() {
        return flattened()
                .map(Tree::getValue)
                .filter(value -> value % 2 == 0)
                .collect(Collectors.toList());
    }

    public Integer getSumOfEvenValues() {
        return flattened()
                .map(Tree::getValue)
                .filter(value -> value % 2 == 0)
                .reduce(Integer::sum)
                .orElse(null);
    }

    public Boolean isContains(Integer num) {
        return flattened()
                .map(Tree::getValue)
                .anyMatch(value -> value.equals(num));
    }
}