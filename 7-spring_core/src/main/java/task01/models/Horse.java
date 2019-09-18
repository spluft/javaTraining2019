package task01.models;

public class Horse {
    private String name;
    private Breed breed;
    private Integer speed;
    private Rider rider;

    public Horse(String name, Breed breed, Integer speed, Rider rider) {
        this.name = name;
        this.breed = breed;
        this.speed = speed;
        this.rider = rider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    @Override
    public String toString() {
        return "Horse{" +
                "name='" + name + '\'' +
                ", breed=" + breed +
                ", speed=" + speed +
                ", rider=" + rider +
                '}';
    }
}
