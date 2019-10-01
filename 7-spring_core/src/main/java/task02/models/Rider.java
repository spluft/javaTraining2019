package task02.models;

import org.springframework.beans.factory.annotation.Required;

public class Rider {
    private String name;
    private int skill;

    public Rider(String name, int skill) {
        this.name = name;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    @Required
    public void setName(String name) {
        this.name = name;
    }

    public int getSkill() {
        return skill;
    }

    @Required
    public void setSkill(int skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Rider{" +
                "name='" + name + '\'' +
                ", skill=" + skill +
                '}';
    }
}
