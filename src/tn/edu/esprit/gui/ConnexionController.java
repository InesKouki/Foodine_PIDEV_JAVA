/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;
import tn.edu.esprit.entities.Client;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ConnexionController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPrenom;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private PasswordField tfConfirmPassword;
    @FXML
    private Button btnupload;
    @FXML
    private ImageView uploadIv;
String uploads = "C:\\Users\\Asus\\Desktop\\Foodine_PIDEV_JAVA\\src\\images\\";
    private String path = "", imgname = "", fn="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterUtilisateur(ActionEvent event) throws IOException {
         if(tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty() ||tfUsername.getText().isEmpty() || tfEmail.getText().isEmpty() || tfPassword.getText().isEmpty() || tfConfirmPassword.getText().isEmpty()){
             Alert a = new Alert(Alert.AlertType.ERROR,"Champs vides !",ButtonType.OK);
               a.showAndWait();
        }else if (!tfEmail.getText().contains("@") || !tfEmail.getText().contains(".")){
             Alert a = new Alert(Alert.AlertType.ERROR,"Email invalide !",ButtonType.OK);
               a.showAndWait();
        }else if (tfPassword.getText().length() <8 ){
             Alert a = new Alert(Alert.AlertType.ERROR,"Mot de passe doit contenir au moins 8 caractères !",ButtonType.OK);
               a.showAndWait();
        }
        else if (!tfPassword.getText().equals(tfConfirmPassword.getText())){
             Alert a = new Alert(Alert.AlertType.ERROR,"Vérifier votre mot de passe !",ButtonType.OK);
               a.showAndWait();
        }else if(tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty()){
            
        }else {
            long millis=System.currentTimeMillis();
            java.sql.Date date= new java.sql.Date(millis);
            ServiceUtilisateur su = new ServiceUtilisateur();
            User u = new Client(tfNom.getText(),tfPrenom.getText(),tfUsername.getText(),tfEmail.getText(),tfPassword.getText(),imgname,date,1);
            su.ajouter(u);
            Alert a = new Alert(Alert.AlertType.INFORMATION,"Inscription effectuée avec success !",ButtonType.OK);
            a.showAndWait();
            
            FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
            ConnexionController ac = loader.getController();
    }
    }

    @FXML
    private void AfficherConnexion(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
            ConnexionController ac = loader.getController();
    }

    @FXML
    private void upload(ActionEvent event) throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        path= f.getAbsolutePath();
        imgname = f.getName();
        fn = imgname;
        Image getAbsolutePath = null;

        String dd = uploads + f.getName();
        File dest = new File(dd);
        this.copyFile(f, dest);

        System.out.println(dd);

        uploadIv.setImage(new Image("file:" + dest.getAbsolutePath()));
    }

    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        try (
                FileChannel in = new FileInputStream(sourceFile).getChannel();
                FileChannel out = new FileOutputStream(destFile).getChannel();) {

            out.transferFrom(in, 0, in.size());
        }
    }
}
