package task02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task02.models.Race;

@Service
public class RaceService {
    @Autowired
    private HorseService horseService;

    public RaceService(HorseService horseService) {
        this.horseService = horseService;
    }

    public Race getRace() {
        return new Race(horseService.getHorses());
    }
}
