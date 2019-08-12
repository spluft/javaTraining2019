package task04;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static ThreeFunction createPerson;
    private static ThreeFunction addBasisToName;

    static {
        /*
         * This ThreeFunction defines logic of person creation and adding an instance to the list.
         * Returns a created person.
         * */
        ThreeFunction<Person, String, Integer, Person> createPerson = (list, name, age) -> {
            Person person = new Person(name, age);

            return person;
        };
        /*
         * This ThreeFunction adds a basis to people whose age is greater or equals to given
         * Returns a list of found persons.
         * */
        ThreeFunction<Person, Integer, String, Person> addBasisToName = (person, age, basis) -> {
            person.setName(person.getName() + ", " + basis);

            return person;
        };
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        createPerson.apply(people, "Saveliy", 25);
        createPerson.apply(people, "Olga", 23);
        createPerson.apply(people, "Oleg", 45);
        createPerson.apply(people, "Alex", 32);

        System.out.println(people);
        System.out.println(addBasisToName.apply(people, 30, "Mr/Mrs"));
    }

}