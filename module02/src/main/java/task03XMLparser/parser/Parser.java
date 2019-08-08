package task03XMLparser.parser;

import task03XMLparser.model.Speech;

import java.util.List;

public interface Parser {
    List<Speech> parse(String path);
    List<String> getListOfTags(String path);
}
