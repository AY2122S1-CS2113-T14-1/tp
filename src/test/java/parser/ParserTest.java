package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ParserTest {

    // test failed invocation of display method of an Narrative object
    @Test
    public void parserTest() {
        Parser parser = new Parser();
        String suspectFather = parser.getSuspectNameFromIndex(0, 1);
        assertEquals("Father", suspectFather);

        String suspectKevin = parser.getSuspectNameFromIndex(1, 2);
        assertEquals("Kevin", suspectKevin);

        String suspectZack = parser.getSuspectNameFromIndex(2, 5);
        assertEquals("Zack", suspectZack);
    }
}
