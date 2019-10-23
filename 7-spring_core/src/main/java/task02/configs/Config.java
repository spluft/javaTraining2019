package task02.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import task02.models.Breed;
import task02.models.Horse;
import task02.models.Rider;
import task02.services.BetService;
import task02.services.EmulationService;
import task02.services.HorseService;
import task02.services.RaceService;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {

    @Bean
    public EmulationService emulationService() {
        return new EmulationService();
    }

    @Bean
    public HorseService horseService() {
        HorseService horseService = new HorseService();
        horseService.setHorses(getHorses());
        return horseService;
    }

    @Bean
    public RaceService raceService() {
        HorseService horseService = horseService();
        RaceService raceService = new RaceService(horseService);
        return raceService;
    }

    @Bean
    public BetService betService() {
        BetService betService = new BetService();
        return betService;
    }

    private List<Horse> getHorses() {
        List<Horse> horses = new ArrayList<>();
        List<Breed> breeds = getBreeds();
        List<Rider> riders = getRiders();
        horses.add(new Horse("1", breeds.get(0), 50, riders.get(0)));
        horses.add(new Horse("2", breeds.get(1), 60, riders.get(1)));
        horses.add(new Horse("3", breeds.get(2), 70, riders.get(2)));
        return horses;
    }

    private List<Breed> getBreeds() {
        List<Breed> breeds = new ArrayList<>();
        breeds.add(new Breed("1"));
        breeds.add(new Breed("2"));
        breeds.add(new Breed("3"));
        return breeds;
    }

    private List<Rider> getRiders() {
        List<Rider> riders = new ArrayList<>();
        riders.add(new Rider("Dobrinya",1));
        riders.add(new Rider("Ilya",2));
        riders.add(new Rider("Aleosha",3));
        return riders;
    }
}
