/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.gui;

import edu.foodinejava.entities.Panier;
import edu.foodinejava.entities.Panierf;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class PanierController implements Initializable {

    @FXML
    private VBox vbPanier;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * @throws java.io.FileNotFoundException
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        List <Panier> entries = Panierf.getInstance().getEntries();
        vbPanier.getChildren().clear();
        
        if(entries.isEmpty()){
            Label labelVide = new Label("panier vide");
            vbPanier.getChildren().add(labelVide);
                  
        }else{
            Label titre = new Label("Panier");
            vbPanier.getChildren().add(titre);
            
            for(Panier panier:entries){
                try {
                    HBox productView = panierEntryView(panier);
                    vbPanier.getChildren().add(productView);
                } catch (FileNotFoundException ex) {
                   
                }
            }
        }
    }    
    
    private HBox panierEntryView(Panier panier) throws FileNotFoundException{
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER_LEFT);
        FileInputStream input = new FileInputStream("../resources"+panier.getProduct().getImageFile());
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        
        Label productName = new Label(panier.getProduct().name());
        productName.setPrefWidth(100);
        productName.setStyle("-fx-font-size:15pt;-fx-padding:5px");
        
        Label quantity = new Label(String.valueOf(panier.getQuantity()));
        quantity.setStyle("-fx-padding:5px");
        
        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-padding:5px");
        
        Button minusButton = new Button("+");
        minusButton.setStyle("-fx-padding:5px");
        
        Label price = new Label(String.valueOf("Dt"+panier.getProduct().getPrice()));
        price.setStyle("-fx-padding:5px");
        
        layout.getChildren().addAll(imageView,productName,plusButton,minusButton,quantity,price);
        
        return layout;
    }
    
}
