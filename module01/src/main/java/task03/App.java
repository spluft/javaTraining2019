package task03;

import task02.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.*;

public class App {
    private static List<Person> people = new ArrayList<>();
    private static Consumer<String> printingOperation = System.out::println;

    private static UnaryOperator<String> addBasisToNameOperation;
    private static Runnable initializePeopleOperation;
    private static Supplier<String> getPeopleInfoOperation;
    private static BiFunction<String, String, Person> createPersonWithStringAgeOperation;
    private static BiFunction<String, Integer, Person> createPersonOperation;
    private static Consumer<Person> addPersonToPeopleOperation;
    private static Deletor<Person, Integer> lambdaDeletor;
    private static Deletor<Person, Integer> anonimousDeletor;

    public static void main(String[] args) {
        initApp();

        myOwnFunctionalInterfaceWIthLambda();

        myOwnFunctionalInterfaceWithAnonimousClass();

        lambdaDeletor = myOwnFunctionalInterfaceWIthLambda();
        anonimousDeletor = myOwnFunctionalInterfaceWithAnonimousClass();

        printResults();
    }

    private static void initApp() {
        addBasisToNameOperation = (n) -> n + ", Mr/Mrs";
        createPersonOperation = (name, age)
                -> new Person(addBasisToNameOperation.apply(name), age);
        addPersonToPeopleOperation = (person) -> people.add
                (person);
        Function<String, Integer> stringToIntegerConverter = Integer::parseInt;
        createPersonWithStringAgeOperation =
                (name, age) -> new Person(addBasisToNameOperation.apply(name),
                        stringToIntegerConverter.apply(age));
        getPeopleInfoOperation = () -> people.stream().map(p -> p
                .toString()).reduce("", (s1, s2) -> s1 + "\n" + s2);
        initializePeopleOperation = () -> {
            addPersonToPeopleOperation.accept(createPersonOperation.apply("Saveliy", 25));
            addPersonToPeopleOperation.accept(createPersonOperation.apply("Olga", 23));
            addPersonToPeopleOperation.accept(createPersonOperation.apply("Oleg", 45));
            addPersonToPeopleOperation.accept(createPersonOperation.apply("Alex", 32));
        };
    }

    /*
     * Defining Deletor with lambda. Also using static interface method.
     * */
    private static Deletor<Person, Integer> myOwnFunctionalInterfaceWIthLambda(){
        Deletor<Person, Integer> lambdaDeletor = (i) -> {
            Person pers = (Person) Deletor.findElement(people, people.size() - 1);
            people.remove(pers);

            return pers;
        };

        return lambdaDeletor;
    }

    /*
     * Defining Deletor with anonimous class.
     * */
    private static Deletor<Person, Integer> myOwnFunctionalInterfaceWithAnonimousClass(){
        Deletor<Person, Integer> anonimousDeletor = new Deletor<Person, Integer>() {
            @Override
            public Person delete(Integer pointer) {
                if (pointer < 0) {
                    throw new IllegalArgumentException("Index can not be" +
                            " negative.");
                }
                Iterator<Person> it = people.iterator();
                Person person = null;

                for (int ctr = 0; ctr <= pointer; ctr++) {
                    person = it.next();
                }

                it.remove();

                return person;
            }
        };

        return anonimousDeletor;
    }

    private static void printResults(){
        printingOperation.accept("\n1. Generating a list of people:");
        initializePeopleOperation.run();
        printingOperation.accept(getPeopleInfoOperation.get());

        printingOperation.accept("\n2. Checking lower case of the first letter in the name:");
        checkLowerCaseInName(people, (s) -> Character.isLowerCase(s.charAt(0)));

        printingOperation.accept("\n3. Printing the info about people:");
        printingOperation.accept(getPeopleInfoOperation.get());

        printingOperation.accept("\n4. Adding a new person:");
        Person person = createPersonOperation.apply("New person #1", 25);
        addPersonToPeopleOperation.accept(person);
        printingOperation.accept(person + " - Added to the list of people.");

        printingOperation.accept("\n5. Adding a new person with string type of age:");

        addPersonToPeopleOperation.accept(createPersonWithStringAgeOperation.apply("New person #2", "25"));

        printingOperation.accept("\nDeleting the last element #1 [Using lambda]:");
        printingOperation.accept("Deleted: " + lambdaDeletor.delete
                (people.size() - 1));
        printingOperation.accept(getPeopleInfoOperation.get());

        printingOperation.accept("\nDeleting the last element #2 [Using anonimous class]:");
        printingOperation.accept("Deleted: " + anonimousDeletor.delete
                (people.size() - 1));
        printingOperation.accept(getPeopleInfoOperation.get());

        printingOperation.accept("\nDeleting last element #3 [Using default method]:");
        printingOperation.accept("Deleted: " +
                lambdaDeletor.deleteItemFromCollection(people, people.size() - 1));
        printingOperation.accept(getPeopleInfoOperation.get());
    }

    /**
     * Implementing Predicate interface.
     */
    private static void checkLowerCaseInName(List<Person> people, Predicate<String> predicate) {
        people.stream().filter(p -> predicate.test(p.getName())).forEach
                (p -> {
                    printingOperation.accept(p.getName() + " - The first letter should be capital.");

                    p.setName(Character.toUpperCase(p.getName().charAt(0))
                            + p.getName().substring(1));

                    printingOperation.accept("Corrected: " + p.getName());
                });
    }
}
