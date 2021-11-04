package command;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;
import investigation.Investigation;
import scene.SceneList;
import scene.SceneTypes;
import ui.Ui;

public class BackCommand extends Command {
    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) throws DukeCorruptedFileException, DukeFileNotFoundException {
        SceneTypes sceneType = sceneList.getCurrentSceneType();
        if (sceneType != SceneTypes.INVESTIGATE_SCENE) {
            investigation.setSuspectStage();
            sceneList.previousScene();
        } else {
            investigation.setSuspectStage();
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
