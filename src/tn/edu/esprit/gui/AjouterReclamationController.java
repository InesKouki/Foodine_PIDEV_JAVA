/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import tn.edu.esprit.entities.Client;
import tn.edu.esprit.entities.Reclamation;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private Button logout;
    @FXML
    private Button Home;
    @FXML
    private Button afficherProfile;
    @FXML
    private Button AjouterReclamation;
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
    private void LogOut(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            tfDescription.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }

    @FXML
    private void AfficherAccueil(ActionEvent event) {
    }

    @FXML
    private void AfficherProfile(ActionEvent event) throws IOException {
          FXMLLoader  loader = new FXMLLoader(getClass().getResource("ProfileClient.fxml"));
            Parent root = loader.load();
            tfDescription.getScene().setRoot(root);
            ProfileClientController ac = loader.getController();

    }

    @FXML
    private void AjouterReclamation(ActionEvent event) {
    }

    @FXML
    private void Ajout(ActionEvent event) {
             if(type.getValue().isEmpty() || tfDescription.getText().isEmpty()){
             Alert a = new Alert(Alert.AlertType.ERROR,"Champs vides !",ButtonType.OK);
               a.showAndWait();
   
        }
            else{
            ServiceReclamation su = new ServiceReclamation();
            long millis=System.currentTimeMillis();
            java.sql.Date date= new java.sql.Date(millis);
          
            
            User u = new Client(parseInt(System.getProperty("id")),"","", "","","", "", date,0);
            System.out.println(type.getSelectionModel().toString());
            Reclamation r = new Reclamation(u,date,0,type.getValue(),tfDescription.getText());
            su.ajouter(r);
             Alert a = new Alert(Alert.AlertType.INFORMATION,"Succes !",ButtonType.OK);
               a.showAndWait();
    }

    }
    
}
