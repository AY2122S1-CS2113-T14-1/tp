package scene;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;
import org.junit.jupiter.api.Test;
import storage.GameDataFileDecoder;
import storage.GameDataFileManager;
import ui.Ui;

import java.io.FileNotFoundException;

public class SceneListTest {


    /*
    @Test
    public void scenesTest() throws FileNotFoundException {
        Ui ui = new Ui();
        GameDataFileDecoder datafile = new GameDataFileDecoder(ui,new GameDataFileManager("GameData.txt"));

        datafile.setCurrentSceneIndex(0);
        SceneList sceneList = SceneListBuilder.buildSceneList(datafile);

        Scene currentScene = sceneList.getCurrentScene();
        currentScene.runScene();

        sceneList.updateSceneNumber();
        currentScene = sceneList.getCurrentScene();

        System.out.println(currentScene.getSuspectList().getSuspectAvailableClues("Father"));
        System.out.println(currentScene.getSuspectList().toString());
        System.out.println(currentScene.getSuspectList().getAllClues());

        currentScene.getSuspectList().setClueChecked("Father",
                sceneList.getCurrentScene().getSuspectList().getAllAvailableClues().get(0));
        System.out.println(currentScene.getSuspectList().getAllAvailableClues());

        System.out.println("------------------------------------------");
        currentScene.runScene();
    }*/

    @Test
    public void scene2Test() throws FileNotFoundException, DukeCorruptedFileException, DukeFileNotFoundException {
        Ui ui = new Ui();
        GameDataFileDecoder datafile = new GameDataFileDecoder("GameData.txt");

        datafile.setCurrentSceneIndex(0);
        SceneList sceneList = SceneListBuilder.buildSceneList(datafile);

        Scene currentScene = sceneList.getCurrentScene();
        currentScene.runScene();

        currentScene = sceneList.getCurrentScene();
        System.out.println("------------------------------------------");
        currentScene.runScene();

        currentScene = sceneList.getCurrentScene();
        System.out.println("------------------------------------------");
        currentScene.runScene();
    }
}
