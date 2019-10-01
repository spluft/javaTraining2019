package task02.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import task02.models.Breed;
import task02.models.Horse;
import task02.services.EmulationService;
import task02.services.HorseService;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {

    @Bean
    public EmulationService emulationService() {
        return new EmulationService();
    }

    @Bean
    public HorseService horseService(){
        HorseService horseService = new HorseService();
        horseService.setHorses(getHorses());
        return horseService;
    }

    private List<Horse> getHorses(){
        List<Horse> horses = new ArrayList<>();
        List<Breed> breeds = getBreeds();
        horses.add(new Horse("1",));
        horses.add(new Horse("2"));
        horses.add(new Horse("3"));
        return horses;
    }

    private List<Breed> getBreeds(){
        List<Breed> breeds = new ArrayList<>();
        breeds.add(new Breed("1"));
        breeds.add(new Breed("2"));
        breeds.add(new Breed("3"));
    }
}
