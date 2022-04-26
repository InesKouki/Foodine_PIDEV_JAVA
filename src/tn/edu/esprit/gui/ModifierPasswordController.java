/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
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

    private void LogOut(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            tfConfirmPass.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }


   private void AfficherProfile(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("ProfileClient.fxml"));
            Parent root = loader.load();
            tfConfirmPass.getScene().setRoot(root);
            ModifierPasswordController ac = loader.getController();
    }
    private void AjouterReclamation(ActionEvent event) throws IOException {
          FXMLLoader  loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
            Parent root = loader.load();
            tfConfirmPass.getScene().setRoot(root);
            AjouterReclamationController ac = loader.getController();
    }

    @FXML
    private void ModifierPass(ActionEvent event) throws IOException {
        if(tfPassword.getText().isEmpty() || tfConfirmPass.getText().isEmpty()){
             Notifications notificationBuilder = Notifications.create()
                    .title("Modification mot de passe")
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
        }else if (!tfPassword.getText().equals(tfConfirmPass.getText())){
        
         Notifications notificationBuilder = Notifications.create()
                    .title("Modification mot de passe")
                    .text("Verifier votre mode passe")
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
    }else if (tfPassword.getText().length()<8){
          Notifications notificationBuilder = Notifications.create()
                    .title("Modification mot de passe")
                    .text("Mot de passe doit contenir au moins 8 caractères")
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
        else {
        
        try {
            int i = 0;
StringBuilder finalresult;
            MessageDigest msg = MessageDigest.getInstance("MD5");
            byte[] hash = msg.digest(tfPassword.getText().getBytes(StandardCharsets.UTF_8));
            finalresult = new StringBuilder();
            for (byte b : hash) {
                i++;
                finalresult.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
           }
            
             ServiceUtilisateur su = new ServiceUtilisateur();
             User u = new Client(parseInt(System.getProperty("id")),finalresult.toString());
            su.modifier_Password((Client) u);
             Notifications notificationBuilder = Notifications.create()
                    .title("Modification mot de passe")
                    .text("Mot de passe modifié avec succes")
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
               
                FXMLLoader  loader = new FXMLLoader(getClass().getResource("ProfileClient.fxml"));
            Parent root = loader.load();
            tfConfirmPass.getScene().setRoot(root);
            ProfileClientController ac = loader.getController();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
           
        }
    }

    private void logout(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("ModifierPassword.fxml"));
            Parent root = loader.load();
            tfConfirmPass.getScene().setRoot(root);
           ModifierPasswordController ac = loader.getController();
    }

    private void AfficherReclamation(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
            Parent root = loader.load();
            tfConfirmPass.getScene().setRoot(root);
           AjouterReclamationController ac = loader.getController();
    }

    @FXML
    private void Home(MouseEvent event) {
    }
    
}
