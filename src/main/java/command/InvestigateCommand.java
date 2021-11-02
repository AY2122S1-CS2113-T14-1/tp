package command;

import exceptions.InvalidInputException;
import investigation.Investigation;
import scene.SceneList;
import scene.SceneTypes;
import ui.Ui;

public class InvestigateCommand extends Command {
    private static final String SUSPECT_FATHER_LOWER = "father";
    private static final String SUSPECT_KEVIN_LOWER = "kevin";
    private static final String SUSPECT_WENDY_LOWER = "wendy";
    private static final String SUSPECT_LING_LOWER = "ling";
    private static final String SUSPECT_ZACK_LOWER = "zack";
    private static final int SUSPECT_FATHER_INDEX = 1;
    private static final int SUSPECT_KEVIN_INDEX = 2;
    private static final int SUSPECT_WENDY_INDEX = 3;
    private static final int SUSPECT_LING_INDEX = 4;
    private static final int SUSPECT_ZACK_INDEX = 5;
    private static final String INVALID_SUSPECT_NAME = "Invalid suspect given!";
    private int suspectIndex;
    private String suspectName = null;
    private boolean backToSuspectStage = false;
    private static final int WENDY_INDEX = 3;

    /**
     * InvestigateCommand using the suspect's index.
     * @param suspectIndex The index of the suspect.
     *     We instantiate a new InvestigateCommand object.
     */
    public InvestigateCommand(int suspectIndex) {
        this.suspectIndex = suspectIndex;
    }

    /**
     * InvestigateCommand using the suspect's index.
     * @param suspectName The name of the suspect.
     *     We instantiate a new InvestigateCommand object.
     */
    public InvestigateCommand(String suspectName) {
        this.suspectName = suspectName;
    }

    /**
     * If the user enters a valid user name, we set the suspect's index with
     * the corresponding suspect index.
     * Else we throw a new InvalidInputException.
     * @throws InvalidInputException When the user enters the wrong killer name.
     */
    private void suspectNameToIndex() throws InvalidInputException {
        switch (suspectName) {
        case SUSPECT_FATHER_LOWER:
            this.suspectIndex = SUSPECT_FATHER_INDEX;
            break;
        case SUSPECT_KEVIN_LOWER:
            this.suspectIndex = SUSPECT_KEVIN_INDEX;
            break;
        case SUSPECT_WENDY_LOWER:
            this.suspectIndex = SUSPECT_WENDY_INDEX;
            break;
        case SUSPECT_LING_LOWER:
            this.suspectIndex = SUSPECT_LING_INDEX;
            break;
        case SUSPECT_ZACK_LOWER:
            this.suspectIndex = SUSPECT_ZACK_INDEX;
            break;
        default:
            throw new InvalidInputException(INVALID_SUSPECT_NAME);
        }
    }

    /**
     * We check if user gave suspect name when the user was investigating.
     * If user gives a suspect name, we match the suspect index to the corresponding name given.
     * @throws InvalidInputException When the user enters the wrong killer name.
     *      If the suspectName is null, we set backToSuspectStage to false.
     *      Else we try to get a suspect index based on the suspect name
     *      and set backToSuspectStage to true.
     */
    private void suspectNameGiven() throws InvalidInputException {
        if (this.suspectName != null) {
            suspectNameToIndex();
            this.backToSuspectStage = true;
        } else {
            this.backToSuspectStage = false;
        }
    }

    /**
     * We check if the suspect index is valid.
     * @throws InvalidInputException When the suspect index is not within 1 to 5 inclusive.
     */
    private void checkSuspectIndex() throws InvalidInputException {
        if (this.suspectIndex <= 0 || this.suspectIndex >= 6) {
            throw new InvalidInputException(INVALID_SUSPECT_NAME);
        }
    }

    @Override
    public void execute(Ui ui, Investigation investigation, SceneList sceneList) throws InvalidInputException {
        // If the user enters the suspect name,
        // we get the corresponding suspect index.
        suspectNameGiven();
        SceneTypes sceneType = sceneList.getCurrentSceneType();

        // If we are at the guess killer scene,
        // we check if the user has given a correct suspect name/index using checkSuspectIndex.
        // We then check if the suspect's index matches the index of the correct killer.
        // We then set the scene number and run the scene.
        // If the user tries to investigate at the introduction scene,
        // we will print an invalid command message.
        // Else we investigate the scene based on the suspect index.
        switch (sceneType) {
        case GUESS_KILLER_SCENE:
            checkSuspectIndex();
            boolean isCorrectKiller = (this.suspectIndex == WENDY_INDEX);
            sceneList.setSceneNumberAfterSuspecting(isCorrectKiller);
            sceneList.runCurrentScene();
            break;
        case INTRODUCTION_SCENE:
            ui.printInvalidCommandMessage();
            break;
        default:
            if (this.backToSuspectStage) {
                investigation.setSuspectStage();
            }
            investigation.investigateScene(this.suspectIndex, sceneList.getCurrentScene());
            break;
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
