/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichertableController implements Initializable {

    @FXML
    private TextField tfnumtableafficher;
    @FXML
    private TextField tfnbplaceafficher;
    @FXML
    private TextField tfimageafficher;
    @FXML
    private TextField tfetatafficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void tfnumtableafficher(String message) {
        this.tfnumtableafficher.setText(message);
    }

    public void tfnbplaceafficher(String message) {
        this.tfnbplaceafficher.setText(message);
    }

    public void tfimageafficher(String message) {
        this.tfimageafficher.setText(message);
    }

    public void tfetatafficher(String message) {
        this.tfetatafficher.setText(message);
    }

}
