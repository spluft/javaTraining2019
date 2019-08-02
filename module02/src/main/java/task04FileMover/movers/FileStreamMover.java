package task04FileMover.movers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class FileStreamMover implements Mover {
    private Logger logger = LogManager.getLogger(FileStreamMover.class);

    @Override
    public double move(String in, String out) throws IOException {
        long startTime = System.nanoTime();
        File sourceFile = new File(in);
        if (sourceFile.exists()) {
            InputStream is = new FileInputStream(sourceFile);
            OutputStream os = new FileOutputStream(out);

            int readByte;
            while ((readByte = is.read()) != -1) {
                os.write(readByte);
            }
            sourceFile.delete();
        } else {
            logger.info(SOURCE_NOT_EXIST + in);
        }
        long endTime = System.nanoTime();
        return (double) (endTime - startTime);
    }
}
