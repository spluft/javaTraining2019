package task04;

import task02.Person;
import task03.Deletor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.*;

public class App {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        /*
         * This ThreeFunction defines logic of person creation and adding an instance to the list.
         * Returns a created person.
         * */
        ThreeFunction<List, String, Integer, Person> createPerson = (list, name, age) -> {
            Person person = new Person(name, age);
            list.add(person);

            return person;
        };

        /*
         * This ThreeFunction adds a basis to people whose age is greater or equals to given
         * Returns a list of found persons.
         * */
        ThreeFunction<List<Person>, Integer, String, List<Person>> addBasisToName = (list, age, basis) -> {
            list.stream()
                    .filter(p -> p.getAge() >= age)
                    .forEach(p -> p.setName(p.getName() + ", " + basis));

            return list;
        };

        createPerson.apply(people, "Saveliy", 25);
        createPerson.apply(people, "Olga", 23);
        createPerson.apply(people, "Oleg", 45);
        createPerson.apply(people, "Alex", 32);

        System.out.println(people);
        System.out.println(addBasisToName.apply(people, 30, "Mr/Mrs"));
    }
}