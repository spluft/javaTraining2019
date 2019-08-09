package task03Parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task03XMLparser.model.Speech;
import task03XMLparser.parser.ParserInstance;
import task03XMLparser.utils.CSVutils;
import task03XMLparser.utils.PlayAnaliser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    String path;
    String pathToCsvWords;
    String pathToCsvTags;

    @BeforeEach
    public void init() {
        path = "/home/spluft/Documents/hamlet.xml";
        pathToCsvWords = "/home/spluft/Documents/words.xml";
        pathToCsvTags = "/home/spluft/Documents/tags.xml";
    }

    @Test
    @DisplayName("Point 04 with Sax parser")
    public void SaxParserTest() {
        List<Speech> speeches = ParserInstance.SAX.parse(this.path);
        Map<String, Long> currentMap = PlayAnaliser.getCountUniqueWordsBySpeaker(speeches, "HAMLET");

        assertThat(currentMap)
                .containsEntry("the", 464L)
                .containsEntry("and", 325L)
                .containsEntry("this", 122L)
                .containsEntry("together", 4L)
                .containsEntry("satyr", 1L);
    }

    @Test
    @DisplayName("Point 04 with Dom parser")
    public void DomParserTest() {
        List<Speech> speeches = ParserInstance.DOM.parse(this.path);
        Map<String, Long> currentMap = PlayAnaliser.getCountUniqueWordsBySpeaker(speeches, "HAMLET");

        assertThat(currentMap)
                .containsEntry("the", 464L)
                .containsEntry("and", 325L)
                .containsEntry("this", 122L)
                .containsEntry("together", 4L)
                .containsEntry("satyr", 1L);
    }

    @Test
    @DisplayName("Point 05 with Sax parser")
    public void SaxParserGetPopularTagsTest() {
        List<String> tags = ParserInstance.SAX.getListOfTags(this.path);
        Map<String, Long> currentTagMap = PlayAnaliser.getListOfTagsByPopularity(tags);

        assertThat(currentTagMap)
                .containsEntry("LINE", 4014L)
                .containsEntry("SCNDESCR", 1L);
    }

    @Test
    @DisplayName("Point 05 with Dom parser")
    public void DomParserGetPopularTagsTest() {
        List<String> tags = ParserInstance.DOM.getListOfTags(this.path);
        Map<String, Long> currentTagMap = PlayAnaliser.getListOfTagsByPopularity(tags);

        assertThat(currentTagMap)
                .containsEntry("LINE", 4014L)
                .containsEntry("SCNDESCR", 1L);
    }

    @Test
    @DisplayName("Point 06 with Dom parser")
    public void SaxWordsToCSVTest() throws IOException {
        List<Speech> speeches = ParserInstance.SAX.parse(this.path);
        Map<String, Long> speechCurrent = PlayAnaliser.getCountUniqueWordsBySpeaker(speeches, "HAMLET");

        CSVutils.givenDataArray_whenConvertToCSV_thenOutputCreated(speechCurrent, pathToCsvWords);
    }

    @Test
    @DisplayName("Point 06 with Dom parser")
    public void DomTagsToCSVTest() throws IOException {
        List<String> tags = ParserInstance.DOM.getListOfTags(this.path);
        Map<String, Long> tagCurrent = PlayAnaliser.getListOfTagsByPopularity(tags);

        CSVutils.givenDataArray_whenConvertToCSV_thenOutputCreated(tagCurrent, pathToCsvTags);
    }
}
