package task02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App {
    private static Comparator<Person> compareByNameAsc;
    private static Comparator<Person> compareByAgeDesc;

    static {
        Comparator<Person> compareByNameAsc =
                (p1, p2) -> p1.getName().compareTo(p2.getName());
        Comparator<Person> compareByAgeDesc =
                (p1, p2) -> p2.getAge() - p1.getAge();
    }

    public static void main(String[] args) {
        List<Person> people = getPeople();

        sortingListOfPersonByNameAsc(people, compareByNameAsc);
        sortingListOfPersonByAgeDesc(people, compareByAgeDesc);
    }

    private static void sortingListOfPersonByNameAsc(List<Person> people, Comparator<Person> compareByNameAsc) {
        System.out.println("Sorting list of people by name in ascending order:");
        people.stream().sorted(compareByNameAsc)
                .forEach(System.out::println);
    }

    private static void sortingListOfPersonByAgeDesc(List<Person> people, Comparator<Person> compareByAgeDesc) {
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
