/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.edu.esprit.entities.Reclamation;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> listRec;
    @FXML
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showList();
      supprimer.setVisible(false);
        traiter.setVisible(false);
    }    

    @FXML
    private void AfficherAccueil(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            listRec.getScene().setRoot(root);
           DashboardController ac = loader.getController();
    }

    @FXML
    private void AfficherReclamation(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
            Parent root = loader.load();
            listRec.getScene().setRoot(root);
            AfficherReclamationController ac = loader.getController();
        
    }

    @FXML
    private void AfficherUtilisateur(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("AfficherUtilisateur.fxml"));
            Parent root = loader.load();
            listRec.getScene().setRoot(root);
            AfficherUtilisateurController ac = loader.getController();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            listRec.getScene().setRoot(root);
            AuthentificationController ac = loader.getController();
    }

    
      public void showList(){
            colNom.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("u.nom"));
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
         delete();
        listRec.getItems().removeAll(listRec.getSelectionModel().getSelectedItem());
        System.out.println(listRec);
       FXMLLoader  loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
            Parent root = loader.load();
            listRec.getScene().setRoot(root);
            AfficherReclamationController ac = loader.getController();
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        
         ServiceReclamation SV = new ServiceReclamation();
       SV.traiter(listRec.getSelectionModel().getSelectedItem().getId());
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
            Parent root = loader.load();
            listRec.getScene().setRoot(root);
            AfficherReclamationController ac = loader.getController();
    }

    @FXML
    private void click(MouseEvent event) {
        if (event.getClickCount() == 1) {
            if (listRec.getSelectionModel().getSelectedItem().getEtat()==0) {
                traiter.setVisible(true);
                supprimer.setVisible(false);
            } else {
                traiter.setVisible(false);
                supprimer.setVisible(true);
            }
        }
    }
    
}
