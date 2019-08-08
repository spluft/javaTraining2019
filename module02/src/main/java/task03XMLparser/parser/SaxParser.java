package task03XMLparser.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import task03XMLparser.model.Speech;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {
    private Logger logger = LogManager.getLogger(SaxParser.class);

    private static SaxParser instance;

    private SAXParser saxParser;

    SaxParser() {
        try {
            this.saxParser = SAXParserFactory.newInstance().newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    private List<Speech> startParse(String path) {
        SaxHandler handler = new SaxHandler();
        try {
            this.saxParser.parse(new File(path), handler);
        } catch (SAXException | IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return handler.getSpeechList();
    }

    public static Parser getInstance() {
        if (instance == null) {
            instance = new SaxParser();
        }
        return instance;
    }

    @Override
    public List<Speech> parse(String path) {
        return startParse(path);
    }
}
