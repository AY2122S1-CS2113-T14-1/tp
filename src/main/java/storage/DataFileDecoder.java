package storage;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;

public class DataFileDecoder extends FileManager {

    public DataFileDecoder(String fileName) throws DukeFileNotFoundException, DukeCorruptedFileException {
        super(fileName);
    }

    public String decodeFile() throws DukeFileNotFoundException, DukeCorruptedFileException {
        try {
            FileInputStream rawFile = new FileInputStream(this.filePath);
            byte[] encText = new byte[rawFile.available()];
            rawFile.read(encText);
            rawFile.close();

            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(encText);

            String textDecryptedString = new String(textDecrypted);
            return textDecryptedString;

        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        } catch (BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
            throw new DukeCorruptedFileException();
        }
    }
}
