package task03Parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task03XMLparser.model.Speech;
import task03XMLparser.parser.ParserInstance;
import task03XMLparser.utils.PlayAnaliser;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    String path;

    @BeforeEach
    public void init() {
        path = "/home/spluft/Documents/hamlet.xml";
    }

    @Test
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
    public void SaxParserGetPopularTagsTest() {
        List<String> tags = ParserInstance.SAX.getListOfTags(this.path);
        Map<String, Long> currentTagMap = PlayAnaliser.getListOfTagsByPopularity(tags);

        assertThat(currentTagMap)
                .containsEntry("LINE", 4014L)
                .containsEntry("SCNDESCR", 1L);
    }

    @Test
    public void DomParserGetPopularTagsTest() {
        List<String> tags = ParserInstance.DOM.getListOfTags(this.path);
        Map<String, Long> currentTagMap = PlayAnaliser.getListOfTagsByPopularity(tags);

        assertThat(currentTagMap)
                .containsEntry("LINE", 4014L)
                .containsEntry("SCNDESCR", 1L);
    }
}
