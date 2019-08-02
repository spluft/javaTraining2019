package task04FileMover.movers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileChannelMover implements Mover {
    private Logger logger = LogManager.getLogger(FileChannelMover.class);

    @Override
    public double move(String in, String out) throws IOException {
        long startTime = System.nanoTime();
        File sourceFile = new File(in);
        if (sourceFile.exists()) {
            FileChannel sourceChannel = new FileInputStream(in).getChannel();
            FileChannel destChannel = new FileOutputStream(out).getChannel();

            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

            sourceChannel.close();
            destChannel.close();

        } else {
            logger.info(SOURCE_NOT_EXIST + in);
        }
        long endTime = System.nanoTime();
        return (double) (endTime - startTime);
    }
}
