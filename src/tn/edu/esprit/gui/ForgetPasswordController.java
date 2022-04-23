/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import static java.lang.Integer.max;
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
import tn.edu.esprit.utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private TextField tfEmail;
ServiceUtilisateur su = new ServiceUtilisateur();
int min = 1000;
int max = 9999;
int random = (int)Math.floor(Math.random()*(max-min+1)+min);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Authentification(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            tfEmail.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }

    @FXML
    private void changerPass(ActionEvent event) throws Exception {
        
        if(tfEmail.getText().isEmpty()){
             Alert a = new Alert(Alert.AlertType.ERROR,"Champs vides !",ButtonType.OK);
               a.showAndWait();
             
        }else if (su.findEmail(tfEmail.getText(),Integer.toString(random))== null){
             Alert a = new Alert(Alert.AlertType.ERROR,"Utilisateur non trouvé !",ButtonType.OK);
               a.showAndWait();
        }
            else{
            JavaMail.sendMail(su.findEmail(tfEmail.getText(),Integer.toString(random)).getEmail(), "Changer Mot de passe "+random);
            su.findEmail(tfEmail.getText(),Integer.toString(random)).setReset_token(Integer.toString(random));
             Alert a = new Alert(Alert.AlertType.INFORMATION,"Un mail vous a été envoyé !",ButtonType.OK);
            a.showAndWait();
             FXMLLoader  loader = new FXMLLoader(getClass().getResource("ChangerPassword.fxml"));
            Parent root = loader.load();
            tfEmail.getScene().setRoot(root);
            ChangerPasswordController ac = loader.getController();   
            
        }
    }
    
}
