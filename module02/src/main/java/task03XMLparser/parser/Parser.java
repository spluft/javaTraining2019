package task03XMLparser.parser;

import task03XMLparser.model.Speech;

import java.util.List;

public interface Parser {
    Parser getInstance();

    List<Speech> parse();
}
