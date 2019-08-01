package task04;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class FastFileMoverFileStreams {
    private String in;
    private String out;

    FileChannel sourceChannel, destChannel;

    public FastFileMoverFileStreams(String in, String out) {
        this.in = in;
        this.out = out;
    }

    public void moveByFileStreams() throws IOException {
        //??
    }

    public void moveByFileStreamsWithBuffer() throws IOException {
        FileInputStream sourceFile = new FileInputStream(this.in);
        FileOutputStream destinationFile = new FileOutputStream(this.out);

        byte[] b = new byte[102400];
        while (sourceFile.read(b, 0, 102400) > 0) {
            destinationFile.write(b);
        }

        sourceFile.close();
        destinationFile.close();
    }

    public void moveByFileChannel() throws IOException {
        FileChannel sourceChannel = new FileInputStream(this.in).getChannel();
        FileChannel destChannel = new FileOutputStream(this.out).getChannel();

        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        sourceChannel.close();
        destChannel.close();
    }

    public void moveByNIO2() throws IOException {
        Files.copy(new File(this.in).toPath(), new File(out).toPath());
    }
}
