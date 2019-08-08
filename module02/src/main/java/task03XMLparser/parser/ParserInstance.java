package task03XMLparser.parser;

import task03XMLparser.model.Speech;

import java.util.List;

public enum ParserInstance implements Parser {
    SAX {
        public List<Speech> parse(String path) {
            return SaxParser.getInstance().parse(path);
        }
    },
    DOM {
        public List<Speech> parse(String path) {
            return DomParser.getInstance().parse(path);
        }
    };
}
