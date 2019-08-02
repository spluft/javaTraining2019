package task04FileMover.movers;

import java.io.IOException;

public interface Mover {
    public String SOURCE_NOT_EXIST = "Source file doesn't exist : ";

    double move(String in, String out) throws IOException;
}
