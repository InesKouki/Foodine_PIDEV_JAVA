/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DashboardController implements Initializable {

    @FXML
    private Label lbadmin;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
  

    @FXML
    private void AfficherAccueil(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            lbadmin.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }

    @FXML
    private void AfficherReclamation(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
            Parent root = loader.load();
            lbadmin.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }

    @FXML
    private void AfficherUtilisateur(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("AfficherUtilisateur.fxml"));
            Parent root = loader.load();
            lbadmin.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }

    private void Logout(ActionEvent event) throws IOException {
          FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            lbadmin.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
            
    }
    
}
