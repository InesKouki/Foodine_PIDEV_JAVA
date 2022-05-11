/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import static com.itextpdf.text.pdf.PdfName.URI;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.edu.foodine.entities.Planning;
import tn.edu.foodine.services.ServicePlanning;
import com.itextpdf.text.pdf.PdfWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import static java.util.Comparator.reverseOrder;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AjouterPlanningController implements Initializable {

    int pId = 0;
    int pid=0;
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
    ObservableList<Planning> list;
    ServicePlanning sp = new ServicePlanning();
    private Button closeButton;
    @FXML
    private Button reset;
    @FXML
    private Button pdf;
    @FXML
    private ListView<Planning> ListView;
    ObservableList<Planning> listView1;
    @FXML
    private ComboBox<String> cbox;
    private Button closeButton1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPlanning();
        ObservableList<String> listcb= FXCollections.observableArrayList("Trie par","Par Nom ↑", "Par Nom ↓");
        cbox.setItems(listcb);  
        cbox.getSelectionModel().selectFirst();

    }
    @FXML
    private void select(ActionEvent event) {
        String s= cbox.getSelectionModel().getSelectedItem();
        listView1=sp.getAll();
        ListView.setItems(listView1);
        if (s.equals("Par Nom ↑")){
            SortedList<Planning> sortedList = new SortedList(ListView.getItems().sorted());
            ListView.setItems(sortedList);
       }
          if (s.equals("Par Nom ↓")){
               SortedList<Planning> sortedList = new SortedList(ListView.getItems().sorted());
            List<Planning> listpp = new LinkedList<>(Arrays.asList());
            int i = sortedList.size();
            while( i > 0 ){
            listpp.add(sortedList.get(i-1));
            i-- ;
            }
            ListView.setItems(FXCollections.observableList(listpp));
       }
    }
    

        /*hyperlink.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                getHostServices().showDocument("https://eclipse.org");
            }
        });*/
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
        /*list = sp.getAll();
        // tvPlanning.setItems(list);
        for (int i=0; i<list.size();i++) {
                String nom=list.get(i).getNom();
                Date date=list.get(i).getDate();
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
                String strDate = dateFormat.format(date);
                String list1 = nom + "  \""+strDate +"\"";
                
        
        }*/
        listView1=sp.getAll();
        ListView.setItems(listView1);
    }

    public void showPlanning() {
        /*colId.setCellValueFactory(new PropertyValueFactory<Planning, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Planning, String>("nom"));
        colDate.setCellValueFactory(new PropertyValueFactory<Planning, Date>("date"));
        list = sp.getAll();
        tvPlanning.setItems(list);*/
        listView1=sp.getAll();
        ListView.setItems(listView1);
        
        
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
            Planning p = new Planning(pId, Date.valueOf(dtDate.getValue()),tfNom.getText());
            sp.modifier(p);
            refresh();
        }
    }
/*
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
*/
    @FXML
    private void supprimerPlanning(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
        alert.getDialogPane().setContentText("Voulez vous vraiment supprimer cette événement?");
        alert.getDialogPane().setHeaderText("Suppression");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) { 
        /*Planning p = tvPlanning.getSelectionModel().getSelectedItem();
        sp.supprimer(p.getId());
        refresh();*/
        Planning p2 = ListView.getSelectionModel().getSelectedItem();
        sp.supprimer(p2.getId());
        refresh();}
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

    @FXML
    private void Pdf(ActionEvent event) {
        Document doc = new Document();
        String FILE_NAME = "E:\\java_pdf\\chillyfacts.pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\PC\\Desktop\\Mobile\\Foodine_PIDEV_Desktop\\src\\Planning.pdf"));
            doc.open();
            Paragraph p = new Paragraph();
            p.add("Foodine");
            p.setAlignment(Element.ALIGN_CENTER);
            
            
            doc.add(p);
            PdfPTable table = new PdfPTable(2); // 2 columns.
            table.setSpacingBefore(20f);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Nom du Planning"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Date Planning"));
          
            cell1.setBackgroundColor(BaseColor.GRAY);
            cell2.setBackgroundColor(BaseColor.GRAY);
            cell1.setPadding(5);
            table.addCell(cell1);
            table.addCell(cell2);
            list = sp.getAll();
            for (int i=0; i<list.size();i++) {
                String nom=list.get(i).getNom();
                Date date=list.get(i).getDate();
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
                String strDate = dateFormat.format(date); 
                table.addCell(nom);
                table.addCell(strDate);
            }
            doc.add(table);
            
            Desktop.getDesktop().open(new File("C:\\Users\\PC\\Desktop\\Mobile\\Foodine_PIDEV_Desktop\\src\\Planning.pdf"));
            
            doc.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjouterPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(AjouterPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void rowClicked1(javafx.scene.input.MouseEvent event) {
        Planning p2 = ListView.getSelectionModel().getSelectedItem();
        System.out.println(p2.getId());
        pId = p2.getId();
        tfNom.setText(p2.getNom());
        dtDate.setValue(p2.getDate().toLocalDate());
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn.setDisable(true);
    }

    

    @FXML
    private void Pdf(javafx.scene.input.MouseEvent event) {
    }
    Stage front = new Stage();
    private void front(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/FrontRecette.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        
        front.setScene(scene);
        front.setMaximized(true);
        front.show();
        Stage stage = (Stage) closeButton1.getScene().getWindow();
        stage.close();
    }

    
}
