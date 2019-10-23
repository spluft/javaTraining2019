package task01.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import task01.models.Horse;
import task01.models.Race;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EmulationService {
    private static final Logger logger = LogManager.getLogger(EmulationService.class);
    private Race race;
    Map<Horse, Double> distance;

    public void start(ApplicationContext context) throws IOException {
        RaceService raceService = context.getBean("raceService", RaceService.class);
        BetService betService = context.getBean("betService", BetService.class);

        this.race = raceService.getRace();

        printInformation(race);
        betService.makeBet(race);

        getResult();
    }

    private void printInformation(Race race) {
        logger.info("Information about race: ");
        race.getHorseList().forEach(horse -> logger.info(horse.toString()));
    }

    private void getResult() {
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

        System.out.println("Winners:");
        printIntermediateResults();
    }

    private double getMaxCoveredDistance() {
        return distance.values().stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .getAsDouble();
    }

    private void increaseCoveredDistance() {
        distance.entrySet()
                .forEach(entry ->
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