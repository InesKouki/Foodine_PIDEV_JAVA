/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class NewFXMainTable extends Application {
    
    @Override
    public void start(Stage primaryStage)  throws IOException {
      
        
            Parent root = FXMLLoader.load(getClass().getResource("../gui/ajoutertable.fxml"));
            
            
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("ajouter table");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
            
             Parent root2 = FXMLLoader.load(getClass().getResource("../gui/ajouterreservation.fxml"));
             Scene scene2 = new Scene(root2);
             
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
