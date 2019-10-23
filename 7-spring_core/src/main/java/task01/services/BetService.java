package task01.services;

import task01.models.Horse;
import task01.models.Race;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class BetService {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public List<Horse> makeBet(Race race) throws IOException {
        do {
            System.out.println("Make your choice:\n" +
                    "1. By horse name\n" +
                    "2. By Rider\n" +
                    "3. By Breed\n" +
                    "\nPress q for exit.\n");

            switch (reader.readLine()) {
                case "1":
                    return getBetHorses(race, Horse::getName);
                case "2":
                    return getBetHorses(race, horse -> horse.getRider().getName());
                case "3":
                    return getBetHorsesByBreed(race, horse -> horse.getBreed().toString());
                case "q":
                    System.exit(0);
                    break;
            }
            System.out.println("You're wrong. Try again.");
        } while (true);
    }

    private static List<Horse> getBetHorses(Race race, Function<Horse, String> mapper) throws IOException {
        List<Horse> horses;
        boolean flag;
        do {
            printOptions(race, mapper);

            String choice = reader.readLine();
            if (choice.equals("q")) {
                System.exit(0);
            }

            horses = race.getHorseList().stream()
                    .filter(horse -> horse.getName().equals(choice))
                    .collect(toList());

            flag = horses.size() > 0;
            if (!flag) {
                System.out.println("You're wrong. Try again.");
            }
        } while (!flag);

        return horses;
    }

    private static List<Horse> getBetHorsesByBreed(Race race, Function<Horse, String> mapper) throws IOException {
        List<Horse> horses;
        boolean flag;
        do {
            printOptions(race, mapper);

            String choice = reader.readLine();

            if (choice.equals("q")) {
                System.exit(0);
            }

            horses = race.getHorseList().stream()
                    .filter(horse -> {
                        String breedStr = horse.getBreed().toString();
                        return breedStr.equals(choice);
                    })
                    .collect(toList());

            flag = horses.size() > 0;
            if (!flag) {
                System.out.println("You're wrong. Try again.");
            }
        } while (!flag);

        return horses;
    }

    private static void printOptions(Race race, Function<Horse, String> mapper) {
        List<String> names = race.getHorseList().stream()
                .map(mapper)
                .distinct()
                .collect(toList());

        IntStream.range(0, names.size())
                .forEach(i -> System.out.println(i + 1 + ". " + names.get(i)));
    }

}