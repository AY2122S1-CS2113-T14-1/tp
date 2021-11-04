package storage;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;

public class GameDataFileManager extends FileManager{

    static DataFileDecoder decoder;
    static DataFileEncoder encoder;
    public GameDataFileManager(String fileName) throws DukeFileNotFoundException, DukeCorruptedFileException {
        super(fileName);
        if (decoder == null) {
            decoder = new DataFileDecoder(fileName);
            encoder = new DataFileEncoder(fileName);
        }
    }

    public void writeFile(String lines) throws DukeFileNotFoundException {
        encoder.encodeFile(lines);
    }

    public String readFile() throws DukeCorruptedFileException, DukeFileNotFoundException {
        return decoder.decodeFile();
    }
}

