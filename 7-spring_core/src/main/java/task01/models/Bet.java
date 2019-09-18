package task01.models;

public class Bet {
    int amount;
    Horse horse;

    public Bet(int amount, Horse horse) {
        this.amount = amount;
        this.horse = horse;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }
}