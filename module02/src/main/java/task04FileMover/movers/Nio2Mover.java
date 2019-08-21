package task04FileMover.movers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Nio2Mover implements Mover {
    private static final Logger LOG = LogManager.getLogger(Nio2Mover.class);

    @Override
    public double move(String in, String out) {
        long startTime = System.nanoTime();
        File sourceFile = new File(in);
        try {
            Files.move(sourceFile.toPath(), new File(out).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOG.error("Exception during move by Nio2Mover: ", e);
        }

        long endTime = System.nanoTime();
        return (double) (endTime - startTime);
    }
}
