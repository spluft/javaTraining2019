package task02.models;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class Race {
    private List<Horse> horseList;
    @Value("${race.distance}")
    private Integer distance;

    public Race() {

    }

    public Race(List<Horse> horseList) {
        this.horseList = horseList;
    }

    public List<Horse> getHorseList() {
        return horseList;
    }

    @Required
    public void setHorseList(List<Horse> horseList) {
        this.horseList = horseList;
    }

    public Integer getDistance() {
        return distance;
    }

    @Required
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Horse getHorseByName(String name){
        Horse result = null;
        for (Horse horse:this.horseList) {
            if (horse.getName().equals(name)){
                result = horse;
            }
        }
        return result;
    }

    private boolean isHorseExist(String horseName) {
        return this.horseList
                .stream()
                .anyMatch(horse -> horse.getName().equals(horseName));
    }

    @Override
    public String toString() {
        return "Race{" +
                "horseList=" + horseList +
                ", distance=" + distance +
                '}';
    }
}
