package ui;

import clue.Clue;
import suspect.Suspect;
import suspect.SuspectList;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Ui {
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GAME_NAME = "Classic Adventure Text Game";
    private static final String WELCOME_MESSAGE = "Welcome to the " + GAME_NAME + "!\n";
    private static final String INTRODUCE_MYSELF = "HELLO! I am \n" + LOGO;
    private static final String ASK_FOR_USERNAME = "Before we get started, how do I address you?";
    private static final String GOODBYE_MESSAGE = "Goodbye.";
    private static final String LIST_OF_COMMAND_AVAILABLE_MESSAGE =
            "Here are the list of commands available to you.";
    private static final String LIST_OF_CLUES_MESSAGE =
            "Here are the list of clues available to you.";
    private static final String LIST_OF_NOTES_MESSAGE =
            "Here are the list of notes available to you.";
    private static final String lineSeparator = "==============================";
    private static final String WHO_KILLED_YOU = "Who do you think killed you?";
    private static final String CORRECT_ANSWER = "Correct answer";
    private static final String WRONG_ANSWER = "Wrong answer";
    private static final String HELP_COMMAND_INDICATOR = "Enter /help to view all commands available.";
    private static final String INVALID_INPUT_GIVEN = "Invalid input! Type '/help' to see the available commands.";
    private static final String INVALID_NUMBER_SUSPECT =
            "Invalid number! To select a suspect, please input its corresponding number.";
    private static final String INVALID_NUMBER_CLUE =
            "Invalid number! To select a clue, please input its corresponding number.";
    private static final String HELP_COMMAND = "/help";
    private static final String EXIT_COMMAND = "/exit";
    private static final String NEXT_COMMAND = "/next";
    private static final String ASK_FOR_CLUE_OR_SUSPECT_NUMBER =
            "To investigate suspects or clues, please input their corresponding number.";

    private Scanner scanner;

    public void printEmptyLine() {
        System.out.println(lineSeparator);
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void askForUsername() {
        System.out.println(ASK_FOR_USERNAME);
    }

    public String readUserInput() {
        this.scanner = new Scanner(System.in);
        String userInput = this.scanner.nextLine();
        return userInput;
    }

    public void printExitMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void printWelcomeUser(String userName) {
        String welcomeMessage = "Welcome " + userName
                + " to the " + GAME_NAME + "!";
        System.out.println(welcomeMessage);
    }

    public void printListOfCommands() {
        System.out.println(LIST_OF_COMMAND_AVAILABLE_MESSAGE);
        System.out.println(HELP_COMMAND);
        System.out.println(EXIT_COMMAND);
        System.out.println(NEXT_COMMAND);
        System.out.println(ASK_FOR_CLUE_OR_SUSPECT_NUMBER);
        System.out.println();
    }

    public void printListOfClues(ArrayList<Clue> clues) {
        int i = 0;
        for (Clue clue : clues) {
            System.out.println((i + 1) + ". " + clue.getClueName().trim());
            i++;
        }
    }

    public void printSuspects(SuspectList suspects) {
        int i = 0;
        for (Map.Entry<String, Suspect> suspectEntry : suspects.getSuspects().entrySet()) {
            System.out.println((i + 1) + ". " + suspectEntry.getKey());
            i++;
        }
    }

    public void printSuspectKillerMessage() {
        System.out.println(WHO_KILLED_YOU);
    }

    public void printCorrectMessage() {
        System.out.println(CORRECT_ANSWER);
    }

    public void printWrongMessage() {
        System.out.println(WRONG_ANSWER);
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

    public void printHelpCommandIndicator() {
        System.out.println();
        System.out.println(HELP_COMMAND_INDICATOR);
    }
}
