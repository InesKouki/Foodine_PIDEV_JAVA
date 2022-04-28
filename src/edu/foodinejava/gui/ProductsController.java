/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.gui;

import edu.foodinejava.entities.Panierf;
import edu.foodinejava.entities.Product;

import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ProductsController implements Initializable {

    @FXML
    private GridPane gpProduct;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * @throws java.io.FileNotFoundException
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            gpProduct.getChildren().clear();
            
            VBox productView1 = productView(Product.pizza);
            gpProduct.add(productView1,0,0);
            
            
            VBox productView2 = productView(Product.burger);
            gpProduct.add(productView2,0,0);
        } catch (FileNotFoundException ex) {
           
        }
        
    }    
    
    
    private VBox productView(Product product) throws FileNotFoundException{
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        FileInputStream input = new FileInputStream("../resources"+product.getImageFile());
        
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        
        Label productName = new Label(product.name());
        Label price = new Label(product.getPrice()+ "Dt");
        
        Button addButton = new Button("ajouter au panier");
        addButton.setUserData(product.name());
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {   
               Node sourceComponent =  (Node)event.getSource();
               String productName = (String)sourceComponent.getUserData(); 
               Panierf panierf = Panierf.getInstance();
               panierf.addProduct(productName);
            }
        });
        layout.getChildren().addAll(imageView,productName,price,addButton);
        
        return layout;
    }
    
    
    
}
