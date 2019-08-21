package task04FileMover.movers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class FileStreamWithBufferMover implements Mover {
    private static final Logger LOG = LogManager.getLogger(FileStreamWithBufferMover.class);

    @Override
    public double move(String in, String out) {
        long startTime = System.nanoTime();
        File sourceFile = new File(in);

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(out)) {

            byte[] b = new byte[102400];
            while (fis.read(b, 0, 102400) > 0) {
                fos.write(b);
            }

        } catch (FileNotFoundException e) {
            LOG.error("Exception during move by FileStreamWithBufferMover: ", e);
        } catch (IOException e) {
            LOG.error("Exception during move by FileStreamWithBufferMover: ", e);
        }

        long endTime = System.nanoTime();
        return (double) (endTime - startTime);
    }
}
