/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< Updated upstream:src/edu/foodinejava/tests/FXMain.java
package edu.foodinejava.tests;
=======
package tn.edu.foodine.tests;
>>>>>>> Stashed changes:src/tn/edu/foodine/tests/FXMain.java

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.xml.stream.Location;

/**
 *
 * @author Ahmed
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
<<<<<<< Updated upstream:src/edu/foodinejava/tests/FXMain.java
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ajouterlivraison.fxml"));
=======
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Authentification.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Authentification.fxml"));
>>>>>>> Stashed changes:src/tn/edu/foodine/tests/FXMain.java
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion des personnes");
        primaryStage.show();
    }
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
