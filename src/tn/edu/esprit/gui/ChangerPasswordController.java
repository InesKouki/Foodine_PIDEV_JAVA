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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tn.edu.esprit.entities.Client;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ChangerPasswordController implements Initializable {

    @FXML
    private PasswordField tfPassword;
    @FXML
    private PasswordField tfConfirmPassword;
    @FXML
    private TextField tfCode;
ServiceUtilisateur su = new ServiceUtilisateur();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ChangerPass(ActionEvent event) throws IOException {
        if(tfPassword.getText().isEmpty() || tfConfirmPassword.getText().isEmpty()|| tfCode.getText().isEmpty()){
             Alert a = new Alert(Alert.AlertType.ERROR,"Champs vides !",ButtonType.OK);
               a.showAndWait();
   
        }
        else if(!tfConfirmPassword.getText().equals(tfPassword.getText())){
             Alert a = new Alert(Alert.AlertType.ERROR,"Verifier votre mot de passe!",ButtonType.OK);
               a.showAndWait();
        }else if(su.findCode(tfCode.getText())==null){
            Alert a = new Alert(Alert.AlertType.ERROR,"Code incorrecte!",ButtonType.OK);
               a.showAndWait();
        }else {
            User u = new Client(tfPassword.getText(),tfCode.getText());
            su.changerPass((Client) u);
             Alert a = new Alert(Alert.AlertType.INFORMATION,"Mot de passe changé avec succes!",ButtonType.OK);
               a.showAndWait();
              FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            tfCode.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
        }
    }

    @FXML
    private void Accueil(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            tfCode.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }
    
}
