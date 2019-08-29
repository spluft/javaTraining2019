package task05.enums;

public enum TypeMessage {
    HDD_MODEl("HDD"),
    CPU_MODEl("CPU"),
    MOTHERBOARD_MODEl("MB"),
    RAM_MODEl("RAM");

    private String name;

    TypeMessage(String s) {
        this.name = s;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName(){
        return name;
    }
}
