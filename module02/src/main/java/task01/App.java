package task01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Person> people = getPeople();
        getPeople();

        Comparator<Person> compareByNameAsc =
                (p1, p2) -> p1.getName().compareTo(p2.getName());
        Comparator<Person> compareByAgeDesc =
                (p1, p2) -> p2.getAge() - p1.getAge();

        System.out.println("Sorting list of people by name in ascending order:");
        people.stream().sorted(compareByNameAsc)
                .forEach(System.out::println);

        System.out.println("\nSorting list of people by age in " +
                "descendant order:");
        people.stream().sorted(compareByAgeDesc)
                .forEach(System.out::println);
    }

    private static List<Person> getPeople() {
        List<Person> people = new ArrayList<>();

        people.add(new Person("Saveliy", 25));
        people.add(new Person("Olga", 23));
        people.add(new Person("Oleg", 45));
        people.add(new Person("Alex", 32));

        return people;
    }
}
