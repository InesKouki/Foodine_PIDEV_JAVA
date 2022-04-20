/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.edu.foodine.entities.Planning;
import tn.edu.foodine.services.ServicePlanning;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AjouterPlanningController implements Initializable {

    int pId = 0;
    @FXML
    private TextField tfNom;
    @FXML
    private DatePicker dtDate;
    @FXML
    private Button btn;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private TableColumn<Planning, String> colNom;
    @FXML
    private TableColumn<Planning, Date> colDate;
    @FXML
    private TableView<Planning> tvPlanning;
    @FXML
    private TableColumn<Planning, Integer> colId;
    ObservableList<Planning> list;
    ServicePlanning sp = new ServicePlanning();
    @FXML
    private Button plannig;
    @FXML
    private Button closeButton;
    @FXML
    private Button reset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPlanning();
    }

    @FXML
    private void ajouterPlanning(ActionEvent event) throws IOException {
        //Instantiating the SimpleDateFormat class

        if ((tfNom.getText().isEmpty()) || dtDate.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.OK);
            a.showAndWait();
        } else if (java.time.LocalDate.now().isAfter(dtDate.getValue())) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Date Invalid", ButtonType.OK);
            a.showAndWait();
        } else {
            Planning p = new Planning(tfNom.getText(), Date.valueOf(dtDate.getValue()));
            sp.ajouter(p);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Planning ajouter", ButtonType.OK);
            a.showAndWait();
            refresh();


            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPlanning.fxml"));
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
            AfficherPlanningController ap = loader.getController();
            ap.SetNom(tfNom.getText());*/
        }
    }

    public void refresh() {
        list = sp.getAll();
        tvPlanning.setItems(list);
    }

    public void showPlanning() {
        colId.setCellValueFactory(new PropertyValueFactory<Planning, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Planning, String>("nom"));
        colDate.setCellValueFactory(new PropertyValueFactory<Planning, Date>("date"));
        list = sp.getAll();
        tvPlanning.setItems(list);

    }

    @FXML
    public void updatePlanning(ActionEvent event) {
        if ((tfNom.getText().isEmpty()) || dtDate.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.OK);
            a.showAndWait();
        } else if (java.time.LocalDate.now().isAfter(dtDate.getValue())) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Date Invalid", ButtonType.OK);
            a.showAndWait();
        } else {
            Planning p = new Planning(pId, Date.valueOf(dtDate.getValue()), tfNom.getText());
            sp.modifier(p);
            refresh();
        }
    }

    @FXML
    private void rowClicked(javafx.scene.input.MouseEvent event) {
        Planning p = tvPlanning.getSelectionModel().getSelectedItem();
        pId = p.getId();
        tfNom.setText(p.getNom());
        dtDate.setValue(p.getDate().toLocalDate());
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn.setDisable(true);
    }

    @FXML
    private void supprimerPlanning(ActionEvent event) {
        Planning p = tvPlanning.getSelectionModel().getSelectedItem();
        sp.supprimer(p.getId());
        refresh();
    }

    /*
    public void showPlanning() {
        colNom.setCellFactory(TextFieldTableCell.forTableColumn());
        colNom.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
        });
        colDate.setCellFactory(TextFieldTableCell.forTableColumn());
        colDate.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setDate(e.getNewValue());
        });
    }
    public void loadData(){
        list = sp.getAll();
        tvPlanning.setItems(list);
    }
     */
    private Parent root;
    Stage recette = new Stage();

    @FXML
    private void recette(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Recette.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        
        recette.setScene(scene);
        recette.setMaximized(true);
        recette.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
        
    }
    public void clear(){
        tfNom.clear();
        dtDate.setValue(null);
    }

    @FXML
    private void reset(ActionEvent event) {
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn.setDisable(false);
        clear();
    }
}
