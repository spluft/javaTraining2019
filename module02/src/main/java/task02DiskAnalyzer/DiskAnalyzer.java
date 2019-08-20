package task02DiskAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger LOG = LogManager.getLogger(DiskAnalyzer.class);

    String path;

    public DiskAnalyzer(String path) {
        this.path = path;
    }

    public File findFileWithMaxNumberOfChar(char letter) {
        return getFileList(TypeOfAction.RECURSIVE_FIND_FILE)
                .stream()
                .collect(Collectors.toMap(
                        File::getAbsoluteFile,
                        file -> file.getName()
                                .chars()
                                .filter(ch -> ch == letter)
                                .count()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

    }

    public Map<File, Long> find5TopBigSize() {
        return getFileList(TypeOfAction.RECURSIVE_FIND_FILE)
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

    public double getAverageSizeOfFiles(Boolean isWithSubdirs) {
        if (isWithSubdirs) {
            return getFileList(TypeOfAction.RECURSIVE_FIND_FILE)
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
        return getFileList(TypeOfAction.NOT_RECURSIVE_FINDE_FILE)
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

    public Map<Character, Long> getSortedStatistics() {
        List<File> fileList = getFileList(TypeOfAction.RECURSIVE_FIND_FILE);
        return fileList.stream()
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

    private List<File> getFileList(TypeOfAction type){
        Result result;
        switch(type){
            case NOT_RECURSIVE_FINDE_FILE:
                result = notRecursivelyFindFile(this.path);
                break;
            case RECURSIVE_FIND_FILE:
                result = recursivelyFindFile(this.path);
                break;
            case RECURSIVE_FIND_PATHS:
                result = recursivelyFindPaths(this.path);
                break;
            default:
                result = new Result(true, new NullPointerException());
                break;
        }
        if(result.isErrorOccurs()) {
            LOG.error("Error. App will close");
            LOG.error(result.getException());
            System.exit(0);
        }
        return result.get();
    }

    private Result recursivelyFindPaths(String path) {
        Result result;
        try {
            List<File> fileList = Files.walk(Paths.get(path))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            result = new Result(fileList);
        } catch (IOException e) {
            LOG.warn("Exception when recursively find paths", e);
            result = new Result(true, e);
        }

        return result;
    }

    private Result recursivelyFindFile(String path) {
        Result result;
        try {
            List<File> fileList = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            result = new Result(fileList);
        } catch (IOException e) {
            LOG.warn("Exception when recursively find file", e);
            result = new Result(true, e);
        }
        return result;
    }

    private Result notRecursivelyFindFile(String path) {
        Result result;
        try {
            List<File> fileList = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            result = new Result(fileList);
        } catch (IOException e) {
            LOG.error("Exception when not recursively find file", e);
            result = new Result(true, e);
        }
        return result;
    }
}