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
public class AuthentificationController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Authentification(ActionEvent event) {
        
    if(tfUsername.getText().isEmpty() || tfPassword.getText().isEmpty()){
             Alert a = new Alert(Alert.AlertType.ERROR,"Champs vides !",ButtonType.OK);
               a.showAndWait();
   
        }
            else{
           
            Alert a = new Alert(Alert.AlertType.INFORMATION,"Succes !",ButtonType.OK);
            a.showAndWait();
            
           
            
    }
    }

    @FXML
    private void OubliPass(ActionEvent event) {
    }

    @FXML
    private void AfficherInscription(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
            Parent root = loader.load();
            tfUsername.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }
    
}
