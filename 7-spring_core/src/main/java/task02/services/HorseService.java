package task02.services;

import org.springframework.stereotype.Service;
import task02.models.Horse;

import java.util.List;

@Service
public class HorseService {
    List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }
}
