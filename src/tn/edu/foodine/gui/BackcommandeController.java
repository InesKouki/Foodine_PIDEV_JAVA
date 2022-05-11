/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import tn.edu.foodine.entities.Commande;
import tn.edu.foodine.services.ServiceCommande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class BackcommandeController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfTotal;
    @FXML
    private TextField tfModepaiement;
    @FXML
    private TableView<Commande> tvCommande;
    @FXML
    private TableColumn<Commande, String> colNom;
    @FXML
    private TableColumn<Commande, String> colTotal;
    @FXML
    private TableColumn<Commande, String> colModepaiement;
    @FXML
    private TableColumn<Commande, String> colDate;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private DatePicker tfDate;
    @FXML
    private Button closeButton;
     private ServiceCommande com = new ServiceCommande();
    ObservableList<Commande> commandelist;
    int id = 0;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         colNom.setCellValueFactory(new PropertyValueFactory<Commande, String>("nom"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Commande, String>("total"));
        colModepaiement.setCellValueFactory(new PropertyValueFactory<Commande, String>("modepaiement"));
        colDate.setCellValueFactory(new PropertyValueFactory<Commande, String>("date"));
        refresh();
    }    

    @FXML
    private void rowclicked(MouseEvent event) {
    //     Commande commande = tvCommande.getSelectionModel().getSelectedItem();
     //   id = commande.getId();
      //  tfNom.setText(commande.getNom());
      //  tfTotal.setText(commande.getTotal().t);
       // tfModepaiement.setText(commande.getModepaiement());

    }


    @FXML
    private void btnLivraison(ActionEvent event) {
        
    }

    @FXML
    private void modifier2(ActionEvent event) {
        
    }

    @FXML
    private void supprimer2(ActionEvent event) {
        /*     Commande commande = tvCommande.getSelectionModel().getSelectedItem();
        com.supprimer(commande.getId());
        refresh();*/
    }
    
    private void refresh() {
        ObservableList<Commande> list = com.getAllCommande();
        tvCommande.setItems(list);
    }
}
