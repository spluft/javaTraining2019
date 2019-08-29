package task05.model;

import lombok.Builder;
import lombok.Data;
import task05.enums.TypeMessage;

@Builder
@Data
public class ComputerPart {
    private boolean status;
    private TypeMessage type;
    private String name;
}
