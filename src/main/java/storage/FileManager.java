package storage;

import exception.DukeCorruptedFileException;
import exception.DukeFileNotFoundException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;

public class FileManager{
    String fileName;
    String filePath;
    String keyPath = "data/key.txt";
    String direName = "data/";

    static KeyGenerator keygenerator;
    static SecretKey myDesKey;
    static Cipher desCipher;

    public FileManager(String fileName) throws DukeFileNotFoundException, DukeCorruptedFileException {
        this.fileName = fileName;
        this.filePath = direName + this.fileName;
        if (keygenerator == null) {
            initialise();
        }
        checkPath();
    }

    public void initialise() throws DukeCorruptedFileException, DukeFileNotFoundException {
        try {
            FileInputStream keyFis = new FileInputStream(keyPath);
            byte[] encKey = new byte[keyFis.available()];
            keyFis.read(encKey);
            keyFis.close();
            keygenerator = KeyGenerator.getInstance("DES");
            myDesKey = new SecretKeySpec(encKey, "DES");
            desCipher = Cipher.getInstance("DES");

        } catch (FileNotFoundException e) {
            generateNewKey();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new DukeCorruptedFileException();
        }
    }

    public void generateNewKey() throws DukeCorruptedFileException,DukeFileNotFoundException {
        try {
            keygenerator = KeyGenerator.getInstance("DES");
            myDesKey = keygenerator.generateKey();
            desCipher = Cipher.getInstance("DES");

            checkPath();
            byte[] keyAsByte = myDesKey.getEncoded();
            FileOutputStream keyfos = new FileOutputStream(keyPath);
            keyfos.write(keyAsByte);
            keyfos.close();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new DukeCorruptedFileException();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }

    public void checkPath() throws DukeFileNotFoundException {
        try {
            new File(direName).mkdir();
            new File(keyPath).createNewFile();
            new File(filePath).createNewFile();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }
}









