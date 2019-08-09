package task03XMLparser.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVutils {
    public static void givenDataArray_whenConvertToCSV_thenOutputCreated(Map<String, Long> data, String pathToCSV) throws IOException {
        File csvOutputFile = new File(pathToCSV);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            String str = data.entrySet().stream()
                    .flatMap(e -> Stream.of(e.getKey() + ":" + e.getValue()))
                    .collect(Collectors.joining(","));
            pw.print(str);
        }
    }
}
