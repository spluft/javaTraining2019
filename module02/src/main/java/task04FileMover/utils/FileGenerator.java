package task04FileMover.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileGenerator {

    public static void generate(String path, int size) {
        File file = new File(path);
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.setLength(1024 * size);
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
