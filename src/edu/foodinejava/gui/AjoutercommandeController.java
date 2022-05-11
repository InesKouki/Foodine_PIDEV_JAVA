/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.gui;

import edu.foodinejava.entities.Commande;
import edu.foodinejava.services.ServiceCommande;
import edu.foodinejava.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AjoutercommandeController implements Initializable {

    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfTotal;
    @FXML
    private TextField tfModepaiement;
    @FXML
    private TableColumn<Commande, String> colNom;
    @FXML
    private TableColumn<Commande, String> colTotal;
    @FXML
    private TableColumn<Commande, String> colModepaiement;
    @FXML
    private TableColumn<Commande, String> colDate;
    @FXML
    private TableView<Commande> tvCommande;

    private ServiceCommande com = new ServiceCommande();
    ObservableList<Commande> commandelist;
    int id = 0;
    @FXML
    private Button closeButton;
    @FXML
    private DatePicker tfDate;

    /**
     * Initializes the controller class.
     */
    public Connection getConnection() {
        Connection cnx;
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodine", "root", "");
            return cnx;
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
            return null;
        }
    }

    private void executeQuery(String query) {
        Connection cnx = getConnection();
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNom.setCellValueFactory(new PropertyValueFactory<Commande, String>("nom"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Commande, String>("total"));
        colModepaiement.setCellValueFactory(new PropertyValueFactory<Commande, String>("modepaiement"));
        colDate.setCellValueFactory(new PropertyValueFactory<Commande, String>("date"));
        refresh();
    }

    private void refresh() {
        ObservableList<Commande> list = com.getAll();
        tvCommande.setItems(list);
    }

    @FXML
    private void rowclicked(MouseEvent event) {
        Commande commande = tvCommande.getSelectionModel().getSelectedItem();
        id = commande.getId();
        tfNom.setText(commande.getNom());
        tfTotal.setText(commande.getTotal());
        tfModepaiement.setText(commande.getModepaiement());

    }

    @FXML
    private void ajouter(ActionEvent event) {
        Commande commande = new Commande(tfNom.getText(), tfTotal.getText(), Date.valueOf(tfDate.getValue()), tfModepaiement.getText());
        com.ajouter(commande);
        refresh();
    }

    @FXML
    private void modifier(ActionEvent event) {
        Commande commande = new Commande(id, tfNom.getText(), tfTotal.getText(), tfModepaiement.getText());
        com.modifier(commande);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Commande commande = tvCommande.getSelectionModel().getSelectedItem();
        com.supprimer(commande.getId());
    }
    private Parent root;
    Stage livra = new Stage();

    @FXML
    private void btnLivraison(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterlivraison.fxml"));
        root = loader.load();

        Scene scene = new Scene(root);
        livra.setScene(scene);
        livra.setMaximized(true);
        livra.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

}
