package task04FileMover.movers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class FileStreamMover implements Mover {
    private static final Logger LOG = LogManager.getLogger(FileStreamMover.class);

    @Override
    public double move(String in, String out) {
        long startTime = System.nanoTime();
        File sourceFile = new File(in);
        try (InputStream is = new FileInputStream(sourceFile);
             OutputStream os = new FileOutputStream(out)) {

            int readByte;
            while ((readByte = is.read()) != -1) {
                os.write(readByte);
            }
            sourceFile.delete();
        } catch (FileNotFoundException e) {
            LOG.error("Exception during move by FileStreamMover: ", e);
        } catch (IOException e) {
            LOG.error("Exception during move by FileStreamMover: ", e);
        }

        long endTime = System.nanoTime();
        return (double) (endTime - startTime);
    }
}
