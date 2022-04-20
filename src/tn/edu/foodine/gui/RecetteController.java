/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import tn.edu.foodine.entities.Planning;
import tn.edu.foodine.entities.Recette;
import tn.edu.foodine.services.ServicePlanning;
import tn.edu.foodine.services.ServiceRecette;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RecetteController implements Initializable {
    String up = "C:\\Users\\PC\\Desktop\\Mobile\\Foodine_PIDEV_Desktop\\src\\img\\";
    int rId = 0;

    @FXML
    private Button importerBtn;
    @FXML
    private ImageView image;
    @FXML
    private TextField Nom_Recette;
    @FXML
    private TextArea Desc_Recette;
    @FXML
    private TextField ing_Recette;
    private String path = "", imgname = "", fn="";

    @FXML
    private ComboBox<Planning> Planning;
    ObservableList<Recette> list;
    ObservableList<Recette> temp = FXCollections.observableArrayList();
    
    ServiceRecette sr = new ServiceRecette();
    @FXML
    private TableView<Recette> tvRecette;
    @FXML
    private TableColumn<Recette, Integer> Col_id;
    @FXML
    private TableColumn<Recette, String> Col_Nom;
    @FXML
    private TableColumn<Recette, String> Col_Desc;
    @FXML
    private TableColumn<Recette, String> Col_ing;
    @FXML
    private TableColumn<Recette, String> Col_img;
    @FXML
    private TableColumn<Recette, Integer> Col_planning;
    @FXML
    private Button ModifierRecette;
    @FXML
    private Button SupprimerRecette;
    @FXML
    private Button AjouterRecette;
    @FXML
    private Button closeButton;
    @FXML
    private Button reset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showRecette();
        showPlanning();
        // TODO
    }

    public void SetNom(String Nom) {
        Nom_Recette.setText(Nom);
    }


    @FXML
    private void importer_image(ActionEvent event) throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        path = filename;
        imgname = f.getName();
        fn=imgname;
        Image getAbsolutePath = null;

        String dd = "C:\\Users\\PC\\Desktop\\Mobile\\Foodine_PIDEV_Desktop\\src\\img\\" + f.getName();
        File dest = new File(dd);
        this.copyFile(f, dest);

        System.out.println(dd);

        image.setImage(new Image("file:" + dest.getAbsolutePath()));

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

    @FXML
    void AjouterProduit(ActionEvent event) {
        if (Nom_Recette.getText().equals("") || Desc_Recette.getText().equals("") || ing_Recette.getText().equals("") || Planning.getValue()==null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.OK);
            a.showAndWait();
        } else {
            Planning p = Planning.getSelectionModel().getSelectedItem();
            //pps.ajouter(new Produit(Nom_ProduitPlat.getText(),Prix.getText(), Desc_ProduitPlat.getText(),qte.getText()));
            sr.ajouter(new Recette(Nom_Recette.getText(), Desc_Recette.getText(), imgname, ing_Recette.getText(), p));
            // pps.add(new Produit(Nom_ProduitPlat.getText(),Double.parseDouble(Prix.getText()),Integer.parseInt(qte.getText()),Desc_ProduitPlat.getText(),id));
            JOptionPane.showMessageDialog(null, "produit ajouté avec succés");
            refresh();
        }

    }

    private void showPlanning() {
        List<Planning> listC = new ServicePlanning().getAll();

        ArrayList<Planning> libelles = new ArrayList<>();
        for (Planning cat : listC) {
            Planning Ocat = new Planning();
            Ocat.setId(cat.getId());
            Ocat.setId(cat.getId());
            Ocat.setNom(cat.getNom());
            libelles.add(Ocat);
        }
        ObservableList<Planning> choices = FXCollections.observableArrayList(libelles);
        Planning.setItems(choices);
    }

    public void refresh() {
        list = sr.getAll();
        tvRecette.setItems(list);
    }

    public void showRecette() {
        Col_id.setCellValueFactory(new PropertyValueFactory<Recette, Integer>("id"));
        Col_Nom.setCellValueFactory(new PropertyValueFactory<Recette, String>("nom"));
        Col_Desc.setCellValueFactory(new PropertyValueFactory<Recette, String>("description"));
        Col_img.setCellValueFactory(new PropertyValueFactory<Recette, String>("image"));
        Col_ing.setCellValueFactory(new PropertyValueFactory<Recette, String>("ingredient"));
        Col_planning.setCellValueFactory(new PropertyValueFactory<Recette, Integer>("planning"));
        list = sr.getAll();
        tvRecette.setItems(list);
        System.out.println(list);

    }

    @FXML
    private void SupprimerRecette(ActionEvent event) {
        Recette p = tvRecette.getSelectionModel().getSelectedItem();
        sr.supprimer(p.getId());
        refresh();
    }

    @FXML
    private void rowClicked(javafx.scene.input.MouseEvent event) {
        ModifierRecette.setDisable(false);
        SupprimerRecette.setDisable(false);
        AjouterRecette.setDisable(true);
        Recette r = tvRecette.getSelectionModel().getSelectedItem();
        rId = r.getId();
        fn = r.getImage();
        Nom_Recette.setText(r.getNom());
        Desc_Recette.setText(r.getDescription());
        ing_Recette.setText(r.getIngredient());
        Planning.setValue(r.getPlanning());
        Image im = new Image("file:///"+up +r.getImage());
        image.setImage(im);
        
    }
    private void refreshData() {
        temp.clear();
        list = sr.getAll();
        /*for (Recette e : list) {
            Image im = new Image("file:///"+ up  + e.getImage());
            ImageView iv = new ImageView(im);
            iv.setFitHeight(112);
            iv.setFitWidth(200);
            Recette ev = new Recette(e.getId(), e.getNom(), e.getDescription(),e.getImage(), e.getIngredient(), e.getPlanning(), iv);
            temp.add(ev);
        }
*/
        tvRecette.setItems(list);
    }
    private void clear() {
        Nom_Recette.clear();
        Desc_Recette.clear();
        ing_Recette.clear();
        Planning.setValue(null);
        image.setImage(null);
    }

    @FXML
    private void modRecette(ActionEvent event) {
        if (Nom_Recette.getText().equals("") || Desc_Recette.getText().equals("") || ing_Recette.getText().equals("") || Planning.getValue()==null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.OK);
            a.showAndWait();
        } else {
            Recette p = new Recette(rId, Nom_Recette.getText(), Desc_Recette.getText(), fn, ing_Recette.getText(), Planning.getValue());
            sr.modifier(p);
            refresh();
        }
    }

    private Parent root;
    Stage planning = new Stage();
    
    @FXML
    private void planning(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPlanning.fxml"));
        root = loader.load();
        
        Scene scene = new Scene(root);
        
        planning.setScene(scene);
        planning.setMaximized(true);
        planning.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
       
    }

    @FXML
    private void reset(ActionEvent event) {
        ModifierRecette.setDisable(true);
        SupprimerRecette.setDisable(true);
        AjouterRecette.setDisable(false);
        clear();
    }
    

}
