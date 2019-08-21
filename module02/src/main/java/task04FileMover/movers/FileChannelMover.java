package task04FileMover.movers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileChannelMover implements Mover {
    private static final Logger LOG = LogManager.getLogger(FileChannelMover.class);

    @Override
    public double move(String in, String out) {
        long startTime = System.nanoTime();

        try (FileChannel sourceChannel = new FileInputStream(in).getChannel();
             FileChannel destChannel = new FileOutputStream(out).getChannel()) {

            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        } catch (FileNotFoundException e) {
            LOG.error("Exception during move by FileChannelMover:", e);
        } catch (IOException e) {
            LOG.error("Exception during move by FileChannelMover:", e);
        }

        long endTime = System.nanoTime();
        return (double) (endTime - startTime);
    }
}
