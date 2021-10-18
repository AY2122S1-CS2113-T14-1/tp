package investigation;

import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import parser.Parser;
import scene.Scene;
import scene.SceneList;
import scene.SceneListBuilder;
import suspect.Suspect;
import ui.Ui;

import java.io.FileNotFoundException;

public class Investigation {
    private static InvestigationStages stage;
    private static SceneList sceneList;
    private static Scene currentScene;
    private static String currentSuspect;
    private static Parser parser;
    private static Ui ui;

    private static final String FILE_NOT_FOUND = "File not found for scene";
    private static final String WRONG_INDEX_GIVEN = "Sorry please enter index within range";
    private static final String ENTER_VALID_COMMAND = "Please enter a valid user command";
    private static final String INVALID_COMMAND = "Invalid command";

    private static final String KILLER_WENDY = "Wendy";

    public Investigation(Parser parser, Ui ui) {
        this.parser = parser;
        this.ui = ui;
        stage = InvestigationStages.SUSPECT_STAGE;
        sceneList = SceneListBuilder.buildSceneList(ui);

        currentScene = sceneList.getCurrentScene();
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }

    public void printCurrentInvestigation() {
        if (stage == InvestigationStages.SUSPECT_STAGE) {
            System.out.println("Scene " + (sceneList.getCurrentSceneIndex() + 1) + " Investigation");
            System.out.println("Who do you want to investigate?");
            ui.printSuspects(currentScene.getSuspectList());
        } else {
            System.out.print("Scene " + (sceneList.getCurrentSceneIndex() + 1) + " Investigation");
            System.out.println(" - " + currentSuspect);
            System.out.println("0. Go back to list of suspects");
            Suspect suspect = currentScene.investigateSuspect(currentSuspect);
            ui.printListOfClues(suspect.getClues());
        }
        ui.printHelpCommandIndicator();
    }


    public void investigateScene(Integer index) throws InvalidSuspectException, InvalidClueException {
        switch (stage) {
        case SUSPECT_STAGE:
            currentSuspect = parser.getSuspectNameFromIndex(sceneList.getCurrentSceneIndex(), index);
            stage = InvestigationStages.CLUE_STAGE;
            break;
        case CLUE_STAGE:
            int suspectNumClues = currentScene.investigateSuspect(currentSuspect).getNumClues();
            if (index > suspectNumClues) {
                throw new InvalidClueException(WRONG_INDEX_GIVEN);
            } else if (index == 0) {
                stage = InvestigationStages.SUSPECT_STAGE;
            } else {
                System.out.println(currentScene.investigateSuspect(currentSuspect).getClues().get(index - 1));
            }
            break;
        default:
            System.out.println(INVALID_COMMAND);
        }
    }

    public boolean completedGame() {
        boolean isLastScene = getNextSceneFromSceneList();
        if (isLastScene) {
            ui.printSuspectKillerMessage();
            String suspectedKiller = ui.readUserInput();
            return checkSuspectedKiller(suspectedKiller);
        } else {
            return false;
        }
    }

    private boolean checkSuspectedKiller(String suspectedKiller) {
        if (suspectedKiller.equals(KILLER_WENDY)) {
            ui.printCorrectMessage();
            return true;
        } else {
            ui.printWrongMessage();
            return false;
        }
    }

    public boolean getNextSceneFromSceneList() {
        return sceneList.nextScene();
    }

    public void runScenes() {
        currentScene = sceneList.getCurrentScene();
        stage = InvestigationStages.SUSPECT_STAGE;
        try {
            currentScene.runScene();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }
}
