/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Reservation;
import tn.edu.esprit.entities.Table;
import tn.edu.esprit.services.servicereservation;
import tn.edu.esprit.services.servicetable;
import tn.edu.esprit.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterreservationController implements Initializable {

    @FXML
    private DatePicker tfdateRES;
    @FXML
    private TextField tfnomRES;
    @FXML
    private TextField tfnumtelRES;
    @FXML
    private TextField tfmailRES;
    @FXML
    private TextField tfnumtableRES;
    @FXML
    private TableView<Reservation> reservationaffichage;
    @FXML
    private TableColumn<Reservation, String> nomreservation;
    @FXML
    private TableColumn<Reservation, Integer> numtelreservation;
    @FXML
    private TableColumn<Reservation, String> mailreservation;
    @FXML
    private TableColumn<Reservation, Date> datereservation;
    @FXML
    private TableColumn<Reservation, Integer> numtabreservation;
    @FXML
    private TableColumn<Reservation, Integer> idreservation;
    @FXML
    private ComboBox<Table> cbnumtab;
    servicereservation sr = new servicereservation();
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showreservation();
        showtable();
    }

    ObservableList<Reservation> list = sr.getAll();

    @FXML
    private void ajouterreservation(ActionEvent event) {
        if (tfnomRES.getText().isEmpty() || tfdateRES.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.WARNING, "verifier les donnees");
            a.showAndWait();
        } else if (tfdateRES.getValue().isBefore(LocalDate.now()))
        {
            Alert a = new Alert(Alert.AlertType.WARNING, "verifier la date");
            a.showAndWait();
        }
        else {

            Reservation p = new Reservation(cbnumtab.getValue(), tfnomRES.getText(), Date.valueOf(tfdateRES.getValue()), Integer.parseInt(tfnumtelRES.getText()), tfmailRES.getText());
            System.out.println(cbnumtab.getValue());
            sr.ajouter(p);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "reservation ajoutée");
            a.showAndWait();
            refresh();

        }
    }

//Date dateRES=java.sql.Date.valueOf(tfdateRES.getValue());
    /*String nomres=tfnomRES.getText();
        int numres=Integer.parseInt(tfnumtelRES.getText());
        
        String mailres=tfmailRES.getText();
       // Table numtabres = new Table();
        /*Reservation r = new Reservation();
        Table t1 = new Table(tfnumtableRES.getText("t.numerotable"));
       int numtabres= Integer.parseInt(tfnumtableRES.getText());
        
        
        
       Reservation r= new Reservation( numtabres,nomres, dateRES, numres, mailres);
       servicereservation sr = new servicereservation();
        sr.ajouter(r);*/
    public void showreservation() {
        setNumericConstraint();
        nomreservation.setCellValueFactory(new PropertyValueFactory<Reservation, String>("nom"));
        numtelreservation.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("mobile"));
        mailreservation.setCellValueFactory(new PropertyValueFactory<Reservation, String>("email"));
        datereservation.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("datereservation"));
        numtabreservation.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("tableid"));
        idreservation.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id"));

        refresh();
    }

    private void refresh() {
        ObservableList<Reservation> list = sr.getAll();
        reservationaffichage.setItems(list);
        showtable();
    }

    @FXML
    private void btnsupprimerreseervation(ActionEvent event) {
        Reservation res = reservationaffichage.getSelectionModel().getSelectedItem();
        sr.supprimer(res.getId());
        list.remove(res);
        refresh();
    }

    int idres = 0;

    public void modifier() {
        Reservation ev = new Reservation(idres, cbnumtab.getValue(), tfnomRES.getText(), Date.valueOf(tfdateRES.getValue()), Integer.parseInt(tfnumtelRES.getText()), tfmailRES.getText());
        sr.modifier(ev);
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Evénement modifié", ButtonType.OK);
        a.showAndWait();
        refresh();
    }

    /* @FXML
    private void addEvent(ActionEvent event) {
    
            try {
                Reservation p = new Reservation(cbnumtab.getValue(),tfnomRES.getText(), Date.valueOf(tfdateRES.getValue()) ,Integer.parseInt(tfnumtelRES.getText()),tfmailRES.getText());
                sp.ajouter(p);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Promotion ajoutée");
                a.showAndWait();
                refreshData();
                clear();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    

   /* @FXML
    private void updateEvent(ActionEvent event) {
        if (tfPourcentage.getText() == null || tfEvenement.getValue() == null || tfProduit.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Veuillez vérifier les données");
            a.showAndWait();
        } else {
            Promotion p = new Promotion(promID, Double.parseDouble(tfPourcentage.getText()) / 100, tfEvenement.getValue(), tfProduit.getValue());
            sp.modifier(p);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Promotion modifiée");
            a.showAndWait();
            refreshData();
            clear();
            btnupdateProm.setDisable(true);
            btndeleteProm.setDisable(true);
            btnaddProm.setDisable(false);
        }
    }
     
    

     */
    @FXML
    private void btnmodifierreservation(ActionEvent event) {
        modifier();
    }

    @FXML
    private void rowClicked(javafx.scene.input.MouseEvent event) {
        Reservation ev = reservationaffichage.getSelectionModel().getSelectedItem();
        idres = ev.getId();

        tfnomRES.setText(ev.getNom());
        tfnumtelRES.setText(String.valueOf(ev.getMobile()));

        tfmailRES.setText(ev.getEmail());
        tfdateRES.setValue(ev.getDatereservation().toLocalDate());

        cbnumtab.setValue(ev.getTableid());
        /*dateDeb.setValue(ev.getDate_deb().toLocalDate());
        dateFin.setValue(ev.getDate_fin().toLocalDate());*/
        //tfnumtelRES.set(ev.getMobile());
    }

    private void showtable() {
        List<Table> listt = new servicetable().getAll();
        ObservableList<Table> choice = FXCollections.observableArrayList(listt);

        cbnumtab.setItems(choice);
    }

    private void setNumericConstraint() {
        tfnumtelRES.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfnumtelRES.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    private Parent root;
    Stage recette = new Stage();

    @FXML
    private void passeratable(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ajoutertable.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);

        recette.setScene(scene);
        recette.setMaximized(true);//????? pour configurer la fenetre
        recette.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
