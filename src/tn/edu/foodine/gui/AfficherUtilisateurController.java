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
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.edu.foodine.entities.Client;
import tn.edu.foodine.entities.User;
import tn.edu.foodine.services.ServiceReclamation;
import tn.edu.foodine.services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficherUtilisateurController implements Initializable {
   
    @FXML
    private TableColumn<User, String> colNom;
    @FXML
    private TableColumn<User, String> colPrenom;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colEmail;
    private TableColumn<User, String> colRole;
    @FXML
    private TableColumn<User, Integer> colEtat;

    ObservableList<User> list ;
 ServiceUtilisateur su = new ServiceUtilisateur();
    @FXML
    private TableView<User> tableList;
    @FXML
    private TableColumn<User, Date> colDate;
    @FXML
    private Button bloquer;
    @FXML
    private Button debloquer;
    @FXML
    private TextField Recherche;
    @FXML
    private AnchorPane myAnchorPane;
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem trierNom;
    @FXML
    private MenuItem tierDate;
    @FXML
    private MenuItem trierEmail;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        debloquer.setVisible(false);
        bloquer.setVisible(false);
        showList();
        
    } 

       public void showList(){
            colNom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
            colUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
            colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
           // colRole.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));
            colEtat.setCellValueFactory(new PropertyValueFactory<User, Integer>("etat"));
            colDate.setCellValueFactory(new PropertyValueFactory<User, Date>("created_at"));
             list=su.getAll();
             tableList.setItems(list);
            
    }

   

    @FXML
    private void supprimerUtilisateur(ActionEvent event) {
        Stage stage =(Stage) myAnchorPane.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("Voulez vous vraiment supprimer cet utilisateur?");
        alert.getDialogPane().setHeaderText("Suppression");
        Optional<ButtonType> result=alert.showAndWait();
        if (result.get()==ButtonType.OK){
             delete();
        tableList.getItems().removeAll(tableList.getSelectionModel().getSelectedItem());
        System.out.println(tableList);
        tableList.refresh();
        Notifications notificationBuilder = Notifications.create()
                    .title("Suppression effectuée")
                    .text("Utilisateur supprimé ")
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
        
    }
    
    
      private void refresh() {
           list.clear();
            showList();
            debloquer.setVisible(false);
            bloquer.setVisible(false);
        
    }
         public void clear() {

        colNom.setText(null);
        colPrenom.setText(null);
        colUsername.setText(null);
         colEmail.setText(null);
        colEtat.setText(null);
        colDate.setText(null);
          colRole.setText(null);

    }
      
      public void delete()
    {
        ServiceUtilisateur SV = new ServiceUtilisateur();
       SV.supprimer(tableList.getSelectionModel().getSelectedItem().getId());
            }

    @FXML
    private void bloquer(ActionEvent event) throws IOException {
         ServiceUtilisateur SV = new ServiceUtilisateur();
         System.out.println(tableList.getSelectionModel().getSelectedItem().getId());
       SV.bloquer(tableList.getSelectionModel().getSelectedItem().getId());
            refresh();
      
            Notifications notificationBuilder = Notifications.create()
                    .title("Bloquage")
                    .text("Utilisateur bloqué")
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
    private void debloquer(ActionEvent event) throws IOException {
         ServiceUtilisateur SV = new ServiceUtilisateur();
          System.out.println(tableList.getSelectionModel().getSelectedItem().getId());
            SV.debloquer(tableList.getSelectionModel().getSelectedItem().getId());
            
       refresh();
            Notifications notificationBuilder = Notifications.create()
                    .title("Débloquage")
                    .text("Utilisateur debloqué")
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
    private void clickItem(javafx.scene.input.MouseEvent event) {
         if (event.getClickCount() == 1) {
            if (tableList.getSelectionModel().getSelectedItem().getEtat()==0) {
                debloquer.setVisible(true);
                bloquer.setVisible(false);
            } else {
                debloquer.setVisible(false);
                bloquer.setVisible(true);
            }
        }
    }

    @FXML
    private void Recherche(KeyEvent event) {
        
        User b= new Client();
        ObservableList<User>filter= su.chercherUser(Recherche.getText());
         populateTable(filter);
    }
    private void populateTable(ObservableList<User> branlist){
       tableList.setItems(branlist);
   
       }

    @FXML
    private void Refresh(javafx.scene.input.MouseEvent event) {
         debloquer.setVisible(false);
        bloquer.setVisible(false);
        showList();
        Recherche.setText(null);
    }

    @FXML
    private void Trier(ActionEvent event) {
        
        
    }

    @FXML
    private void trierNom(ActionEvent event) {
        colNom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
            colUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
            colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
           // colRole.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));
            colEtat.setCellValueFactory(new PropertyValueFactory<User, Integer>("etat"));
            colDate.setCellValueFactory(new PropertyValueFactory<User, Date>("created_at"));
             list=su.getAllTriNom();
             tableList.setItems(list);
    }

    @FXML
    private void trierDate(ActionEvent event) {
        colNom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
            colUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
            colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
           // colRole.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));
            colEtat.setCellValueFactory(new PropertyValueFactory<User, Integer>("etat"));
            colDate.setCellValueFactory(new PropertyValueFactory<User, Date>("created_at"));
             list=su.getAllTridate();
             tableList.setItems(list);
    }

    @FXML
    private void trierEmail(ActionEvent event) {
        colNom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
            colUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
            colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
           // colRole.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));
            colEtat.setCellValueFactory(new PropertyValueFactory<User, Integer>("etat"));
            colDate.setCellValueFactory(new PropertyValueFactory<User, Date>("created_at"));
             list=su.getAllTriEmail();
             tableList.setItems(list);
    }

    
}
