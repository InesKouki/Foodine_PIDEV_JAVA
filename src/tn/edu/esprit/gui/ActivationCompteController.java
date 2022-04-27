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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.edu.esprit.entities.Client;
import tn.edu.esprit.services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ActivationCompteController implements Initializable {
ServiceUtilisateur su = new ServiceUtilisateur();
    @FXML
    private TextField tfCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Activer(ActionEvent event) throws IOException {
       // System.out.println(tfCode.getText());
//        System.out.println(su.findActivationCode(tfCode.getText()).toString());
          if(tfCode.getText().isEmpty()){
            Notifications notificationBuilder = Notifications.create()
                    .title("Activation de compte")
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
    }else if(su.findActivationCode(tfCode.getText())==null){

         Notifications notificationBuilder = Notifications.create()
                    .title("Activation de compte")
                    .text("code incorrecte")
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
        
        
        
       su.Activer((Client) su.findActivationCode(tfCode.getText()));
        Notifications notificationBuilder = Notifications.create()
                    .title("Activation de compte")
                    .text("Votre compte a été activer")
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
    
}
