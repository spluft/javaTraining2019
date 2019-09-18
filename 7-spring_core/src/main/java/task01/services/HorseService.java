package task01.services;

import task01.models.Horse;

import java.util.List;

public class HorseService {
    List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }
}
