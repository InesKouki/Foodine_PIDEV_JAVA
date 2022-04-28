/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.entities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Ahmed
 */
public class Products {
    
    
    private Parent view;
    
    public Products() throws IOException {
        URL url = new File("C:/Users/Ahmed/Documents/NetBeansProjects/foodinejava/src/edu/foodinejava/gui/products.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        
        this.view = root;
    }
    
    public Parent getView(){
        return view;
    }
    
}
