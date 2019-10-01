package task02.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import task02.models.Bet;
import task02.models.Horse;
import task02.models.Race;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmulationService {
    private static final Logger logger = LogManager.getLogger(EmulationService.class);
    private List<Horse> horses;
    private Bet bet;
    private Race race;
    private Map<Horse, Double> distance;

    public List<Horse> start(ApplicationContext context) {
        HorseService horseService = context.getBean("horseService", HorseService.class);
        RaceService raceService = context.getBean("raceService", RaceService.class);

        this.race = raceService.getRace();

        printInformation(race);
        this.bet = makeABet(race);

        return getResult();
    }

    private void printInformation(Race race) {
        logger.info("Information about race: ");
        race.getHorseList().forEach(horse -> logger.info(horse.toString()));
    }

    private Bet makeABet(Race race) {
        String scanner;
        Horse horse;
        do {
            logger.info("You bet on a horse:");
            scanner = new Scanner(System.in).nextLine();
            horse = race.getHorseByName(scanner);
        } while (horse == null);
        logger.info("Your bet is:");
        Integer amount = new Scanner(System.in).nextInt();
        return new Bet(amount, horse);
    }

    private List<Horse> getResult() {
        distance = race.getHorseList().stream()
                .collect(Collectors.toMap(Function.identity(), s -> 0d));

        do {
            increaseCoveredDistance();
            printIntermediateResults();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (getMaxCoveredDistance() < 100);

        return new ArrayList<Horse>();
    }

    private double getMaxCoveredDistance() {
        return distance.values().stream().mapToDouble(Double::doubleValue).max().getAsDouble();
    }

    private void increaseCoveredDistance() {
        distance.entrySet().forEach(entry ->
                entry.setValue(entry.getValue() + ThreadLocalRandom.current().nextDouble(3, 5)));
    }


    private void printIntermediateResults() {
        List<Horse> namesByPosition = getHorsesByPositions(distance);

        IntStream.range(0, namesByPosition.size())
                .forEach(i -> System.out.println((i + 1) + ". "
                        + namesByPosition.get(i).getName()
                        + "(" + namesByPosition.get(i).getRider().getName() + ", " + namesByPosition.get(i).getBreed() + ") - "
                        + String.format("%.2f", distance.get(namesByPosition.get(i)))));
        System.out.println("");
    }

    private List<Horse> getHorsesByPositions(Map<Horse, Double> distance) {
        return distance.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}