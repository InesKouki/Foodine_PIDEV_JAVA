/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.services.ServiceReclamation;
import tn.edu.esprit.services.ServiceUtilisateur;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         debloquer.setVisible(false);
        bloquer.setVisible(false);
        showList();
    } 
    
   

    @FXML
    private void AfficherAccueil(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            tableList.getScene().setRoot(root);
           DashboardController ac = loader.getController();
    }

    @FXML
    private void AfficherReclamation(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
            Parent root = loader.load();
            tableList.getScene().setRoot(root);
           AfficherReclamationController ac = loader.getController();
    }

    @FXML
    private void AfficherUtilisateur(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("AfficherUtilisateur.fxml"));
            Parent root = loader.load();
            tableList.getScene().setRoot(root);
            AfficherUtilisateurController ac = loader.getController();
        
        
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
    private void logout(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            tableList.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }

    @FXML
    private void supprimerUtilisateur(ActionEvent event) {
         delete();
        tableList.getItems().removeAll(tableList.getSelectionModel().getSelectedItem());
        System.out.println(tableList);
        tableList.refresh();
        Notifications notificationBuilder = Notifications.create()
                    .title("Suppression effectuée")
                    .text("Utilisateur supprimé supprimée")
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
       Notifications notificationBuilder = Notifications.create()
                    .title("Suppression")
                    .text("Utilisateur supprimé")
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
   
    private void supprimer(ActionEvent event) {
         delete();
        tableList.getItems().removeAll(tableList.getSelectionModel().getSelectedItem());
        
        tableList.refresh();
    }
    
   

    @FXML
    private void bloquer(ActionEvent event) throws IOException {
         ServiceUtilisateur SV = new ServiceUtilisateur();
         System.out.println(tableList.getSelectionModel().getSelectedItem().getId());
       SV.bloquer(tableList.getSelectionModel().getSelectedItem().getId());
      
       FXMLLoader  loader = new FXMLLoader(getClass().getResource("AfficherUtilisateur.fxml"));
            Parent root = loader.load();
            tableList.getScene().setRoot(root);
            AfficherUtilisateurController ac = loader.getController();
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
            
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("AfficherUtilisateur.fxml"));
            Parent root = loader.load();
            tableList.getScene().setRoot(root);
            AfficherUtilisateurController ac = loader.getController();
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

    
}
