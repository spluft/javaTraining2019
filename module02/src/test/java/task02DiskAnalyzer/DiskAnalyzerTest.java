package task02DiskAnalyzer;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task04FileMover.utils.FileGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DiskAnalyzerTest {
    final private static String BASE_CATALOG = "/home/spluft/test";
    final private static char CHARACTER = 's';

    @BeforeEach
    public void init() throws IOException {
        FileGenerator.generate(BASE_CATALOG + "/firstFile", 1);
        FileGenerator.generate(BASE_CATALOG + "/secondFile", 2);
        FileGenerator.generate(BASE_CATALOG + "/thirdFile", 3);
        FileGenerator.generate(BASE_CATALOG + "/fouthFile", 4);
        FileGenerator.generate(BASE_CATALOG + "/fithFile", 5);
        FileGenerator.generate(BASE_CATALOG + "/ssssss", 5);
        FileGenerator.generate(BASE_CATALOG + "/lFile", 1);

        Files.newFolder(BASE_CATALOG + "/bigFiles");
        Files.newFolder(BASE_CATALOG + "/b");
        Files.newFolder(BASE_CATALOG + "/c");

        FileGenerator.generate(BASE_CATALOG + "/bigFiles" + "/bigFirstFile", 20);
        FileGenerator.generate(BASE_CATALOG + "/bigFiles" + "/bigSecondFile", 19);
        FileGenerator.generate(BASE_CATALOG + "/bigFiles" + "/bigThirdFile", 18);
        FileGenerator.generate(BASE_CATALOG + "/bigFiles" + "/bigFouthFile", 17);
        FileGenerator.generate(BASE_CATALOG + "/bigFiles" + "/bigFithFile", 16);
    }

    @AfterEach
    public void destroy() throws IOException {
        java.nio.file.Files.walk(Paths.get(BASE_CATALOG))
                .map(Path::toFile)
                .sorted(Comparator.comparing(File::isDirectory))
                .forEach(File::delete);
    }

    @Test
    public void testFindFileWithMaxNumberOfChar() throws IOException {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer(BASE_CATALOG);

        File etalon = new File(BASE_CATALOG + "/ssssss");

        assertThat(diskAnalyzer.findFileWithMaxNumberOfChar(CHARACTER))
                .isFile()
                .exists()
                .hasContentEqualTo(etalon);
    }

    @Test
    public void testFind5TopBigSize() throws IOException {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer(BASE_CATALOG);

        Map<File, Long> etalonMap = new HashMap<>();
        etalonMap.put(new File(BASE_CATALOG + "/bigFiles/" + "bigFirstFile"), 20480L);
        etalonMap.put(new File(BASE_CATALOG + "/bigFiles/" + "bigSecondFile"), 19456L);
        etalonMap.put(new File(BASE_CATALOG + "/bigFiles/" + "bigThirdFile"), 18432L);
        etalonMap.put(new File(BASE_CATALOG + "/bigFiles/" + "bigFouthFile"), 17408L);
        etalonMap.put(new File(BASE_CATALOG + "/bigFiles/" + "bigFithFile"), 16384L);

        assertThat(diskAnalyzer.find5TopBigSize())
                .isEqualTo(etalonMap);
    }

    @Test
    public void testGetAverageSizeOfFilesWithSubdirectories() throws IOException {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer(BASE_CATALOG);

        assertThat(diskAnalyzer.getAverageSizeOfFiles(true))
                .isEqualTo(10240.0);
    }

    @Test
    public void testGetAverageSizeOfFilesWithoutSubdirectories() throws IOException {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer(BASE_CATALOG);

        assertThat(diskAnalyzer.getAverageSizeOfFiles(false))
                .isEqualTo(3413.3333333333335);
    }

    @Test
    public void testGetSortedStatistics() throws IOException {
        DiskAnalyzer diskAnalyzer = new DiskAnalyzer(BASE_CATALOG);

        Map<Character, Long> etalonMapSortedStatistics = new HashMap<>();
        etalonMapSortedStatistics.put('b', 5L);
        etalonMapSortedStatistics.put('s', 2L);
        etalonMapSortedStatistics.put('t', 1L);
        etalonMapSortedStatistics.put('f', 3L);
        etalonMapSortedStatistics.put('l', 1L);

        assertThat(diskAnalyzer.getSortedStatistics())
                .isEqualTo(etalonMapSortedStatistics);
    }

}
