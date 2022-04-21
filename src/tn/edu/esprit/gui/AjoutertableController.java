/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import tn.edu.esprit.entities.Reservation;
import tn.edu.esprit.entities.Table;
import tn.edu.esprit.services.servicetable;
import tn.edu.esprit.utils.DataSource;
import static tn.edu.esprit.utils.DataSource.getInstance;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutertableController implements Initializable {

    ObservableList<String> mm = FXCollections.observableArrayList("disponible", "non disponible");

    @FXML
    private TextField tfnumtable;
    @FXML
    private TextField tfnbplace;
   
    @FXML
    private TableView<Table> tableauaffichage;
    @FXML
    private TableColumn<Table, Integer> num;
    @FXML
    private TableColumn<Table, Integer> nb;
    @FXML
    private TableColumn<Table, String> img;
    @FXML
    private TableColumn<Table, String> etat;
    @FXML
    private ComboBox comboboxetat;
    private TextField tfpathimage;
    @FXML
    private Button closeButton;
    @FXML
    private Button btnimageupload;
    @FXML
    private ImageView uploadimagev;

    /**
     * Initializes the controller class.
     */
    String uploads = "C:\\Users\\ASUS\\Documents\\GitHub\\Foodine_PIDEV_JAVA\\src\\img\\";
    String path, fn, imgname;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showtable();
        comboboxetat.setValue("disponible");
        comboboxetat.setItems(mm);

    }

    public void ajouter(Table t) {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String req = "INSERT INTO `table` (`nbplacetable`, `numerotable`,`etat`,`imagetable`) VALUES ('" + t.getNbplacetable() + "','" + t.getNumerotable() + "','" + t.getEtat() + "','" + t.getImagetable() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Table created !");
            showtable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void ajoutertable(ActionEvent event) throws IOException {
        int numtable = Integer.parseInt(tfnumtable.getText());
        int nbplace = Integer.parseInt(tfnbplace.getText());
       
        String etat = comboboxetat.getValue().toString();

        if (this.tfnbplace.toString().isEmpty()  || comboboxetat.getItems().isEmpty()|| imgname.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Remplir les Champs", ButtonType.OK);
            a.showAndWait();
        } else if (nbplace == 0 || numtable == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "nombre de place et numero table doit etre different de zero", ButtonType.OK);
            a.showAndWait();
        } else {

            /*try {
              Table t = new Table(numtable, nbplace, image, etat);
        servicetable st= new servicetable();
        st.ajouter(t);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/affichertable.fxml"));
            Parent root =loader.load();
            AffichertableController aftc=loader.getController();
            aftc.tfnumtableafficher(""+t.getNumerotable());
            aftc.tfnbplaceafficher(""+t.getNbplacetable());
            aftc.tfimageafficher(""+t.getImagetable());
            aftc.tfetatafficher(""+t.getImagetable());
            
            tfnumtable.getScene().setRoot(root);
            
        } catch (IOException ex) {
           Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                a.showAndWait();
        }*/
            Table t = new Table(numtable, nbplace, imgname, etat);

            ajouter(t);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "table ajouter", ButtonType.OK);
            a.showAndWait();

        }

    }

    public void showtable() {
        servicetable st = new servicetable();
        ObservableList<Table> list = st.getAll();
        num.setCellValueFactory(new PropertyValueFactory<Table, Integer>("numerotable"));
        nb.setCellValueFactory(new PropertyValueFactory<Table, Integer>("nbplacetable"));
        img.setCellValueFactory(new PropertyValueFactory<Table, String>("imagetable"));
        etat.setCellValueFactory(new PropertyValueFactory<Table, String>("etat"));
        tableauaffichage.setItems(list);
    }

    public void modifier() {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String req = "UPDATE `table` SET `nbplacetable` = '" + tfnbplace.getText() + "', `numerotable` = '" + tfnumtable.getText() + "', `etat` = '" + comboboxetat.getValue() + "', `imagetable` = '" + fn + "' WHERE `table`.`numerotable` = " + tfnumtable.getText();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Table updated !");
            showtable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnmodifiertable(ActionEvent event) {
        modifier();
    }

    public void supprimer() {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String req = "DELETE FROM `table` WHERE numerotable = " + tfnumtable.getText();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Table deleted !");
            showtable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnsupprimer(ActionEvent event) {
        supprimer();
    }

    @FXML
    private void btnimage(ActionEvent event) throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        path = filename;
        imgname = f.getName();
        fn = imgname;
        Image getAbsolutePath = null;

        String dd = uploads + f.getName();
        File dest = new File(dd);
        this.copyFile(f, dest);

        System.out.println(dd);

        uploadimagev.setImage(new Image("file:" + dest.getAbsolutePath()));

    }

    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        try (
                FileChannel in = new FileInputStream(sourceFile).getChannel();
                FileChannel out = new FileOutputStream(destFile).getChannel();) {

            out.transferFrom(in, 0, in.size());
        }
    }
    private Parent root;
    Stage recette = new Stage();

    @FXML
    private void passerareservation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterreservation.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);

        recette.setScene(scene);
        recette.setMaximized(true);
        recette.show();

    }

    @FXML
    private void rowclicked(MouseEvent event) {

        Table ev = tableauaffichage.getSelectionModel().getSelectedItem();

        tfnumtable.setText(String.valueOf(ev.getNumerotable()));
        tfnbplace.setText(String.valueOf(ev.getNbplacetable()));
        comboboxetat.setValue(ev.getEtat());
        Image im = new Image("file:" + uploads + ev.getImagetable());
        uploadimagev.setImage(im);
    }

}
