/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.edu.foodine.entities.Planning;
import tn.edu.foodine.entities.Recette;
import tn.edu.foodine.services.ServicePlanning;
import tn.edu.foodine.services.ServiceRecette;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FrontRecetteController implements Initializable {
    String up = "C:\\Users\\PC\\Desktop\\Mobile\\Foodine_PIDEV_Desktop\\src\\img\\";
    
    @FXML
    private GridPane RecetteContainer;
    @FXML
    private Button retour;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Recette> list = new ServiceRecette().getAll();
        int col=0,row=1;
        try {
            for (Recette event : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventItem.fxml"));
                AnchorPane itemBox = fxmlLoader.load();
                EventItemController itemController = fxmlLoader.getController();
                itemController.setData(event);

                if (col == 3) {
                    col = 0;
                    row++;
                }

           RecetteContainer.add(itemBox,col++,row);
           GridPane.setMargin(itemBox, new Insets(30));
                
            }
        } catch (IOException ex) {
        }}
    Stage front = new Stage();
    private Parent root;
    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterPlanning.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        
        front.setScene(scene);
        front.setMaximized(true);
        front.show();
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
      
    
}
