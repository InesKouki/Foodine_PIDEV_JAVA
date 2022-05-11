/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.edu.foodine.entities.Client;
import tn.edu.foodine.entities.Reclamation;
import tn.edu.foodine.entities.User;
import tn.edu.foodine.services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField tfDescription;
    @FXML
    private ChoiceBox<String> type;
  FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
    AuthentificationController ac = loader.getController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> list = FXCollections.observableArrayList("Livraison en retard","Commande Erroné","Autre");
        type.setItems(list);

    }    

  

    @FXML
    private void Ajout(ActionEvent event) {
             if(type.getValue().isEmpty() || tfDescription.getText().isEmpty()){
            
              Notifications notificationBuilder = Notifications.create()
                    .title("Error")
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
            else{
            ServiceReclamation su = new ServiceReclamation();
            long millis=System.currentTimeMillis();
            java.sql.Date date= new java.sql.Date(millis);
          
            
            User u = new Client(parseInt(System.getProperty("id")),"","", "","","", "", date,0);
            System.out.println(type.getSelectionModel().toString());
            Reclamation r = new Reclamation(u,date,0,type.getValue(),tfDescription.getText());
            su.ajouter(r);
             Notifications notificationBuilder = Notifications.create()
                    .title("Ajouter reclamation")
                    .text("Reclamation ajoutée")
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
    }

    }

}
