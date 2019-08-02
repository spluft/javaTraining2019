package task04FileMover;

import task04FileMover.movers.FileChannelMover;
import task04FileMover.movers.FileStreamMover;
import task04FileMover.movers.FileStreamWithBufferMover;
import task04FileMover.movers.Nio2Mover;
import task04FileMover.utils.PerfomanceAnalyzer;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        PerfomanceAnalyzer analyzer = new PerfomanceAnalyzer("tdin", "tdout");
        analyzer.reportPerfomance(new FileStreamMover());
        analyzer.reportPerfomance(new FileStreamWithBufferMover());
        analyzer.reportPerfomance(new FileChannelMover());
        analyzer.reportPerfomance(new Nio2Mover());
    }
}
