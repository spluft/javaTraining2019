package task02DiskAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiskAnalyzer {

    String path;

    public DiskAnalyzer(String path) {
        this.path = path;
    }

    public File findFileWithMaxNumberOfChar(char letter) throws IOException {
        return recursivelyFindFile(this.path)
                .stream()
                .collect(Collectors.toMap(
                        File::getAbsoluteFile,
                        file -> file.getName()
                                .chars()
                                .filter(ch -> ch == letter)
                                .count()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

    }

    public Map<File, Long> find5TopBigSize() throws IOException {
        return recursivelyFindFile(this.path)
                .stream()
                .collect(Collectors.toMap(
                        File::getAbsoluteFile,
                        file -> Stream.of(file.length())
                                .reduce(Long.MIN_VALUE, Long::max)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public double getAverageSizeOfFiles(Boolean isWithSubdirs) throws IOException {
        if (isWithSubdirs) {
            return recursivelyFindFile(this.path)
                    .stream()
                    .collect(Collectors.toMap(
                            File::getAbsoluteFile,
                            file -> Stream.of(file.length())
                                    .reduce(Long.MIN_VALUE, Long::max)))
                    .entrySet().stream().sorted(Map.Entry.<File, Long>comparingByValue().reversed())
                    .mapToLong(Map.Entry::getValue)
                    .average()
                    .getAsDouble();
        }
        return notRecursivelyFindFile(this.path)
                .stream()
                .collect(Collectors.toMap(
                        File::getAbsoluteFile,
                        file -> Stream.of(file.length())
                                .reduce(Long.MIN_VALUE, Long::max)))
                .entrySet().stream().sorted(Map.Entry.<File, Long>comparingByValue().reversed())
                .mapToLong(Map.Entry::getValue)
                .average()
                .getAsDouble();
    }

    public Map<Character, Long> getSortedStatistics() throws IOException {
        return recursivelyFindFile(this.path)
                .stream()
                .flatMap(s -> Stream.of(s.getName()))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(s -> s.toString().charAt(0), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private List<File> recursivelyFindPaths(String path) throws IOException {
        return Files.walk(Paths.get(path))
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    private List<File> recursivelyFindFile(String path) throws IOException {
        return Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    private List<File> notRecursivelyFindFile(String path) throws IOException {
        return Files.list(Paths.get(path))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }
}