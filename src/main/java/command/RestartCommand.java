package command;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;
import investigation.Investigation;
import scene.SceneList;
import ui.Ui;

public class RestartCommand extends Command {
    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) throws DukeCorruptedFileException, DukeFileNotFoundException {
        investigation.restartGame();
        sceneList.resetAllScenes();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
