package task04FileMover.movers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Nio2Mover implements Mover {
    private Logger logger = LogManager.getLogger(Nio2Mover.class);

    @Override
    public double move(String in, String out) throws IOException {
        long startTime = System.nanoTime();
        File sourceFile = new File(in);
        if (sourceFile.exists()) {
            Files.move(sourceFile.toPath(), new File(out).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } else {
            logger.info(SOURCE_NOT_EXIST + in);
        }
        long endTime = System.nanoTime();
        return (double) (endTime - startTime);
    }
}
