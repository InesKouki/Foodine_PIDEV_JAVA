/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import tn.edu.foodine.entities.Livraison;
import tn.edu.foodine.services.ServiceLivraison;
import tn.edu.foodine.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;



/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class BacklivraisonController implements Initializable {

    @FXML
    private TextField tfCodepostal;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfAddresse;
    @FXML
    private TextField tfDetails;
    @FXML
    private TextField tfEmail;
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
    private TableColumn<Livraison, String> colDetails;
    @FXML
    private Button closeButton;
    private ServiceLivraison liv = new ServiceLivraison();
    ObservableList<Livraison> livraisonlist;
    int id = 0;
    private Button comm;
    private ObservableList<Livraison> getTableList() {
       
        ObservableList<Livraison> List = liv.getAll();
        return List ;
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colAddresse.setCellValueFactory(new PropertyValueFactory<Livraison, String>("addresse"));
        colCodepostal.setCellValueFactory(new PropertyValueFactory<Livraison, String>("codepostal"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Livraison, String>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Livraison, String>("phone"));
        colDetails.setCellValueFactory(new PropertyValueFactory<Livraison, String>("details"));
        refresh();
    }    

    @FXML
    private void rowclicked(MouseEvent event) {
         Livraison livraison = tvLivraison.getSelectionModel().getSelectedItem();
       
        tfAddresse.setText(livraison.getAddresse());
        tfCodepostal.setText(livraison.getCodepostal());
        tfEmail.setText(livraison.getEmail());
        tfPhone.setText(livraison.getPhone());
        tfDetails.setText(livraison.getDetails());
    }

    @FXML
    private void supprimer1(ActionEvent event) {
        Livraison livraison = tvLivraison.getSelectionModel().getSelectedItem();
        liv.supprimer(livraison.getId());
        refresh();
    }

    @FXML
    private void modifier1(ActionEvent event) {
    }

    @FXML
    private void btnCommande(ActionEvent event) {
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
}
