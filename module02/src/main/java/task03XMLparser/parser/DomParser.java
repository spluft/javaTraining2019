package task03XMLparser.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import task03XMLparser.model.EntityType;
import task03XMLparser.model.Speech;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {
    private Logger logger = LogManager.getLogger(DomParser.class);

    private static DomParser instance;


    public DomParser() {
    }

    private List<Speech> startParse(String path) {
        Element play = null;
        play = getDocument(path);
        List<Speech> speeches = new ArrayList<>();
        NodeList speechNodes = play.getElementsByTagName(String.valueOf(EntityType.SPEECH));
        for (int i = 0; i < speechNodes.getLength(); i++) {
            Element speechElement = (Element) speechNodes.item(i);
            Speech speech = getSpeech(speechElement);
            speeches.add(speech);
        }
        return speeches;
    }

    private Element getDocument(String path) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document document = null;
        try {
            db = dbf.newDocumentBuilder();
            document = db.parse(new File(path));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return document.getDocumentElement();
    }

    private Speech getSpeech(Element speechElement) {
        Speech speech = new Speech();
        speech.setSpeaker(getSpeakerName(speechElement));
        speech.setLine(getLine(speechElement));
        return speech;
    }

    private static String getSpeakerName(Element element) {
        NodeList nodeList = element.getElementsByTagName(String.valueOf(EntityType.SPEAKER));
        Element child = (Element) nodeList.item(0);
        return child.getTextContent().trim();
    }

    private static String getLine(Element element) {
        NodeList nodeList = element.getElementsByTagName(String.valueOf(EntityType.LINE));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element line = (Element) nodeList.item(i);
            sb.append(line.getTextContent().trim());
            sb.append('\n');
        }
        return sb.toString();
    }

    public static Parser getInstance() {
        if (instance == null) {
            instance = new DomParser();
        }
        return instance;
    }

    @Override
    public List<Speech> parse(String path) {
        return startParse(path);
    }
}
