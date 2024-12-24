package sample.one_time_pad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileAssist {
    public byte[] readFile(String file_path) {

        try {
            byte[] the_text = Files.readAllBytes(Path.of(file_path));
            return the_text;
        } catch (IOException e) {

        }
        return null;
    }

    public boolean writeCipherFile(byte[] key, String key_path)
    {
        File keyFile = new File(key_path);
        try {
            keyFile.createNewFile();
            Files.write(Path.of(key_path),key);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean writeDecryptFile(byte[] key, String key_path, String file_extension)
    {
        String mykey_path = new String();
        mykey_path = key_path + "\\decrypted" + file_extension;
        File keyFile = new File(mykey_path);
        try {
            keyFile.createNewFile();
            Files.write(Path.of(mykey_path),key);
        } catch (IOException e) {
            return false;
        }
        return true;
    }



}
