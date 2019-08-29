package task05.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Computer {
    private int order;
    private ComputerPart cpu;
    private ComputerPart motherboard;
    private ComputerPart hdd;
    private ComputerPart ram;

    public boolean isReady() {
        if (cpu.isStatus() && motherboard.isStatus() && hdd.isStatus() && ram.isStatus()) {
            return true;
        }

        return false;
    }
}