/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import tn.edu.esprit.entities.Client;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierPasswordController implements Initializable {

    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfConfirmPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            tfConfirmPass.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }

    @FXML
    private void AfficherAccueil(ActionEvent event) {
    }

    @FXML
   private void AfficherProfile(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("ProfileClient.fxml"));
            Parent root = loader.load();
            tfConfirmPass.getScene().setRoot(root);
            ModifierPasswordController ac = loader.getController();
    }
    @FXML
    private void AjouterReclamation(ActionEvent event) throws IOException {
          FXMLLoader  loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
            Parent root = loader.load();
            tfConfirmPass.getScene().setRoot(root);
            AjouterReclamationController ac = loader.getController();
    }

    @FXML
    private void ModifierPass(ActionEvent event) throws IOException {
        if(tfPassword.getText().isEmpty() || tfConfirmPass.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR,"Champs vides !",ButtonType.OK);
            
               a.showAndWait(); 
        }else if (!tfPassword.getText().equals(tfConfirmPass.getText())){
        
         Alert a = new Alert(Alert.AlertType.ERROR,"Verifier votre mot de passe !",ButtonType.OK);
               a.showAndWait(); 
    }else if (tfPassword.getText().length()<8){
          Alert a = new Alert(Alert.AlertType.ERROR,"Mot de passe doit contenir au moins 8 caracters !",ButtonType.OK);
               a.showAndWait(); 
    }
        else {
        
        try {
            int i = 0;
StringBuilder finalresult;
            MessageDigest msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(tfPassword.getText().getBytes(StandardCharsets.UTF_8));
            finalresult = new StringBuilder();
            for (byte b : hash) {
                i++;
                finalresult.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
           }
            
             ServiceUtilisateur su = new ServiceUtilisateur();
             User u = new Client(57,finalresult.toString());
            su.modifier_Password((Client) u);
             Alert a = new Alert(Alert.AlertType.INFORMATION,"Succes !",ButtonType.OK);
               a.showAndWait();
               
                FXMLLoader  loader = new FXMLLoader(getClass().getResource("ProfileClient.fxml"));
            Parent root = loader.load();
            tfConfirmPass.getScene().setRoot(root);
            ProfileClientController ac = loader.getController();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
           
        }
    }
    
}
