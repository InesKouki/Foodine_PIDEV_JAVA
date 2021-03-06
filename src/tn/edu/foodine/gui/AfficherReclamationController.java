/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import tn.edu.foodine.entities.Reclamation;
import tn.edu.foodine.entities.Reponse;
import tn.edu.foodine.entities.User;
import tn.edu.foodine.services.ServiceReclamation;



/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficherReclamationController implements Initializable {
private AnchorPane myAnchorePane;
    @FXML
    private TableView<Reclamation> listRec;
    private TableColumn<Reclamation, String> colNom;
    @FXML
    private TableColumn<Reclamation, String> colType;
    @FXML
    private TableColumn<Reclamation, Date> colDate;
    @FXML
    private TableColumn<Reclamation, String> colDescr;
    @FXML
    private TableColumn<Reclamation, Integer> colEtat;

    ServiceReclamation sv = new ServiceReclamation();
    ObservableList<Reclamation> list ;
    @FXML
    private Button supprimer;
    @FXML
    private Button traiter;
    private ChoiceBox<String> choicebox;
    @FXML
    private AnchorPane myAnchorPane;
    @FXML
    private TextArea tfText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showList();
      supprimer.setVisible(false);
        traiter.setVisible(false);
        tfText.setVisible(false);
          System.out.println(System.getProperty("id"));
          ObservableList<String> list = FXCollections.observableArrayList("Trier par date ","Trier par type");
//          choicebox.setItems(list);
        
    }    

      public void showList(){
           // colNom.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("u.nom"));
            colType.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("type"));
            colDescr.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
            colEtat.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("etat"));
            colDate.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("created_at"));
             list=sv.getAll();
             System.out.println(list);
             listRec.setItems(list);
            
    }
      
    
      private void refresh() {
           list.clear();
           list=sv.getAll();
             System.out.println(list);
             listRec.setItems(list);
        
    }
         public void clear() {

        colNom.setText(null);
        colType.setText(null);
        colDescr.setText(null);
        colEtat.setText(null);
        colDate.setText(null);

    }
      
      public void delete()
    {
        ServiceReclamation SV = new ServiceReclamation();
       SV.supprimer(listRec.getSelectionModel().getSelectedItem().getId());
        System.out.println(listRec.getSelectionModel().getSelectedItem().getId());
    }
   
    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        
        Stage stage =(Stage) myAnchorPane.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("Voulez vous vraiment supprimer cette reclamation?");
        alert.getDialogPane().setHeaderText("Suppression");
        Optional<ButtonType> result=alert.showAndWait();
        if (result.get()==ButtonType.OK)
        {
            delete();
        listRec.getItems().removeAll(listRec.getSelectionModel().getSelectedItem());
        System.out.println(listRec);
       refresh();
     
            Notifications notificationBuilder = Notifications.create()
                    .title("Suppression effectu??e")
                    .text("Reclamation supprim??e")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showConfirm();
        }else if (result.get()==ButtonType.CANCEL){
            //nothing
        }
        
        

         
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        
         ServiceReclamation SV = new ServiceReclamation();
       SV.traiter(listRec.getSelectionModel().getSelectedItem().getId());
       refresh();
          /* Reponse r = new Reponse(listRec.getSelectionModel().getSelectedItem().getId(),tfText.getText());
           SV.repondre(r);
           System.out.println(r);*/
            Notifications notificationBuilder = Notifications.create()
                    .title("Traiter Reclamation")
                    .text("Reclamation trait?? avec succes")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showConfirm();
    }

    @FXML
    private void click(MouseEvent event) {
        if (event.getClickCount() == 1) {
            if (listRec.getSelectionModel().getSelectedItem().getEtat()==0) {
                traiter.setVisible(true);
                tfText.setVisible(true);
                supprimer.setVisible(false);
            } else {
                tfText.setVisible(false);
                traiter.setVisible(false);
                supprimer.setVisible(true);
            }
        }
    }

    
}
