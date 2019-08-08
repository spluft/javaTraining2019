package task03XMLparser.utils;

import task03XMLparser.model.Speech;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PlayAnaliser {

    public static Map<String, Long> getCountUniqueWordsBySpeaker(List<Speech> speechList, String speaker) {
        return getAllWordsBySpeaker(speechList, speaker)
                .stream()
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> {
                            throw new IllegalStateException();
                        },
                        LinkedHashMap::new
                ));
    }

    public static Map<String, Long> getListOfTagsByPopularity() {

    }

    private static List<String> getAllWordsBySpeaker(List<Speech> speechList, String speaker) {
        List<String> words = new ArrayList<>();
        for (Speech speech : speechList) {
            if (speech.getSpeaker().equals(speaker)) {
                words.addAll(
                        Arrays.asList(
                                speech.getLine()
                                        .replaceAll("[^A-Za-z0-9_ ]", " ")
                                        .replace("  ", " ")
                                        .split("\\s")));
            }
        }
        return words;
    }
}
