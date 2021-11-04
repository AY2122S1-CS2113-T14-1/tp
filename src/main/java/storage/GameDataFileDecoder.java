package storage;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;

public class GameDataFileDecoder extends GameDataFileManager {
    private static final int MAX_SCENE_NUMBER = 3;
    String factorySetting = "The Great Detective Data File\nCurrentSceneIndex: ";

    public GameDataFileDecoder(String fileName) throws DukeFileNotFoundException, DukeCorruptedFileException {
        super(fileName);
    }
    public void setCurrentSceneIndex(int index) throws DukeFileNotFoundException {
        this.writeFile(factorySetting + index);
    }

    public int getCurrentSceneIndex() throws DukeFileNotFoundException {
        int currentSceneIndex = 0;
        try {
            if (isValidFile()) {
                String lines = this.readFile();
                currentSceneIndex = Integer.parseInt(lines.substring(factorySetting.length()));
                if (currentSceneIndex <= MAX_SCENE_NUMBER) {
                    return currentSceneIndex;
                }
            }
        } catch (DukeCorruptedFileException e) {
            setCurrentSceneIndex(0);
        }
        setCurrentSceneIndex(0);
        return 0;
    }

    public boolean isValidFile() throws DukeCorruptedFileException, DukeFileNotFoundException {
        String lines = this.readFile();
        return lines.contains("The Great Detective Data File");
    }

}
