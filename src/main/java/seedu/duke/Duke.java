package seedu.duke;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;
import scene.clue.CheckedClueTrackerBuilder;
import command.InvalidCommand;
import exceptions.InvalidInputException;
import exceptions.InvalidClueException;
import exceptions.InvalidSuspectException;
import exceptions.MissingSceneFileException;
import investigation.Investigation;
import note.NoteList;
import scene.SceneList;
import scene.SceneListBuilder;
import storage.GameDataFileDecoder;
import storage.GameDataFileManager;
import scene.suspect.SuspectList;
import ui.Ui;
import parser.Parser;
import command.Command;

import java.io.FileNotFoundException;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static Ui ui;
    private static Parser parser;
    private static Investigation investigation;
    private static GameDataFileDecoder dataFile;
    private static SceneList sceneList;
    private static SuspectList clueTracker;
    private static final String GAME_DATA_FILE_NAME = "data.txt";
    private static NoteList notes;

    public static void initializeGame() {
        // Initialise new parser object
        parser = new Parser();

        // Initialise a new Ui object
        ui = new Ui();
        ui.printWelcomeMessage();

        try {
            dataFile = new GameDataFileDecoder(GAME_DATA_FILE_NAME);
            sceneList = SceneListBuilder.buildSceneList(dataFile);
            clueTracker = CheckedClueTrackerBuilder.buildClueTracker();
            investigation = new Investigation(clueTracker);
            sceneList.runCurrentScene();
        } catch (MissingSceneFileException e) {
            ui.printMissingSceneFileMessage();
        } catch (DukeFileNotFoundException e) {
            ui.printFileErrorMessage();
        } catch (DukeCorruptedFileException e) {
            ui.printCorruptedFileMessage();
        }

    }

    public static void main(String[] args) {
        initializeGame();
        runUntilExitCommand();
    }

    private static void runUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.printCurrentInvestigation(investigation, sceneList);
                String userInput = ui.readUserInput();
                Command commandFromUser = new InvalidCommand();
                commandFromUser = parser.getCommandFromUser(userInput);
                commandFromUser.execute(ui, investigation, sceneList);
                isExit = commandFromUser.exit();
            } catch (InvalidSuspectException e1) {
                ui.printInvalidSuspectMessage();
            } catch (InvalidClueException e2) {
                ui.printInvalidClueMessage();
            } catch (InvalidInputException | NumberFormatException e3) {
                ui.printInvalidCommandMessage();
            } catch (DukeCorruptedFileException e) {
                ui.printCorruptedFileMessage();
            } catch (DukeFileNotFoundException | NullPointerException e) {
                ui.printCorruptedFileMessage();
                isExit = true;
            }
        }
    }
}
