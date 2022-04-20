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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
         AuthentificationController ac = loader.getController();
         
//        System.out.println(ac.connectedUser);
    }    

    @FXML
    private void AfficherAccueil(ActionEvent event) {
    }

    @FXML
    private void AfficherProfile(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("ProfileClient.fxml"));
            Parent root = loader.load();
            txtNom.getScene().setRoot(root);
           ProfileClientController ac = loader.getController();
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
            Parent root = loader.load();
            txtNom.getScene().setRoot(root);
            AjouterReclamationController ac = loader.getController();
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            txtNom.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
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
            User u = new Client(57,txtNom.getText(),txtPrenom.getText(),txtEmail.getText(),Integer. parseInt(txtPhone.getText()),txtAdresse.getText());
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
    
}
