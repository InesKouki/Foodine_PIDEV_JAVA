/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodine.gui;

import foodine.entities.Evenement;
import foodine.services.ServiceEvenement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class EventsController implements Initializable {

    @FXML
    private TableView<Evenement> eventsTable;
    @FXML
    private TableColumn<Evenement, String> colName;
    @FXML
    private TableColumn<Evenement, String> colDateDeb;
    @FXML
    private TableColumn<Evenement, String> colDateFin;
    @FXML
    private TableColumn<Evenement, String> colDescription;
    @FXML
    private TableColumn<Evenement, String> colImage;

    ObservableList<Evenement> eventsList;
    ObservableList<Evenement> temp = FXCollections.observableArrayList();
    ServiceEvenement se = new ServiceEvenement();
    int evID = 0;
    String fn = null;
    FileChooser fc = new FileChooser();
    String filename = null;
    String filepath = null;
    String uploads = "C:/Users/azizm/Desktop/SEM2/PIDEV/Foodine_PIDEV/public/uploads/";

    @FXML
    private Button btnaddEvent;
    @FXML
    private TextField tfNom;
    @FXML
    private DatePicker dateDeb;
    @FXML
    private TextArea tfDescription;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Button btnupdateEvent;
    @FXML
    private Button btndeleteEvent;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpload;
    @FXML
    private ImageView uploadIV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        colImage.setPrefWidth(200);
        colName.setCellValueFactory(new PropertyValueFactory<Evenement, String>("name"));
        colDateDeb.setCellValueFactory(new PropertyValueFactory<Evenement, String>("date_deb"));
        colDateFin.setCellValueFactory(new PropertyValueFactory<Evenement, String>("date_fin"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        colImage.setCellValueFactory(new PropertyValueFactory<Evenement, String>("iv"));

        refreshData();
    }

    @FXML
    private void addEvent(ActionEvent event) {
        if (tfNom.getText().isEmpty() || dateDeb.getValue() == null || dateFin.getValue() == null || tfDescription.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Veuillez vérifier les données");
            a.showAndWait();
        } else if (dateDeb.getValue().isAfter(dateFin.getValue()) || dateDeb.getValue().isBefore(LocalDate.now()) || dateFin.getValue().isBefore(LocalDate.now())) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Veuillez vérifier les dates");
            a.showAndWait();
        } else if (filename == null) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Veuillez ajouter une image");
            a.showAndWait();
        } else {
            try {
                Evenement ev = new Evenement(tfNom.getText(), Date.valueOf(dateDeb.getValue()), Date.valueOf(dateFin.getValue()), tfDescription.getText(), filename);
                se.ajouter(ev);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Evénement ajouté");
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
        if (tfNom.getText().isEmpty() || dateDeb.getValue() == null || dateFin.getValue() == null || tfDescription.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Veuillez vérifier les données", ButtonType.OK);
            a.showAndWait();
        } else if (dateDeb.getValue().isAfter(dateFin.getValue()) || dateDeb.getValue().isBefore(LocalDate.now()) || dateFin.getValue().isBefore(LocalDate.now())) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Veuillez vérifier les dates");
            a.showAndWait();
        } else {
            Evenement ev = new Evenement(evID, tfNom.getText(), Date.valueOf(dateDeb.getValue()), Date.valueOf(dateFin.getValue()), tfDescription.getText(), fn);
            se.modifier(ev);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Evénement modifié", ButtonType.OK);
            a.showAndWait();
            refreshData();
            clear();
            btnupdateEvent.setDisable(true);
            btndeleteEvent.setDisable(true);
            btnaddEvent.setDisable(false);
        }
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        Evenement ev = eventsTable.getSelectionModel().getSelectedItem();
        se.supprimer(ev.getId());
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Evénement supprimé", ButtonType.OK);
        a.showAndWait();
        refreshData();
        clear();
        btnupdateEvent.setDisable(true);
        btndeleteEvent.setDisable(true);
        btnaddEvent.setDisable(false);
    }

    @FXML
    private void rowClicked(MouseEvent event) {
        btnaddEvent.setDisable(true);
        btnupdateEvent.setDisable(false);
        btndeleteEvent.setDisable(false);
        Evenement ev = eventsTable.getSelectionModel().getSelectedItem();
        evID = ev.getId();
        fn = ev.getImage();
        tfNom.setText(ev.getName());
        dateDeb.setValue(ev.getDate_deb().toLocalDate());
        dateFin.setValue(ev.getDate_fin().toLocalDate());
        dateFin.setValue(ev.getDate_fin().toLocalDate());
        tfDescription.setText(ev.getDescription());

        Image im = new Image("file:///" + uploads + ev.getImage());
        uploadIV.setImage(im);
    }

    private void refreshData() {
        temp.clear();
        eventsList = se.getAll();
        for (Evenement e : eventsList) {
            Image im = new Image("file:///" + uploads + e.getImage());
            ImageView iv = new ImageView(im);
            iv.setFitHeight(112);
            iv.setFitWidth(200);
            Evenement ev = new Evenement(e.getId(), e.getName(), e.getDate_deb(), e.getDate_fin(), e.getDescription(), e.getImage(), iv);
            temp.add(ev);
        }
        eventsTable.setItems(temp);
    }

    private void clear() {
        tfNom.clear();
        tfDescription.clear();
        dateDeb.setValue(null);
        dateFin.setValue(null);
        uploadIV.setImage(null);
    }

    @FXML
    private void resetFields(ActionEvent event) {
        btnaddEvent.setDisable(false);
        btnupdateEvent.setDisable(true);
        btndeleteEvent.setDisable(true);
        clear();
    }

    @FXML
    private void upload(ActionEvent event) throws NoSuchAlgorithmException, IOException {
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
            uploadIV.setImage(new Image(file.toURI().toString()));

            filename = file.getName();
            filepath = file.getAbsolutePath();
//            System.out.println("INITAL filename : " + filename);
//            System.out.println("filepath : " + filepath);

            String extension = Arrays.stream(filename.split("\\.")).reduce((a, b) -> b).orElse(null);

            byte[] bytesOfMessage = filename.getBytes();

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytesOfMessage, 0, filename.length());

            filename = new BigInteger(1, md.digest()).toString(16) + "." + extension;
            fn = filename;

            FileChannel source = new FileInputStream(filepath).getChannel();
            FileChannel dest = new FileOutputStream(uploads + filename).getChannel();
            dest.transferFrom(source, 0, source.size());
//            System.out.println("HASHED filename : " + filename);

        } else {
            System.out.println("Fichier invalide!");
        }
    }

}
