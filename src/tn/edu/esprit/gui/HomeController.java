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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherReclamation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AfficherReclamation.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void AfficherUtilisateur(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AfficherUtilisateur.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void AfficherProduit(ActionEvent event) {
    }

    @FXML
    private void AfficherCatégories(ActionEvent event) {
    }

    @FXML
    private void AfficherEvenement(ActionEvent event) {
    }

    @FXML
    private void AfficherPromotion(ActionEvent event) {
    }

    @FXML
    private void AfficherRecette(ActionEvent event) {
    }

    @FXML
    private void AfficherPlanning(ActionEvent event) {
    }

    @FXML
    private void AfficherReservation(ActionEvent event) {
    }

    @FXML
    private void AfficherTable(ActionEvent event) {
    }


    @FXML
    private void AfficherCommande(ActionEvent event) {
    }

    @FXML
    private void AfficherLivraison(ActionEvent event) {
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
       FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            rootPane.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }
    
}
