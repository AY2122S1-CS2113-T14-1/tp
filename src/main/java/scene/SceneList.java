package scene;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;
import storage.GameDataFileDecoder;
import ui.Ui;
import java.io.FileNotFoundException;

public class SceneList {
    private static final Ui ui = new Ui();
    private Scene[] scenes;
    private int currentSceneIndex;
    private static final int STARTING_INDEX_FOR_FILE = 0;
    private static final int INTRODUCTION_SCENE_INDEX = 0;
    private static final int GUESS_KILLER_SCENE_INDEX = 4;
    private static final int CORRECT_KILLER_SCENE_INDEX = 5;
    private static final int WRONG_KILLER_SCENE_INDEX = 6;
    GameDataFileDecoder dataFile;

    /**
     * Creates a SceneList that contains an array of scenes, with its currentSceneIndex obtained from the dataFile.
     * The scenes in the array will be arranged based on the order they were taken in the parameter.
     *
     * @param dataFile Game data file.
     * @param scenes The scenes that are to be contained in this SceneList
     */
    public SceneList(GameDataFileDecoder dataFile, Scene... scenes) throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.dataFile = dataFile;
        this.currentSceneIndex = dataFile.getCurrentSceneIndex();
        this.scenes = scenes;
    }

    public void setSceneNumberAfterSuspecting(boolean killerFound) throws DukeCorruptedFileException, DukeFileNotFoundException {
        if (killerFound) {
            this.currentSceneIndex = CORRECT_KILLER_SCENE_INDEX;
            dataFile.setCurrentSceneIndex(INTRODUCTION_SCENE_INDEX);
        } else {
            this.currentSceneIndex = WRONG_KILLER_SCENE_INDEX;
        }
    }

    public Scene getCurrentScene() {
        assert currentSceneIndex <= 7;
        return this.scenes[currentSceneIndex];
    }

    private void resetToIntroductionScene() throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.currentSceneIndex = STARTING_INDEX_FOR_FILE;
        updateDataFileSceneIndex(STARTING_INDEX_FOR_FILE);
    }

    public int getCurrentSceneIndex() {
        return this.currentSceneIndex;
    }

    public void updateSceneNumber() throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.currentSceneIndex++;
        assert currentSceneIndex <= 7;
        updateDataFileSceneIndex(currentSceneIndex);
    }

    public SceneTypes getCurrentSceneType() {
        Scene currentScene = this.getCurrentScene();
        return currentScene.getSceneType();
    }

    public void runCurrentScene() {
        Scene currentScene = this.getCurrentScene();
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            ui.printFileErrorMessage();
        }
    }

    public void resetAllScenes() throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.resetToIntroductionScene();
        runCurrentScene();
    }

    private void resetToGuessKillerScene() throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.currentSceneIndex = GUESS_KILLER_SCENE_INDEX;
        updateDataFileSceneIndex(GUESS_KILLER_SCENE_INDEX);
    }

    private void goBackOneScene() throws DukeCorruptedFileException, DukeFileNotFoundException {
        this.currentSceneIndex--;
        assert this.currentSceneIndex >= 0;
        updateDataFileSceneIndex(currentSceneIndex);
    }

    private void updateDataFileSceneIndex(int sceneIndex) throws DukeCorruptedFileException, DukeFileNotFoundException {
        dataFile.setCurrentSceneIndex(sceneIndex);
    }

    private void decreaseSceneNumber() throws DukeCorruptedFileException, DukeFileNotFoundException {
        // We do not allow users to go back to any scene with
        // scene number less than 0

        SceneTypes sceneType = getCurrentSceneType();
        switch (sceneType) {
        case INTRODUCTION_SCENE:
            break;
        case WRONG_KILLER_SCENE:
            //fallthrough
        case CORRECT_KILLER_SCENE:
            this.resetToGuessKillerScene();
            break;
        default:
            this.goBackOneScene();
        }
    }

    public void previousScene() throws DukeCorruptedFileException, DukeFileNotFoundException {
        decreaseSceneNumber();
        runCurrentScene();
    }
}
