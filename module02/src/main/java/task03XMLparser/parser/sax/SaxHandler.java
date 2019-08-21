package task03XMLparser.parser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import task03XMLparser.model.Speech;

import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {

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
        //value should be reset every new opening tag
        value = new StringBuilder();
        switch (qName) {
            case "SPEECH":
                speech = new Speech();
                speechText = new StringBuilder();
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
        switch (qName) {
            case "SPEAKER":
                speech.setSpeaker(value.toString());
                break;
            case "LINE":
                speechText.append(value).append('\n');
                break;
            case "SPEECH":
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
