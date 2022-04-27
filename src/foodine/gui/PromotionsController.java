/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodine.gui;

import foodine.entities.Evenement;
import foodine.entities.Produit;
import foodine.entities.Promotion;
import foodine.services.ServiceEvenement;
import foodine.services.ServicePromotion;
import foodine.services.ServiceProduit;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
//import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class PromotionsController implements Initializable {

    @FXML
    private TextField tfPourcentage;
    @FXML
    private ComboBox<Produit> tfProduit;
    @FXML
    private ComboBox<Evenement> tfEvenement;
    @FXML
    private Button btnaddProm;
    @FXML
    private Button btnupdateProm;
    @FXML
    private Button btndeleteProm;
    @FXML
    private Button btnReset;
    @FXML
    private TableView<Promotion> promotionsTable;
    @FXML
    private TableColumn<Promotion, Double> colPourcentage;
    @FXML
    private TableColumn<Promotion, Produit> colProduit;
    @FXML
    private TableColumn<Promotion, Evenement> colEvenement;

    ObservableList<Promotion> promotionsList;
    ServicePromotion sp = new ServicePromotion();
    int promID = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setNumericConstraint();
        colPourcentage.setCellValueFactory(new PropertyValueFactory<Promotion, Double>("pourcentage"));
        colEvenement.setCellValueFactory(new PropertyValueFactory<Promotion, Evenement>("evenement_id"));
        colProduit.setCellValueFactory(new PropertyValueFactory<Promotion, Produit>("produit_id"));
        refreshData();
    }

    private void refreshData() {
        promotionsList = sp.getAll();
        promotionsTable.setItems(promotionsList);
        showEvents();
        showProduits();
    }

    @FXML
    private void addEvent(ActionEvent event) {
        if (tfPourcentage.getText() == null || tfEvenement.getValue() == null || tfProduit.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Veuillez vérifier les données");
            a.showAndWait();
        } else {
            try {
                Promotion p = new Promotion(Double.parseDouble(tfPourcentage.getText()) / 100, tfEvenement.getValue(), tfProduit.getValue());
                sp.ajouter(p);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Promotion ajoutée");
                a.showAndWait();
                refreshData();
                clear();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
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

    @FXML
    private void deleteEvent(ActionEvent event) {
        Promotion ev = promotionsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
        alert.getDialogPane().setContentText("Voulez vous vraiment supprimer cette événement?");
        alert.getDialogPane().setHeaderText("Suppression");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sp.supprimer(ev.getId());
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Promotion supprimée", ButtonType.OK);
            a.showAndWait();
            refreshData();
            clear();
            btnupdateProm.setDisable(true);
            btndeleteProm.setDisable(true);
            btnaddProm.setDisable(false);
        }
    }

    @FXML
    private void resetFields(ActionEvent event) {
        btnaddProm.setDisable(false);
        btnupdateProm.setDisable(true);
        btndeleteProm.setDisable(true);
        clear();
    }

    @FXML
    private void rowClicked(MouseEvent event) {
        btnaddProm.setDisable(true);
        btnupdateProm.setDisable(false);
        btndeleteProm.setDisable(false);

        Promotion p = promotionsTable.getSelectionModel().getSelectedItem();
        promID = p.getId();
        tfPourcentage.setText(String.valueOf(Math.round(p.getPourcentage())));
        tfEvenement.setValue(p.getEvenement_id());
        tfProduit.setValue(p.getProduit_id());
    }

    private void showEvents() {
        List<Evenement> listEv = new ServiceEvenement().getAll();
        ArrayList<Evenement> events = new ArrayList<>();
        for (Evenement ev : listEv) {
            Evenement e = new Evenement();
            e.setId(ev.getId());
            e.setName(ev.getName());
            events.add(e);
        }
        ObservableList<Evenement> choices = FXCollections.observableArrayList(events);
        tfEvenement.setItems(choices);
    }

    private void showProduits() {
        List<Produit> listPr = new ServiceProduit().getAll();

        ArrayList<Produit> promotions = new ArrayList<>();
        for (Produit ev : listPr) {
            Produit e = new Produit();
            e.setId(ev.getId());
            e.setName(ev.getName());
            promotions.add(e);
        }
        ObservableList<Produit> choices2 = FXCollections.observableArrayList(promotions);
        tfProduit.setItems(choices2);
    }

    private void clear() {
        tfPourcentage.clear();
        tfEvenement.setValue(null);
        tfProduit.setValue(null);
    }

    private void setNumericConstraint() {
        tfPourcentage.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfPourcentage.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

}
