package task01.services;

import task01.models.Race;

public class RaceService {
    private HorseService horseService;

    public RaceService(HorseService horseService) {
        this.horseService = horseService;
    }

    public Race getRace() {
        return new Race(horseService.getHorses());
    }
}
