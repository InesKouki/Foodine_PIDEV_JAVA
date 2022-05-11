/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tn.edu.foodine.entities.Categorie;
import tn.edu.foodine.entities.Produit;
import tn.edu.foodine.services.ProduitCRUD;
import tn.edu.foodine.services.CategorieCRUD;
import tn.edu.foodine.utils.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Samar
 */
public class AddProduitController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfQt;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<Produit> tab;
    @FXML
    private TableColumn<Produit, String> nomProdTab;
    @FXML
    private TableColumn<Produit, String> ImageTab;
    @FXML
    private TableColumn<Produit, Double> PrixTab;
    @FXML
    private TableColumn<Produit, Integer> QtTab;
    @FXML
    private TableColumn<Produit, String> CatTab;
    @FXML
    private Button modifier;
    @FXML
    private ComboBox<Categorie> listeCategorie;
    @FXML
    private Button supprimer;

    ObservableList<Produit> dataList;
    ProduitCRUD pcr = new ProduitCRUD();
    CategorieCRUD sc = new CategorieCRUD();
    public int ID;

    String filepath = null, filename = null, fn = null;
    String uploads = "C:/Users/azizm/Desktop/SEM2/PIDEV/Foodine_PIDEV/public/uploads/";

    FileChooser fc = new FileChooser();

    ResultSet rs = null;
    @FXML
    private TextField textrechercher;
    @FXML
    private Pane backgroundPane;
    @FXML
    private Button btnUpload;
    @FXML
    private ImageView uploadIv;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
