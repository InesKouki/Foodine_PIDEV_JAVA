/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
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
    private void AjouterReclamation(ActionEvent event) {
    }

    @FXML
    private void ModifierPass(ActionEvent event) {
        if(tfPassword.getText().isEmpty() || tfConfirmPass.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR,"Champs vides !",ButtonType.OK);
               a.showAndWait(); 
        }else {
            ServiceUtilisateur su = new ServiceUtilisateur();
        }
    }
    
}
