/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodine.gui;

import foodine.entities.Categorie;
import foodine.entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    String uploads = "C:/Users/habib/Desktop/Foodine_PIDEV/public/uploads/";
    @FXML
    private Label labelCategory;

    public void setData(Produit prod) {
        this.prod = prod;
        labelName.setText(prod.getName());
        labelPrix.setText(prod.getPrice() + " DT");
        labelCategory.setText(prod.getCategorie().getName());
        Image im = new Image("file:" + uploads + prod.getImage());
        img.setImage(im);
    }
}
