package ui;

import scene.clue.Clue;
import investigation.Investigation;
import investigation.InvestigationStages;
import scene.Scene;
import scene.SceneList;
import scene.SceneTypes;
import scene.suspect.SuspectList;
import note.Note;
import note.NoteList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String GAME_NAME = "Classic Adventure Text Game";
    private static final String WELCOME_MESSAGE = "Welcome to the " + GAME_NAME + "!\n";
    private static final String GOODBYE_MESSAGE = "Goodbye.";
    private static final String LIST_OF_COMMAND_AVAILABLE_MESSAGE =
            "Here are the list of commands available to you.";
    private static final String LIST_OF_NOTES_MESSAGE =
            "Here are the list of notes available to you.";
    private static final String LINE_SEPARATOR = "==============================";
    private static final String SELECTED_NOTES_MESSAGE =
            "Here are the list of notes found given keywords:";
    private static final String DELETE_NOTE_MESSAGE =
            "Ok! The note has been successfully deleted!";
    private static final String SAVE_NOTE_MESSAGE =
            "Ok! The new note has been successfully created and saved.";
    private static final String WHO_KILLED_YOU = "Who do you think killed you?";
    private static final String NARRATIVE_LINES_COMMAND =
            "\"/narrative-lines #NUM\" - change number of narrative lines print each time to #NUM";
    private static final String HELP_COMMAND = "\"/help\" - view this command list";
    private static final String EXIT_COMMAND = "\"/exit\" - exit the game";
    private static final String NEXT_COMMAND = "\"/next\" - move on to the next scene or the next stage of a scene";
    private static final String BACK_COMMAND = "\"/back\" - go back to previous scene";
    private static final String NOTE_COMMAND = "\"/note\" - create a new note / open a note / delete a note";
    private static final String VIEW_COMMAND = "\"/view\" - view all the clues that you have gathered";
    private static final String RESTART_COMMAND = "\"/restart\" - restart the game from beginning";

    private static final String INVALID_INPUT_GIVEN = "Invalid input! Type '/help' to see the available commands.";
    private static final String INVALID_NUMBER_SUSPECT =
            "Invalid number! To select a suspect, please input its corresponding number.";
    private static final String INVALID_NUMBER_CLUE =
            "Invalid number! To select a clue, please input its corresponding number.";
    private static final String ASK_FOR_CLUE_OR_SUSPECT_NUMBER =
            "To investigate suspects or clues, please input their corresponding number.";
    private static final String LIST_ALL_NOTES_MESSAGE = "Here is the note you want:";
    private static final String VIEWING_CHECKED_CLUES_MESSAGE = "Preparing the clues that you have gathered...\n";
    private static final String FILE_NOT_FOUND = "File not Found";
    private static final String ALL_SUSPECT_MESSAGE = "Here are all the suspects";
    private static final String WHO_TO_INVESTIGATE_MESSAGE = "Who do you want to investigate?";
    private static final String NEXT_SCENE = "Enter \"/next\" to go to the next scene.";
    private static final String SCENE_FILE_MISSING_MESSAGE =
            "File containing number of scene and its order is missing";
    private static final String INVALID_INDEX = "Invalid index";
    private static final String DELETE_ALL_NOTE = "Ok! All notes have been deleted";
    private static final String NO_NOTE_MESSAGE = "There is no note now, try to add one!";
    private static final String CHOOSE_SUSPECT_OR_CLUE_INDEX =
            "Key in the index (e.g. 1, 2) in front of the suspect/clue you want to investigate";
    private static final String ENTER_SUSPECT_NAME = "Please enter a valid suspect name!";
    private static final String NO_SEARCHED_CLUES_FOR_ALL = "You have not gathered any clues for anyone.";
    private static final String NO_SEARCHED_CLUES_FOR_ONE = "You have not gathered any clues for ";

    private Scanner scanner;

    public void printEmptyLine() {
        System.out.println(LINE_SEPARATOR);
    }

    private static final String ASK_USER_RETYPE_KILLER_NAME =
            "Invalid suspect name given. Please enter one of the suspect name below.";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    public void printExitMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void printListOfCommands() {
        System.out.println(LIST_OF_COMMAND_AVAILABLE_MESSAGE);
        System.out.println(NARRATIVE_LINES_COMMAND);
        System.out.println(HELP_COMMAND);
        System.out.println(NEXT_COMMAND);
        System.out.println(BACK_COMMAND);
        System.out.println(NOTE_COMMAND);
        System.out.println(VIEW_COMMAND);
        System.out.println(RESTART_COMMAND);
        System.out.println(CHOOSE_SUSPECT_OR_CLUE_INDEX);
        System.out.println(ASK_FOR_CLUE_OR_SUSPECT_NUMBER);
        System.out.println(EXIT_COMMAND);
    }

    public void printListOfClues(ArrayList<Clue> clues) {
        int i = 0;
        for (Clue clue : clues) {
            System.out.println((i + 1) + ". " + clue.getClueName().trim());
            i++;
        }
    }

    public void printListOfSearchedClues(ArrayList<Clue> clues) {
        int i = 0;
        for (Clue clue : clues) {
            if (clue.isChecked()) {
                System.out.println((i + 1) + ". " + clue.getClueName().trim());
                i++;
            }
        }
    }
    public void printCorruptedFileMessage() {
        System.out.println("Please do not mess with the data file");
    }
    public void printCurrentInvestigation(Investigation investigation, SceneList sceneList) {

        if (investigation.getStage() == InvestigationStages.SUSPECT_STAGE) {
            this.printCurrentSuspectPage(sceneList);
        } else {
            this.printInvestigationMessage(sceneList.getCurrentSceneIndex());
            this.printCurrentCluePage(investigation.getCurrentSuspectName(), sceneList.getCurrentScene());
            this.printGoNextSceneMessage();
        }
    }

    public void printCurrentSuspectPage(SceneList sceneList) {
        if (sceneList.getCurrentSceneType() == SceneTypes.INVESTIGATE_SCENE) {
            this.printInvestigationMessage(sceneList.getCurrentSceneIndex());
            this.printWhoToInvestigate();
            this.printSuspects(sceneList.getCurrentScene().getSuspectList());
        }
    }

    public void printCurrentCluePage(String suspectName, Scene scene) {
        System.out.println(" - " + suspectName);
        System.out.println("0. Go back to list of suspects");
        this.printListOfClues(scene.investigateSuspect(suspectName).getClues());
    }

    public void printViewingCheckedCluesMessage() {
        System.out.println(VIEWING_CHECKED_CLUES_MESSAGE);
    }

    //@@author peng-217
    public void printSaveNoteMessage() {
        System.out.println(SAVE_NOTE_MESSAGE);
    }

    public void printExistingNotes(NoteList notes, int orderIndex) {
        System.out.println(LIST_ALL_NOTES_MESSAGE);
        System.out.println("scene " + notes.getIndexNote(orderIndex - 1).getNoteSceneIndex());
        System.out.println(notes.getIndexNote(orderIndex - 1).getNoteTitle());
        System.out.println(notes.getIndexNote(orderIndex - 1).getNoteContent());
        System.out.println(LINE_SEPARATOR);
    }

    public boolean printOpenNoteMessage(NoteList notes) {
        boolean checkExistance = printNoteTitle(notes);
        if (checkExistance) {
            System.out.println("Do you want to search a note (type in 'search') or "
                    + "directly open a note (type in 'open')?");
        }
        return checkExistance;
    }

    public void printAllNotes(NoteList notes) {
        for (int i = 0; i < notes.getSize(); i++) {
            System.out.println((i + 1) + "." + notes.getIndexNote(i).getNoteTitle());
        }
    }

    public void printDeleteNoteMessage() {
        System.out.println(DELETE_NOTE_MESSAGE);
    }

    public boolean printNoteTitle(NoteList notes) {
        boolean checkExistance = false;
        if (notes.getSize() == 0) {
            System.out.println(NO_NOTE_MESSAGE);
            System.out.println();
            return checkExistance;
        } else {
            System.out.println(LIST_OF_NOTES_MESSAGE);
            for (int i = 0; i < notes.getSize(); i++) {
                System.out.println((i + 1) + "." + " " + notes.getIndexNote(i).getNoteTitle());
            }
            checkExistance = true;
            return checkExistance;
        }

    }

    public void printSelectedNote(ArrayList<Note> result) {
        System.out.println(SELECTED_NOTES_MESSAGE);
        for (int i = 0; i < result.size(); i++) {
            System.out.println((i + 1) + "." + " " + "scene " + result.get(i).getNoteSceneIndex());
            System.out.println(result.get(i).getNoteTitle());
            System.out.println(result.get(i).getNoteContent());
        }
        System.out.println();
        System.out.println(LINE_SEPARATOR);
    }
    //@@author

    public void printSuspects(SuspectList suspects) {
        System.out.println(suspects.toString());
    }

    public void printSuspectKillerMessage() {
        System.out.println(WHO_KILLED_YOU);
    }

    public void printFileErrorMessage() {
        System.out.println(FILE_NOT_FOUND);
    }

    public void printInvalidClueMessage() {
        System.out.println(INVALID_NUMBER_CLUE);
    }

    public void printInvalidSuspectMessage() {
        System.out.println(INVALID_NUMBER_SUSPECT);
    }

    public void printInvalidCommandMessage() {
        System.out.println(INVALID_INPUT_GIVEN);
    }

    public void printMissingSceneFileMessage() {
        System.out.println(SCENE_FILE_MISSING_MESSAGE);
    }

    public void printAllSuspectsMessage() {
        System.out.println(ALL_SUSPECT_MESSAGE);
    }

    public void printInvestigationMessage(int sceneNumber) {
        System.out.println("-------------------------");
        System.out.println("| Scene " + sceneNumber + " Investigation |");
        System.out.println("-------------------------");
    }

    public void printWhoToInvestigate() {
        System.out.println(WHO_TO_INVESTIGATE_MESSAGE);
    }

    public void printGoNextSceneMessage() {
        System.out.println(NEXT_SCENE);
    }

    public void printIndexCommand() {
        System.out.println(INVALID_INDEX);
    }

    public void printAskUserEnterSuspectName() {
        System.out.println(ASK_USER_RETYPE_KILLER_NAME);
    }

    public void printAllSuspectInCurrentScene(Scene scene) {
        printAllSuspectsMessage();
        printSuspects(scene.getSuspectList());
        printSuspectKillerMessage();
    }

    public void printSuccessChangeNarrativeLines(int numLines) {
        System.out.println("Successfully changed number of narrative lines to print each time to " + numLines);
    }

    public void printSelectedClue(Clue currentClueInScene) {
        System.out.println(currentClueInScene.toString());
    }


    //@@author peng-217
    public void printNoteInstructions() {
        System.out.println("Do you want to create a new note"
                + " or open an existing note or delete note?");
        System.out.println("Please type in:");
        System.out.println("'1' for create a new note.");
        System.out.println("'2' for open an existing note.");
        System.out.println("'3' for delete notes.");
    }

    public void printNoteTitleInstructions() {
        System.out.println("Please enter the title for this note"
                + " (if you do not need title, type a spacing instead):");
    }

    public void printNoteTextInstructions() {
        System.out.println("Please enter your note:");
    }

    public void printNoteOpenSearchInstructions() {
        System.out.println("Do you want to search a note (type in 'search') or "
                + "directly open a note (type in 'open')?");
    }

    public void printNoteSearchInstructions() {
        System.out.println("Do you want to search by keyword (type 'keyword') or scene index (type 'index')?");
    }

    public void printNoteSearchKeyWordInstructions() {
        System.out.println("Please enter keywords");
    }

    public void printNoteSearchSceneIndexInstructions() {
        System.out.println("Please enter scene index:");
    }


    public void printNoteOpenInstructions() {
        System.out.println("Please type in the index of the note to open it:");
    }

    public void printNoteListStarter() {
        System.out.println("Here are the notes you have: ");
    }

    public void printNoteDeleteInstructions() {
        System.out.println("Please enter the index of the note you want to delete "
                + "(type 'all' if you want delete all notes).");
    }

    public void printDeleteAllNoteMessage() {
        System.out.println(DELETE_ALL_NOTE);
        System.out.println();
    }

    public void printNoteMissingError(int size) {
        System.out.println("Invalid index! There are only " + size + " notes currently."
                + "\n");
    }

    public void printNoteErrorMessage(String errorMessage) {
        System.out.println(errorMessage + "\n");
    }

    public void printNoteDeleteErrorMessage() {
        System.out.println("Invalid index! Please input a number corresponding to one of the notes shown above.");
    }


    /**
     * Prints the already searched clues relating to a specifoc suspect.
     *
     * @param name  Name of one of the suspects.
     * @param clues List of already checked clues under that suspect.
     */
    public void printSearchedClues(String name, ArrayList<Clue> clues) {
        printNameHeaderForSearchedCluesList(name);
        for (Clue clue : clues) {
            System.out.println(clue);
        }
    }

    private void printNameHeaderForSearchedCluesList(String name) {
        System.out.println("<" + name + ">");
    }

    public void printEnterKillerName() {
        System.out.println(ENTER_SUSPECT_NAME);
    }

    public void printNoSearchedClues() {
        System.out.println(NO_SEARCHED_CLUES_FOR_ALL);
    }

    public void printNoSearchedClues(boolean hasNoSpecifiedSuspects, String name) {
        if (hasNoSpecifiedSuspects) {
            return;
        }
        System.out.println("<" + name + ">");
        System.out.println(NO_SEARCHED_CLUES_FOR_ONE + name + ".");
    }
}
