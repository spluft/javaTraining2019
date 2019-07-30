package task02DiskAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DiskAnalyzerTest {
    private Logger logger = LogManager.getLogger();

    @Test
    public void testFindFileWithMaxNumberOfChar() throws IOException {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer("/home/spluft/Documents/TrainingJava");

        File etalon = new File("/home/spluft/Documents/TrainingJava/01 - Java 8 (Lambdas & Streams)/Lectures/01 - 2017-05-30 10.03 JMP7.16_ Lambdas and Streams (Java 8).mp4");

        assertThat(diskAnalyzer.findFileWithMaxNumberOfChar('s'))
                .isFile()
                .exists()
                .hasContentEqualTo(etalon);
    }

    @Test
    public void testFind5TopBigSize() throws IOException {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer("/home/spluft/Documents/TrainingJava");

        Map<File, Long> etalonMap = new HashMap<>();
        etalonMap.put(new File("/home/spluft/Documents/TrainingJava/04 - Advanced Multithreading/Part_2_CF_Akka.mp4"), 158735395L);
        etalonMap.put(new File("/home/spluft/Documents/TrainingJava/03 - Multithreading and Concurrency/Lectures/2017-06-08 10.03 JMP7.17_ Backend_ Multithreading_ Classic Model and Concurrency .mp4"), 151167801L);
        etalonMap.put(new File("/home/spluft/Documents/TrainingJava/02 - Working with Files in Java (File API, JSON, XML, CSV)/Lectures/Working with Files in Java.mp4"), 148387204L);
        etalonMap.put(new File("/home/spluft/Documents/TrainingJava/01 - Java 8 (Lambdas & Streams)/Lectures/02 - 2017-06-01 10.05 JMP7.16_ Lambdas and Streams (Java 8).mp4"), 147002545L);
        etalonMap.put(new File("/home/spluft/Documents/TrainingJava/01 - Java 8 (Lambdas & Streams)/Lectures/01 - 2017-05-30 10.03 JMP7.16_ Lambdas and Streams (Java 8).mp4"), 142306985L);

        assertThat(diskAnalyzer.find5TopBigSize())
                .isEqualTo(etalonMap);
    }

    @Test
    public void testGetAverageSizeOfFilesWithSubdirectories() throws IOException {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer("/home/spluft/Documents/TrainingJava");

        assertThat(diskAnalyzer.getAverageSizeOfFiles(true))
                .isEqualTo(4.4621363E7);
    }

    @Test
    public void testGetAverageSizeOfFilesWithoutSubdirectories() throws IOException {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer("/home/spluft/Documents/TrainingJava/01 - Java 8 (Lambdas & Streams)");

        assertThat(diskAnalyzer.getAverageSizeOfFiles(false))
                .isEqualTo(15107.0);
    }

    @Test
    public void testGetSortedStatistics() throws IOException {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer("/home/spluft/Documents/TrainingJava");

        Map<Character, Long> etalonMap = new HashMap<>();
        etalonMap.put('p',2L);
        etalonMap.put('0',2L);
        etalonMap.put('r',3L);
        etalonMap.put('2',2L);
        etalonMap.put('c',1L);
        etalonMap.put('d',1L);
        etalonMap.put('w',1L);
        etalonMap.put('h',3L);
        etalonMap.put('i',1L);
        etalonMap.put('l',2L);
        etalonMap.put('m',3L);
        etalonMap.put('.',1L);

        assertThat(diskAnalyzer.getSortedStatistics())
                .isEqualTo(etalonMap);
    }

}
