package storage;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;

public class DataFileEncoder extends FileManager {

    public DataFileEncoder(String fileName) throws DukeFileNotFoundException, DukeCorruptedFileException {
        super(fileName);
    }

    public void encodeFile(String line) throws DukeFileNotFoundException {
        try {

            byte[] text = line.getBytes(StandardCharsets.UTF_8);

            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);

            FileOutputStream rawFile = new FileOutputStream(this.filePath);
            rawFile.write(textEncrypted);
            rawFile.close();

        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        } catch (BadPaddingException| InvalidKeyException | IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
        }
    }

}
