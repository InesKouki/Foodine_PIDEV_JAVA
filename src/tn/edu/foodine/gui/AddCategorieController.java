/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import tn.edu.foodine.entities.Categorie;
import tn.edu.foodine.entities.Produit;
import javafx.scene.image.Image;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tn.edu.foodine.services.CategorieCRUD;
import tn.edu.foodine.utils.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author windows 10
 */
public class AddCategorieController implements Initializable {

    @FXML
    private TextField lblNomCat;
    @FXML
    private TableView<Categorie> tab;
    @FXML
    private TableColumn<Categorie, String> NomCatTab;
    @FXML
    private TableColumn<Categorie, String> ImageTab;

    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField textrechercher;
    ObservableList<String> listW1;
    @FXML
    private Pane backgroundPane;

    @FXML
    private Button ajoutersuj;

    int ID;
    CategorieCRUD pcr = new CategorieCRUD();
    ObservableList<Categorie> list = FXCollections.observableArrayList();
    String filepath = null, filename = null, fn = null;
    String uploads = "C:/Users/habib/Desktop/Foodine_PIDEV/public/uploads/";
    FileChooser fc = new FileChooser();

    @FXML
    private ImageView uploadIv;
    @FXML
    private Button btnUpload;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCategorie();

    }

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        try {
            if (lblNomCat.getText().isEmpty()) {
//                // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Categorie!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Un nom de Categorie svp!");
//                a.showAndWait();
                Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez donner le nom").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
                notificationBuilder.showError();

            } else if (filename == null) {
//                // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Categorie!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Une image svp!");
//                a.showAndWait();
                Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez ajouter une image").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
                notificationBuilder.showError();

            } else {

                //save Categorie in DATA BASE
                String nomCat = lblNomCat.getText();
                Categorie t = new Categorie(nomCat, filename);
                CategorieCRUD Categorie = new CategorieCRUD();
                Categorie.addCategorie(t);
                ///Alert
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Categorie!");
//                a.setHeaderText(null);
//                a.setContentText("Categorie ajouté!");
//                a.showAndWait();
                Notifications notificationBuilder = Notifications.create().title("Succés").text("Catégorie ajoutée avec succés").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
                notificationBuilder.showConfirm();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        showCategorie();
    }

    public void showCategorie() {
        list = pcr.getCategorie();
        NomCatTab.setCellValueFactory(new PropertyValueFactory<Categorie, String>("name"));
        ImageTab.setCellValueFactory(new PropertyValueFactory<Categorie, String>("image"));

        tab.setItems(list);
    }

    @FXML
    private void SetValue(MouseEvent event) {
        Categorie selected = tab.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblNomCat.setText(String.valueOf(selected.getName()));
            ID = selected.getId();
            fn = selected.getImage();
            Image im = new Image("file:" + uploads + selected.getImage());
            uploadIv.setImage(im);
        }
    }

    @FXML
    private void SupprimerCategorie(ActionEvent event) {

        Categorie selected = tab.getSelectionModel().getSelectedItem();
        if (selected != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Voulez-Vous Supprimer cet Categorie?");
            alert.setContentText("Supprimer?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    pcr.supprimerCategorie(ID);
                    showCategorie();
                }
            });
        } else {
//            Alert a = new Alert(Alert.AlertType.WARNING);
//            a.setTitle(" Categorie!");
//            a.setHeaderText(null);
//            a.setContentText("Selectionner une Categorie!");
//            a.showAndWait();
            Notifications notificationBuilder = Notifications.create().title("Succés").text("Catégorie supprimée avec succés").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
            notificationBuilder.showConfirm();
        }
        showCategorie();
    }

    @FXML
    private void ModifierCategorie(ActionEvent event) {
        String NomCat = lblNomCat.getText();
        Categorie c = new Categorie(ID, NomCat, fn);

        if (lblNomCat.getText().isEmpty()) {
//                // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Categorie!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Un nom de Categorie svp!");
//                a.showAndWait();
            Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez donner le nom").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
            notificationBuilder.showError();

        } else if (fn == null) {
//                // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Categorie!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Une image svp!");
//                a.showAndWait();
            Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez ajouter une image").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
            notificationBuilder.showError();

        } else {
            pcr.modifierCategorie(c);
//        Alert a = new Alert(Alert.AlertType.WARNING);
//        a.setTitle("Categorie!");
//        a.setHeaderText(null);
//        a.setContentText("Categorie Modifié!");
//        a.showAndWait();

            Notifications notificationBuilder = Notifications.create().title("Succés").text("Catégorie modifiée avec succés").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
            notificationBuilder.showConfirm();

            showCategorie();
        }
    }

    @FXML
    private void uploadImage(ActionEvent event) throws IOException {
        // Set the title of the displayed file dialog 
        fc.setTitle("Choisir une image");
        // Gets the extension filters used in the displayed file dialog. 
        fc.getExtensionFilters().clear();
        // Removes all of the elements from this list 
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        // Set the selected file or null if no file has been selected 
        File file = fc.showOpenDialog(null);
        // Shows a new file open dialog.
        if (file != null) {
            // URI that represents this abstract pathname 
            uploadIv.setImage(new Image(file.toURI().toString()));

            filename = file.getName();
            filepath = file.getAbsolutePath();

            fn = filename;

            FileChannel source = new FileInputStream(filepath).getChannel();
            FileChannel dest = new FileOutputStream(uploads + filename).getChannel();
            dest.transferFrom(source, 0, source.size());
        } else {
            System.out.println("Fichier invalide!");
        }
    }

    @FXML
    private void search(KeyEvent event
    ) {
        FilteredList<Categorie> filter = new FilteredList<>(list, e -> true);

        textrechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(t -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (t.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Categorie> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sort);
    }

}
