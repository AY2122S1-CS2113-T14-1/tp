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
        System.out.println("/help");
        System.out.println("/exit");
        System.out.println("/next");
        System.out.println("To investigate suspects or clues, please input their corresponding number.");
        System.out.println();
    }

    public void printListOfClues(ArrayList<Clue> clues) {
        int i = 0;
        for (Clue clue : clues) {
            System.out.println((i + 1) + ". " + clue.getClueName().trim());
            i++;
        }
    }

    public void printNotesMessage() {
        System.out.println(LIST_OF_NOTES_MESSAGE);
        System.out.println("1. This is a place holder");
    }

    public void getClue(int clueNumber) {
        String str = "this is a clue placeholder";
        System.out.println("Clue number " + clueNumber
                + " " + str);
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
        System.out.println("Invalid number! To select a clue, please input its corresponding number.");
    }

    public void printInvalidSuspectMessage() {
        System.out.println("Invalid number! To select a suspect, please input its corresponding number.");
    }

    public void printInvalidCommandMessage() {
        System.out.println("Invalid input! Type '/help' to see the available commands.");
    }

    public void printHelpCommandIndicator() {
        System.out.println();
        System.out.println("Enter /help to view all commands available.");
    }
}
