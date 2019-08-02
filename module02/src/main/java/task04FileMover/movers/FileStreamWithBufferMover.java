package task04FileMover.movers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamWithBufferMover implements Mover {
    private Logger logger = LogManager.getLogger(FileStreamWithBufferMover.class);

    @Override
    public double move(String in, String out) throws IOException {
        long startTime = System.nanoTime();
        File sourceFile = new File(in);
        if (sourceFile.exists()) {
            FileInputStream fis = new FileInputStream(sourceFile);
            FileOutputStream fos = new FileOutputStream(out);

            byte[] b = new byte[102400];
            while (fis.read(b, 0, 102400) > 0) {
                fos.write(b);
            }

            fis.close();
            fos.close();
        } else {
            logger.info(SOURCE_NOT_EXIST + in);
        }
        long endTime = System.nanoTime();
        return (double) (endTime - startTime);
    }
}
