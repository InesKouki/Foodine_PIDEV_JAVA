/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.edu.foodine.entities.Client;
import tn.edu.foodine.entities.User;
import tn.edu.foodine.services.ServiceUtilisateur;

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
            Notifications notificationBuilder = Notifications.create()
                    .title("Changer Mot de passe")
                    .text("Champs vides")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showError();
   
        }
        else if(!tfConfirmPassword.getText().equals(tfPassword.getText())){
             Notifications notificationBuilder = Notifications.create()
                    .title("Changer Mot de passe")
                    .text("Verifier votre mot de passe")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showError();
        }else if(su.findCode(tfCode.getText())==null){
            Notifications notificationBuilder = Notifications.create()
                    .title("Changer Mot de passe")
                    .text("Code incorrecte")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showError();
        }else {
            User u = new Client(tfPassword.getText(),tfCode.getText());
            su.changerPass((Client) u);
             Notifications notificationBuilder = Notifications.create()
                    .title("Changer Mot de passe")
                    .text("Mot de passe modifi?? avec succes")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showConfirm();
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
