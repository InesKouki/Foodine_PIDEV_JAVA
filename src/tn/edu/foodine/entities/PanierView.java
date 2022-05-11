/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.entities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Ahmed
 */
public class PanierView {
    
    private Parent view;
    public PanierView() throws MalformedURLException, IOException{
        URL uiFile = new File("../edu/foodinejava/gui/panier.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(uiFile);
        this.view = root;
    }
    
    public Parent getView(){
        return this.view;
    }
    
}
