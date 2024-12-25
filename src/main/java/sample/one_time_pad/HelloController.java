package sample.one_time_pad;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class HelloController {
    @FXML
    private ToggleButton toggleFunc;
    @FXML
    private Button encrypt, decrypt;
    @FXML
    private TextField filePath, folderPath, keyPath, cpPath;
    @FXML
    private ImageView pt_browse, key_browse, cp_browse;
    @FXML
    private Text cp_label, pt_label, key_label, statText, functext;
    private static Stage theStage;
    private String fileExtension;
    private  static int state = 1;

    public static void setStage(Stage stage) {
        theStage = stage;
    }

    @FXML
    public void initialize() {
        state = 1;
        setEncryption();
    }

    @FXML
    protected void onToggle() {
        if(state == 1) //default state: encription
        {
            setEncryption();
        }else //default state: decription
        {
            setDecryption();
        }
    }

    @FXML
    protected void onEncryptClick()
    {
        //initialize variables
        byte[] in_file;
        byte[] key;
        String ct_path = new String();
        String key_path = new String();

        //initialize classes
        FileAssist fa = new FileAssist();
        Encryption e = new Encryption();

        //get user defined paths
        String inpath = filePath.getText();
        String outpath = folderPath.getText();

        //read plain text
        in_file = fa.readFile(inpath);

        // Create Output path:
        if (!outpath.isBlank()){
            ct_path = outpath + "\\Cipher.cph";
            key_path = outpath + "\\myKey.key";
        }else{
            statText.setText("Select Output Folder");
            return;
        }

        if (in_file != null) {
            //encrypt, get key, save cipher text
            key = e.encryptme(in_file, ct_path, fa,fileExtension);
            if (key != null) {
                //save key in a file
                boolean keyCreated = fa.writeCipherFile(key,key_path);
                if(keyCreated){
                    statText.setText("Encryption Successful!");
                }else{
                    statText.setText("Failed encryption. Try Again");
                }
            }else{
                statText.setText("Failed encryption. Try Again");
            }
        }else {
            statText.setText("Could not find file to encrypt");
        }

    }

    @FXML
    protected void onDecryptClick()
    {
        //initialize variables
        byte[] cipher;
        byte[] key;
        byte[] decryptText;
        String decryptExt = new String();
        String ct_path;
        String key_path;
        String decrypt_path;

        //initialize classes
        FileAssist fa = new FileAssist();
        Encryption d = new Encryption();

        // Read Paths
        ct_path = cpPath.getText();
        key_path = keyPath.getText();
        decrypt_path = folderPath.getText();

        if (decrypt_path.isBlank()){
            statText.setText("Specify Output Folder!");
            return;
        }

        //Decrypt cipher text:
        cipher = fa.readFile(ct_path);
        key = fa.readFile(key_path);

        if((cipher!= null) && (key!= null)){
            decryptText = d.decryptme(cipher,key);
            decryptExt = d.getExt();
            if(decryptText!=null){
                boolean isDecrypt = fa.writeDecryptFile(decryptText,decrypt_path,decryptExt);
                if(isDecrypt){
                    statText.setText("Decryption Successful");
                }else{
                    statText.setText("Decryption Unsuccessful");
                }
            }
        }else {
            statText.setText("Invalid Key or Cipher file");
        }

    }

    @FXML
    protected void onFolderBrowse()
    {
        String message = new String();
        DirectoryChooser browse = new DirectoryChooser();
        File file = browse.showDialog(theStage);
        if (file != null) {
            message = file.getAbsolutePath();
        }
        folderPath.setText(message);

    }

    @FXML
    protected void onFileBrowse()
    {
        String message = new String();
        FileChooser browse = new FileChooser();
        File file = browse.showOpenDialog(theStage);
        if (file != null) {
            message = file.getAbsolutePath();
            String fileName = file.getName();
            int indexOfDot = fileName.lastIndexOf(".");
            fileExtension = fileName.substring(indexOfDot);
        }
        filePath.setText(message);

    }

    @FXML
    protected void onCphBrowse() {
        String message = new String();
        FileChooser browse = new FileChooser();
        browse.getExtensionFilters().add(new FileChooser.ExtensionFilter("Cipher Files","*.cph"));
        File file = browse.showOpenDialog(theStage);
        if (file != null) {
            message = file.getAbsolutePath();
        }
        cpPath.setText(message);
    }

    @FXML
    protected void onKeyBrowse() {
        String message = new String();
        FileChooser browse = new FileChooser();
        browse.getExtensionFilters().add(new FileChooser.ExtensionFilter("Key Files","*.key"));
        File file = browse.showOpenDialog(theStage);
        if (file != null) {
            message = file.getAbsolutePath();
        }
        keyPath.setText(message);
    }





    private void setEncryption()
    {
        functext.setText("Encrypting...");
        state = 2;
        //decrypt related:
        decrypt.setDisable(true);
        decrypt.setVisible(false);
        key_browse.setDisable(true);
        key_browse.setVisible(false);
        key_label.setDisable(true);
        key_label.setVisible(false);
        keyPath.setDisable(true);
        keyPath.setVisible(false);
        cp_browse.setDisable(true);
        cp_browse.setVisible(false);
        cp_label.setDisable(true);
        cp_label.setVisible(false);
        cpPath.setDisable(true);
        cpPath.setVisible(false);

        //encrypt related:
        encrypt.setDisable(false);
        encrypt.setVisible(true);
        filePath.setDisable(false);
        filePath.setVisible(true);
        pt_browse.setDisable(false);
        pt_browse.setVisible(true);
        pt_label.setDisable(false);
        pt_label.setVisible(true);
    }

    private void setDecryption()
    {
        functext.setText("Decrypting...");
        state = 1;
        //decrypt related:
        decrypt.setDisable(false);
        decrypt.setVisible(true);
        key_browse.setDisable(false);
        key_browse.setVisible(true);
        key_label.setDisable(false);
        key_label.setVisible(true);
        keyPath.setDisable(false);
        keyPath.setVisible(true);
        cp_browse.setDisable(false);
        cp_browse.setVisible(true);
        cp_label.setDisable(false);
        cp_label.setVisible(true);
        cpPath.setDisable(false);
        cpPath.setVisible(true);

        //encrypt related:
        encrypt.setDisable(true);
        encrypt.setVisible(false);
        filePath.setDisable(true);
        filePath.setVisible(false);
        pt_browse.setDisable(true);
        pt_browse.setVisible(false);
        pt_label.setDisable(true);
        pt_label.setVisible(false);
    }

}
