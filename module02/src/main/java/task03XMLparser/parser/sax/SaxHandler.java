package task03XMLparser.parser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import task03XMLparser.model.EntityType;
import task03XMLparser.model.Speech;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SaxHandler extends DefaultHandler {

    private EntityType entityType;
    private Speech speech;
    private StringBuilder value;
    private StringBuilder speechText;

    private List<Speech> speechList = new ArrayList<>();
    private List<String> tagList = new ArrayList<>();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        entityType = EntityType.NULL;
        value = new StringBuilder();
        switch (qName) {
            case "SPEECH":
                entityType = EntityType.SPEECH;
                speech = new Speech();
                speechText = new StringBuilder();
                tagList.add(qName);
                break;
            case "SPEAKER":
                entityType = EntityType.SPEAKER;
                tagList.add(qName);
                break;
            case "LINE":
                entityType = EntityType.LINE;
                tagList.add(qName);
                break;
            default:
                tagList.add(qName);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        value = value.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (entityType) {
            case SPEAKER:
                speech.setSpeaker(value.toString());
                break;
            case LINE:
                speechText.append(value).append('\n');
                break;
            case SPEECH:
                speech.setLine(speechText.toString());
                speechList.add(speech);
                break;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End parse XML.");
    }

    public List<Speech> getSpeechList() {
        return this.speechList;
    }

    public List<String> getTagList() {
        return this.tagList;
    }
}
