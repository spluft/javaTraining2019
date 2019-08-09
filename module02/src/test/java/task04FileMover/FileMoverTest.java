package task04FileMover;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task04FileMover.movers.FileChannelMover;
import task04FileMover.movers.FileStreamMover;
import task04FileMover.movers.FileStreamWithBufferMover;
import task04FileMover.movers.Nio2Mover;
import task04FileMover.utils.FileGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FileMoverTest {
    private String in = "ttin";
    private String out = "ttout";

    @BeforeEach
    public void beforeMethod() throws IOException {
        FileGenerator.generate(in, 1);
    }

    @AfterEach
    public void afterMethod() throws IOException {
        Files.deleteIfExists(Paths.get(out));
        Files.deleteIfExists(Paths.get(in));
    }

    @Test
    @DisplayName("Point 01")
    public void FileStreamMoverTest() throws IOException {
        FileStreamMover mover = new FileStreamMover();
        mover.move(in, out);

        assertThat(new File(out))
                .exists();
    }

    @Test
    @DisplayName("Point 02")
    public void FileStreamWithBufferMoverTest() throws IOException {
        FileStreamWithBufferMover mover = new FileStreamWithBufferMover();
        mover.move(in, out);

        assertThat(new File(out))
                .exists();
    }

    @Test
    @DisplayName("Point 03")
    public void FileChannelMoverTest() throws IOException {
        FileChannelMover mover = new FileChannelMover();
        mover.move(in, out);

        assertThat(new File(out))
                .exists();
    }

    @Test
    @DisplayName("Point 04")
    public void Nio2MoverTest() throws IOException {
        Nio2Mover mover = new Nio2Mover();
        mover.move(in, out);

        assertThat(new File(out))
                .exists();
    }
}
