package task02.models;

import org.springframework.beans.factory.annotation.Required;

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

    @Required
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Horse getHorse() {
        return horse;
    }

    @Required
    public void setHorse(Horse horse) {
        this.horse = horse;
    }
}