/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.foodinejava.gui;

import com.sun.org.apache.xerces.internal.util.FeatureState;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import edu.foodinejava.entities.Livraison;
import edu.foodinejava.services.ServiceLivraison;
import edu.foodinejava.utils.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/*

* To change this license header, choose License Headers in Project Properties.

* To change this template file, choose Tools | Templates

* and open the template in the editor.

 */
/**
 *
 * FXML Controller class
 *
 *
 *
 * @author Aziz
 *
 */
public class AjouterlivraisonController implements Initializable {

    @FXML

    private TextField tfAddresse;

    @FXML

    private TextField tfCodepostal;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPhone;
    private TextField tfDetail;
    @FXML
    private TableView<Livraison> tvLivraison;
    @FXML
    private TableColumn<Livraison, String> colAddresse;
    @FXML
    private TableColumn<Livraison, String> colCodepostal;
    @FXML
    private TableColumn<Livraison, String> colEmail;
    @FXML
    private TableColumn<Livraison, String> colPhone;
    @FXML
    private TextField tfDetails;
    @FXML
    private TableColumn<Livraison, String> colDetails;
    private Button btnUpdate;

    private ServiceLivraison liv = new ServiceLivraison();
    ObservableList<Livraison> livraisonlist;
    int id = 0;
    private Button comm;
    @FXML
    private Button closeButton;

    /**
     *
     * Initializes the controller class.
     *
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        colAddresse.setCellValueFactory(new PropertyValueFactory<Livraison, String>("addresse"));
        colCodepostal.setCellValueFactory(new PropertyValueFactory<Livraison, String>("codepostal"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Livraison, String>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Livraison, String>("phone"));
        colDetails.setCellValueFactory(new PropertyValueFactory<Livraison, String>("details"));
        refresh();
    }

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

    @FXML
    private void ajouterlivraison(ActionEvent event) {

        Livraison livraison = new Livraison(tfAddresse.getText(), tfCodepostal.getText(), tfEmail.getText(), tfPhone.getText(), tfDetails.getText());

        tvLivraison.getItems().add(livraison);

    }

    private void insert() {
        String query = "INSERT INTO livraison VALUES('" + tfAddresse.getText() + "','" + tfCodepostal.getText() + "','" + tfEmail.getText() + "','" + tfPhone.getText() + "','" + tfDetails.getText() + "')";
        executeQuery(query);
        refresh();
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

    private void refresh() {
        ObservableList<Livraison> list = getLivraisonList();
        tvLivraison.setItems(list);
    }

    public ObservableList<Livraison> getLivraisonList() {
        ObservableList<Livraison> ll = FXCollections.observableArrayList();
        Connection cnx = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM livraison";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Livraison livraison;
            while (rs.next()) {
                livraison = new Livraison(rs.getInt("id"), rs.getString("addresse"), rs.getString("codepostal"), rs.getString("email"), rs.getString("phone"), rs.getString("details"));
                ll.add(livraison);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ll;
    }

    @FXML
    private void rowclicked(MouseEvent event) {
        Livraison livraison = tvLivraison.getSelectionModel().getSelectedItem();
        id = livraison.getId();
        tfAddresse.setText(livraison.getAddresse());
        tfCodepostal.setText(livraison.getCodepostal());
        tfEmail.setText(livraison.getEmail());
        tfPhone.setText(livraison.getPhone());
        tfDetails.setText(livraison.getDetails());
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Livraison livraison = tvLivraison.getSelectionModel().getSelectedItem();
        liv.supprimer(livraison.getId());
        refresh();
    }

    @FXML
    private void modifier(ActionEvent event) {
        Livraison livraison = new Livraison(id, tfAddresse.getText(), tfCodepostal.getText(), tfEmail.getText(), tfPhone.getText(), tfDetails.getText());
        liv.modifier(livraison);
        refresh();
    }

    private Parent root;
    Stage com = new Stage();

    @FXML
    private void btnCommande(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutercommande.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        com.setScene(scene);
        com.setMaximized(true);
        com.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

}
