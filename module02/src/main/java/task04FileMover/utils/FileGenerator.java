package task04FileMover.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileGenerator {

    public static void generate(String path, int size) {
        File file = new File(path);
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");) {
            randomAccessFile.setLength(1024 * size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