//    ProduitCRUD UP = new ProduitCRUD();

    ObservableList<Produit> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setNumericConstraint();
        showProduit();
        SetCombo();
    }

    @FXML
    private void AjouterProduit(ActionEvent event) throws SQLException, Exception {
        try {
            if (tfNom.getText().isEmpty()) {
                Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez donner le nom").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
                notificationBuilder.showError();
                // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Produit!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Un nom svp!");
//                a.showAndWait();

            } else if (tfprix.getText().isEmpty()) {
                Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez donner le prix").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
                notificationBuilder.showError();
                // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Produit!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Un prix svp!");
//                a.showAndWait();

            } else if (tfQt.getText().isEmpty()) {
                Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez donner la quantité").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
                notificationBuilder.showError();
                // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Produit!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Une quantité svp!");
//                a.showAndWait();

            } else if (filename == null) {
                Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez ajouter une image").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
                notificationBuilder.showError();
                // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Produit!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Une Image svp!");
//                a.showAndWait();

            } else {

                //save Res in DATA BASE            
                Categorie cat = listeCategorie.getValue();

                String nom = tfNom.getText();
                Double prix = Double.parseDouble(tfprix.getText());
                int quantite = Integer.parseInt(tfQt.getText());

                Produit res = new Produit(nom, quantite, prix, cat, filename);
                pcr.addProduit(res);

                // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Produit!");
//                a.setHeaderText(null);
//                a.setContentText(" Produit Ajoutée!");
//                a.showAndWait();
                Notifications notificationBuilder = Notifications.create().title("Succés").text("Produit ajouté avec succés").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
                notificationBuilder.showConfirm();

            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }

        showProduit();
    }

    @FXML
    private void ModifierProduit(ActionEvent event) throws SQLException, ClassNotFoundException {
        Categorie cat = listeCategorie.getValue();
        String nom = tfNom.getText();
        int quantite = Integer.parseInt(tfQt.getText());
        Double prix = Double.parseDouble(tfprix.getText());
        Produit p = new Produit(ID, nom, quantite, prix, cat, fn);

        if (tfNom.getText().isEmpty()) {
            Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez donner le nom").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
            notificationBuilder.showError();
            // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Produit!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Un nom svp!");
//                a.showAndWait();

        } else if (tfprix.getText().isEmpty()) {
            Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez donner le prix").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
            notificationBuilder.showError();
            // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Produit!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Un prix svp!");
//                a.showAndWait();

        } else if (tfQt.getText().isEmpty()) {
            Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez donner la quantité").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
            notificationBuilder.showError();
            // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Produit!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Une quantité svp!");
//                a.showAndWait();

        } else if (fn == null) {
            Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez ajouter une image").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
            notificationBuilder.showError();
            // Alert 
//                Alert a = new Alert(Alert.AlertType.WARNING);
//                a.setTitle(" Produit!");
//                a.setHeaderText(null);
//                a.setContentText("Donner Une Image svp!");
//                a.showAndWait();

        } else {
            pcr.modifierProduit(p);
//            Alert a = new Alert(Alert.AlertType.WARNING);
//            a.setTitle(" Produit!");
//            a.setHeaderText(null);
//            a.setContentText("Produit Modifié!");
//            a.showAndWait();

            Notifications notificationBuilder = Notifications.create().title("Succés").text("Produit modifié avec succés").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
            notificationBuilder.showConfirm();
            showProduit();
        }
    }

    @FXML
    private void SupprimerProduit(ActionEvent event) throws SQLException, ClassNotFoundException {
        Produit selected = tab.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Voulez-Vous Supprimer cet Produit?");
            alert.setContentText("Supprimer?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    pcr.supprimerProduit(ID);
                    Notifications notificationBuilder = Notifications.create().title("Succés").text("Produit supprimé avec succés").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
                    notificationBuilder.showConfirm();
                } else if (type == noButton) {
                    showProduit();
                } else {
                    showProduit();
                }
            });
        } else {
            Notifications notificationBuilder = Notifications.create().title("Erreur").text("Veuillez séléctionner un produit").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
            notificationBuilder.showError();
            // Alert 
//            Alert a = new Alert(Alert.AlertType.WARNING);
//            a.setTitle(" Produit!");
//            a.setHeaderText(null);
//            a.setContentText("Selectionner Produit!");
//            a.showAndWait();
        }
        showProduit();
    }

    public void showProduit() {

        nomProdTab.setCellValueFactory(new PropertyValueFactory<>("name"));
        ImageTab.setCellValueFactory(new PropertyValueFactory<>("image"));
        PrixTab.setCellValueFactory(new PropertyValueFactory<>("price"));
        QtTab.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        CatTab.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        list = pcr.getProduit();
        tab.setItems(list);
    }

    @FXML
    private void SetValue(MouseEvent event) throws SQLException, ClassNotFoundException {
        Produit selected = tab.getSelectionModel().getSelectedItem();
        CategorieCRUD tabC = new CategorieCRUD();
        if (selected != null) {
            listeCategorie.setValue(selected.getCategorie());
            tfNom.setText(selected.getName());
            tfprix.setText(String.valueOf(Math.round(selected.getPrice())));
            tfQt.setText(String.valueOf(selected.getQuantite()));
            ID = selected.getId();
            fn = selected.getImage();

            Image im = new Image("file:" + uploads + selected.getImage());
            uploadIv.setImage(im);
        }
    }

    private void SetCombo() {

        CategorieCRUD tabC = new CategorieCRUD();
        List<Categorie> tabList = tabC.getCategorie();
        ArrayList<Categorie> cats = new ArrayList<>();
        for (Categorie c : tabList) {
            Categorie cat = new Categorie();
            cat.setId(c.getId());
            cat.setName(c.getName());
            cats.add(cat);
        }

        ObservableList<Categorie> choices = FXCollections.observableArrayList(cats);
        listeCategorie.setItems(choices);
    }

    private void setNumericConstraint() {
        tfprix.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfprix.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        tfQt.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfQt.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
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
    private void search(KeyEvent event) {
        FilteredList<Produit> filter = new FilteredList<>(list, ev -> true);

        textrechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(t -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(t.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(t.getPrice()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Produit> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sort);
    }

    @FXML
    private void exportExcel(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        Connection cn = DataSource.getInstance().getCnx();
        String query = "Select * from product";
        PreparedStatement pst = cn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Détails ProduitPlat");
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Nom");
        header.createCell(2).setCellValue("Quantité");
        header.createCell(3).setCellValue("Prix");

        int index = 1;
        while (rs.next()) {
            HSSFRow row = sheet.createRow(index);

            row.createCell(0).setCellValue(rs.getInt("id"));
            row.createCell(1).setCellValue(rs.getString("name"));
            row.createCell(2).setCellValue(rs.getInt("quantitie"));
            row.createCell(3).setCellValue(rs.getDouble("price"));
            index++;
        }

        FileOutputStream file = new FileOutputStream("C:\\Users\\azizm\\desktop\\ProduitPlat.xls");
        wb.write(file);
        file.close();
        JOptionPane.showMessageDialog(null, "Exportation 'EXCEL' effectuée avec succés");

        pst.close();
        rs.close();

    }

}
