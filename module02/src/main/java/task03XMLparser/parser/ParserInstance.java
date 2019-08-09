package task03XMLparser.parser;

import task03XMLparser.model.Speech;
import task03XMLparser.parser.dom.DomParser;
import task03XMLparser.parser.sax.SaxParser;

import java.util.List;

public enum ParserInstance implements Parser {
    SAX {
        public List<Speech> parse(String path) {
            return SaxParser.getInstance().parse(path);
        }

        public List<String> getListOfTags(String path) {
            return SaxParser.getInstance().getListOfTags(path);
        }
    },
    DOM {
        public List<Speech> parse(String path) {
            return DomParser.getInstance().parse(path);
        }
        public List<String> getListOfTags(String path) {
            return DomParser.getInstance().getListOfTags(path);
        }
    };
}
