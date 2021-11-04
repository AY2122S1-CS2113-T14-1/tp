package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;
import exceptions.InvalidInputException;
import exceptions.MissingSceneFileException;
import org.junit.jupiter.api.Test;
import scene.SceneList;
import scene.SceneListBuilder;
import storage.GameDataFileDecoder;
import storage.GameDataFileManager;
import ui.Ui;

public class ParserTest {

    // test failed invocation of display method of an Narrative object
    @Test
    public void parserTest() throws MissingSceneFileException, DukeCorruptedFileException, DukeFileNotFoundException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        GameDataFileDecoder dataFile = new GameDataFileDecoder("GameData.txt");
        SceneList sceneList = SceneListBuilder.buildSceneList(dataFile);

        sceneList.updateSceneNumber();
        String suspectFather = parser.getSuspectNameFromIndex(sceneList.getCurrentScene(), 1);
        assertEquals("Father", suspectFather);


        sceneList.updateSceneNumber();
        String suspectKevin = parser.getSuspectNameFromIndex(sceneList.getCurrentScene(), 2);
        assertEquals("Kevin", suspectKevin);

        sceneList.updateSceneNumber();
        String suspectZack = parser.getSuspectNameFromIndex(sceneList.getCurrentScene(), 5);
        assertEquals("Zack", suspectZack);

        assertThrows(InvalidInputException.class, () -> parser.getCommandFromUser(""));
    }
}
