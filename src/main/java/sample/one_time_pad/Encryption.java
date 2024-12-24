package sample.one_time_pad;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Encryption {
    private String ext = new String();
    public byte[] decryptme(byte[] cyph_all, byte[] key_all ) {
        int len = key_all.length;
        byte[] pt_ext = new byte[4];
        byte[] pt_decrypt = new byte[len-4];

        //decrypt
        for (int j = 0; j<len;j++)
        {
            if(j>=0 && j<4) {
                pt_ext[j] = (byte) (cyph_all[j] ^ key_all[j]);
            }else {
                pt_decrypt[j-4] = (byte) (cyph_all[j] ^ key_all[j]);
            }
        }

        String extension = new String(pt_ext, StandardCharsets.UTF_8);
        ext = extension;

        //return decrypted text
        return pt_decrypt;
    }
    public String getExt(){
        return ext;
    }


    public byte[] encryptme(byte[] pt, String ct_path, FileAssist fa, String extension) {

        int s_len;
        int pt_len;
        int ext_len;
        byte[] ext = extension.getBytes(StandardCharsets.UTF_8);
        pt_len = pt.length;
        ext_len = ext.length;
        s_len = pt_len + ext_len;
        byte[] pt_all;
        byte[] key = new byte[s_len];
        byte[] ct = new byte[s_len];

        //concatenate file content and extension
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(ext);
            outputStream.write(pt);
            pt_all = outputStream.toByteArray();
        } catch (IOException e) {
            return null;
        }


        // generate random key
        Random r = new Random();
        r.nextBytes(key);
        // encrypt
        for (int j = 0; j<s_len;j++)
        {
            ct[j] = (byte) (pt_all[j] ^ key[j]);
        }

        //write cipher text and return key
        boolean isEncrypted = fa.writeCipherFile(ct,ct_path);
        if (isEncrypted){
            return key;
        }
        return null;
    }
}
