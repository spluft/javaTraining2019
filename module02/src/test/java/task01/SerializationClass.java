package task01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SerializationClass {
    private Logger logger = LogManager.getLogger();

    @Test
    public void testCollector() throws IOException, ClassNotFoundException {

        List<Person> personList = getPeople();

        writePerson(personList.get(0),"personOne.out");
        writePerson(personList.get(1),"personTwo.out");

        Person personOne = readPerson("personOne.out");
        Person personTwo = readPerson("personTwo.out");

        assertThat(personOne)
                .isNotNull()
                .isLenientEqualsToByAcceptingFields(personList.get(0), "name");

        assertThat(personTwo)
                .isNotNull()
                .isLenientEqualsToByAcceptingFields(personList.get(1),"name");
    }

    private Person readPerson(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

        return (Person) ois.readObject();
    }
    private void writePerson(Person person, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(person);
        oos.flush();
        oos.close();
    }

    private List<Person> getPeople() {
        List<Person> people = new ArrayList<>();

        people.add(new Person("Saveliy", 25));
        people.add(new Person("Olga", 23));
        people.add(new Person("Oleg", 45));
        people.add(new Person("Alex", 32));

        return people;
    }
}