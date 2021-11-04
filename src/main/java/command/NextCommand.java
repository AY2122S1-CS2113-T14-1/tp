package command;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;
import investigation.Investigation;
import scene.SceneList;
import scene.SceneTypes;
import ui.Ui;

public class NextCommand extends Command {
    private boolean hasCompleted = false;

    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) throws DukeCorruptedFileException, DukeFileNotFoundException {
        SceneTypes sceneType = sceneList.getCurrentSceneType();
        switch (sceneType) {
        case CORRECT_KILLER_SCENE:
            hasCompleted = true;
            break;
        case WRONG_KILLER_SCENE:
            sceneList.updateSceneNumber();
            sceneList.runCurrentScene();
            break;
        case TRUTH_SCENE:
            hasCompleted = true;
            sceneList.runCurrentScene();
            break;
        case GUESS_KILLER_SCENE:
            ui.printEnterKillerName();
            break;
        default:
            investigation.setSuspectStage();
            sceneList.updateSceneNumber();
            sceneList.runCurrentScene();
        }
    }

    @Override
    public boolean exit() {
        return this.hasCompleted;
    }
}
