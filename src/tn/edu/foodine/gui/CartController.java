/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import tn.edu.foodine.entities.Commande;
import tn.edu.foodine.entities.CommandeDetails;

import tn.edu.foodine.entities.Produit;
import tn.edu.foodine.services.Cart;

import  tn.edu.foodine.services.ServiceCommande;
import  tn.edu.foodine.services.ServiceCommandeDetails;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EXTREME-GAMING
 */
public class CartController implements Initializable {

    @FXML
    private Label totalprice;

    @FXML
    private ListView<String> carte;
    private List<Produit> produits;
    @FXML
    Button plusButton;
    @FXML
    Button moinsButton;
    ServiceCommande commandeService = new ServiceCommande();
    ServiceCommandeDetails commandeDetailService = new ServiceCommandeDetails();
    @FXML
    private TextField qte;
    int newQuantity;
    double PrixTot = 0f;
    @FXML
    private ListView<?> Chart;

        @Override
    public void initialize(URL url, ResourceBundle rb) {
        produits = new ArrayList<>();
        produits = Cart.getPanier();
        Cart.getPanier().forEach(e -> {
            this.carte.getItems().add(e.getName() + e.getPrice() + e.getQuantite());
        });

        carte.getSelectionModel().selectedItemProperty().addListener(lis -> {
            int a = carte.getSelectionModel().getSelectedIndex();
            if (produits.get(a).getQuantite() < produits.get(a).getQuantite()) {
                plusButton.setOnAction((ActionEvent plus) -> {
                    newQuantity = produits.get(a).getQuantite() + 1;
                    double Prix = produits.get(a).getPrice();
                    PrixTot = Prix * newQuantity;
                    System.out.println(produits.get(a) + Integer.toString(newQuantity) + Double.toString(PrixTot) + "hhhhh");
                    totalprice.setText(Double.toString(PrixTot));
                    produits.get(a).setQuantite(newQuantity);
                    System.out.println(produits.get(a) + "nouveeauuuu");
                    System.out.println(newQuantity);
                    qte.setText(Integer.toString(newQuantity));
                });
            } else if (produits.get(a).getQuantite() == produits.get(a).getQuantite()) {
                plusButton.setDisable(true);
            }
        });

        carte.getSelectionModel().selectedItemProperty().addListener(lis -> {
            int a = carte.getSelectionModel().getSelectedIndex();
            if (produits.get(a).getQuantite() > 0) {
                moinsButton.setOnAction(moins -> {
                    newQuantity = produits.get(a).getQuantite() + 1;
                    double Prix = produits.get(a).getPrice();
                    PrixTot = Prix * newQuantity;
                    newQuantity = produits.get(a).getQuantite() - 1;
                    produits.get(a).setQuantite(newQuantity);
                    System.out.println(produits.get(a) + "moins");
                    System.out.println(newQuantity);
                    qte.setText(Integer.toString(newQuantity));
                    totalprice.setText(Double.toString(PrixTot));
                });
            } else if (produits.get(a).getQuantite() == 0) {
                moinsButton.setDisable(true);
            }
        });

        /* validerPanier.setOnAction(valid -> {
            try {
                panierService.getAll();
            } catch (SQLException ex) {
                Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); */
    }
    @FXML
    public void validerPanier() throws IOException, SQLException, ClassNotFoundException {

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Commande c = new Commande(1, date ,PrixTot);
        commandeService.addCommande(c);
        Commande d = commandeService.searchCommande(c);
        Cart.getPanier().forEach(x -> {
            CommandeDetails cd = new CommandeDetails(x.getId(), d.getId(), x.getQuantite());
            System.out.println(cd);
            try {
                commandeDetailService.addCommandeDetails(cd);
            } catch (SQLException ex) {
                Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       

    }
    private void Commande(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Products.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   
}   
