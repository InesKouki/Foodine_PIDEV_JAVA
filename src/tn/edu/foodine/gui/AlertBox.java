/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.gui;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author EXTREME-GAMING
 */
public class AlertBox {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    public static void display(String title, String message){
        Stage window = new Stage();
        //restrict interaction untill this window alert is tooken care of
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Fermer");
        closeButton.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
    }
}

