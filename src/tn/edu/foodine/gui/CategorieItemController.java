/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import tn.edu.foodine.entities.Categorie;
import tn.edu.foodine.entities.Produit;
import tn.edu.foodine.services.Cart;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author habib
 */
public class CategorieItemController {

    @FXML
    private ImageView img;
    @FXML
    private Label labelName;
    @FXML
    private Label labelPrix;

    private Produit prod;

    String uploads = "C:/Users/azizm/Desktop/SEM2/PIDEV/Foodine_PIDEV/public/uploads/";

    @FXML
    private Label labelCategory;
    @FXML
    private Button addToCart;

    public void setData(Produit prod) {
        this.prod = prod;
        labelName.setText(prod.getName());
        labelPrix.setText(prod.getPrice() + " DT");
        labelCategory.setText(prod.getCategorie().getName());
        Image im = new Image("file:" + uploads + prod.getImage());
        img.setImage(im);
    }

    @FXML
    private void addToCart(ActionEvent event) {
        new Cart().addPanier(this.prod);
        edu.foodinejava.gui.AlertBox.display("Ajout au panier", "Produit " + this.prod.getName() + " est ajouté au panier");
    }
}
