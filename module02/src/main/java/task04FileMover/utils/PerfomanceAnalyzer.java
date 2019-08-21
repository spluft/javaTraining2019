package task04FileMover.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task04FileMover.movers.Mover;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PerfomanceAnalyzer {
    private static final Logger LOG = LogManager.getLogger(PerfomanceAnalyzer.class);

    public static final int[] FILE_SIZES = new int[]{1};
    final private int CONST_REPEAT = 1000;

    private String in;
    private String out;
    private double averageTime;

    public PerfomanceAnalyzer(String in, String out) {
        this.in = in;
        this.out = out;
    }

    public void reportPerfomance(Mover mover) {
        LOG.info(mover.getClass().getSimpleName());
        try {
            for (int size : FILE_SIZES) {
                for (int j = 0; j < CONST_REPEAT; j++) {
                    FileGenerator.generate(in, size);
                    averageTime = mover.move(in, out);
                }
                double averageTime = this.averageTime / CONST_REPEAT;
                logTime(averageTime, size);
                restartTimer();
            }
        } catch (IOException e) {
            LOG.error("", e);
        } finally {
            deleteFile();
        }
    }

    private void logTime(double time, int size) {
        if (averageTime > 1_000_000_000) {
            double recalculated = time / 1_000_000_000;
            LOG.info(recalculated + " seconds. File size " + size + " KB.");
        } else if (averageTime > 1_000_000) {
            double recalculated = time / 1_000_000;
            LOG.info(recalculated + " milliseconds. File size " + size + " KB.");
        } else
            LOG.info(time + " nanoseconds. File size " + size + " KB.");

    }

    private void restartTimer(){
        averageTime = 0;
    }

    private void deleteFile() {
        try {
            Files.deleteIfExists(Paths.get(in));
            Files.deleteIfExists(Paths.get(out));
        } catch (IOException e) {
            LOG.error("Exception in PerfomanceAnalyzer.deleteFile: ", e);
        }
    }

}