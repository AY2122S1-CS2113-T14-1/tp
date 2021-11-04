package exception;

public class DukeCorruptedFileException extends Exception {

    public DukeCorruptedFileException() {
        super("Game Data file is corrupted");
    }
}