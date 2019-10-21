package task05.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class OrderMessage {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private int amount;
    @Getter
    private List<Computer> computers;

    public OrderMessage(int id, int amount) {
        this.id = id;
        this.amount = amount;
        this.computers = new ArrayList<>();
    }

    public void addComputer(Computer computer) {
        this.computers.add(computer);
    }
}
