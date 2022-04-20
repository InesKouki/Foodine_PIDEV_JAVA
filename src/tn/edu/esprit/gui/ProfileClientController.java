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
import static java.lang.Integer.parseInt;
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
public class ProfileClientController implements Initializable {

    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtNom;
    String uploads = "C:\\Users\\Asus\\Desktop\\Foodine_PIDEV_JAVA\\src\\images\\";
     private String path = "", imgname = "", fn="";
    ServiceUtilisateur u = new ServiceUtilisateur();
     Client us = (Client) u.find(Integer.parseInt(System.getProperty("id")));
    @FXML
    private ImageView image;
    @FXML
    private Button btnphoto;
    private ImageView image1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
         AuthentificationController ac = loader.getController();
         txtNom.setText(us.getNom());
     
           txtAdresse.setText(us.getAddress());
      
    
           txtPhone.setText(Integer.toString(us.getPhone()));
       
       
         txtPrenom.setText(us.getPrenom());
         txtEmail.setText(us.getEmail());
         image.setImage(new Image("file:" + uploads +us.getFile()));
         image1.setImage(new Image("file:" + uploads +us.getFile()));
     

    }    


    @FXML
    private void AfficherProfile(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("ProfileClient.fxml"));
            Parent root = loader.load();
            txtNom.getScene().setRoot(root);
           ProfileClientController ac = loader.getController();
    }

    


    @FXML
    private void ModifierInformation(ActionEvent event) {
        
        if(!txtEmail.getText().contains("@") || !txtEmail.getText().contains(".")){
             Alert a = new Alert(Alert.AlertType.ERROR,"Adresse Email non valide !",ButtonType.OK);
               a.showAndWait();
        }else if(!txtPhone.getText().matches("[0-9]+") ||txtPhone.getText().length() > 8 ){
            Alert a = new Alert(Alert.AlertType.ERROR,"Numero téléphone invalide !",ButtonType.OK);
               a.showAndWait();
        }else if (txtNom.getText().matches("[0-9]+") ||txtPrenom.getText().matches("[0-9]+")){
             Alert a = new Alert(Alert.AlertType.ERROR,"Votre nom ou prenom doit contenir des lettre alphabetique !",ButtonType.OK);
               a.showAndWait();
        }
        
        else  {
            ServiceUtilisateur su = new ServiceUtilisateur();
            User u = new Client(parseInt(System.getProperty("id")),txtNom.getText(),txtPrenom.getText(),txtEmail.getText(),Integer. parseInt(txtPhone.getText()),txtAdresse.getText(),imgname);
            su.modifierInfo((Client) u);
             Alert a = new Alert(Alert.AlertType.INFORMATION,"Succes !",ButtonType.OK);
               a.showAndWait();
            
        }
    }

    @FXML
    private void ModifierPassword(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("ModifierPassword.fxml"));
            Parent root = loader.load();
            txtNom.getScene().setRoot(root);
           ModifierPasswordController ac = loader.getController();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            txtNom.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }

    @FXML
    private void AfficherReclamation(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
            Parent root = loader.load();
            txtNom.getScene().setRoot(root);
            AjouterReclamationController ac = loader.getController();
    }

    @FXML
    private void AfficherAccueil(ActionEvent event) {
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

        image.setImage(new Image("file:" + dest.getAbsolutePath()));
       
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
