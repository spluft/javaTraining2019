package task03XMLparser.parser;

import org.xml.sax.SAXException;
import task03XMLparser.exception.XMLParserException;
import task03XMLparser.model.Speech;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser{
    private static SaxParser instance;

    private String path;
    private SAXParser saxParser;

    private SaxParser(String path) {
        this.path = path;
        try {
            this.saxParser = SAXParserFactory.newInstance().newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private List<Speech> startParse() throws XMLParserException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxHandler handler = new SaxHandler();
        this.saxParser.parse(new File(this.path), handler);
        return handler.getSpeechList();
    }

    @Override
    public Parser getInstance(String path) {
        if (instance == null) {
            instance = new SaxParser(path);
        }
        return instance;
    }

    @Override
    public List<Speech> parse(String path) throws XMLParserException {
        return startParse();
    }
}
