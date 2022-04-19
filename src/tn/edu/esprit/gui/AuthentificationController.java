/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tn.edu.esprit.entities.Client;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.services.ServiceUtilisateur;
import tn.edu.esprit.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AuthentificationController implements Initializable {
public static int idplayer;
public static String role;
Connection cnx = DataSource.getInstance().getCnx();
    public static User connectedUser;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Authentification(ActionEvent event) throws SQLException, IOException {
        
    if(tfUsername.getText().isEmpty() || tfPassword.getText().isEmpty()){
             Alert a = new Alert(Alert.AlertType.ERROR,"Champs vides !",ButtonType.OK);
               a.showAndWait();
   
        }
            else{
            ServiceUtilisateur su = new ServiceUtilisateur();

        if (su.authentifier(tfUsername.getText(), tfPassword.getText()) == 1) {
            PreparedStatement statement;
            try {
                statement = cnx.prepareStatement("SELECT * FROM user WHERE username=?");
                statement.setString(1, tfUsername.getText());
                 
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    idplayer = rs.getInt(1);
                    role = rs.getString(6);
                    connectedUser = su.find(rs.getInt(1));
                }
                System.out.println(connectedUser);
                System.out.println(role);

            } catch (SQLException ex) {
                ex.getMessage();
            }
 Alert aa = new Alert(Alert.AlertType.INFORMATION,"Succes !",ButtonType.OK);
                    aa.showAndWait();
                    
               if(role.contains("[\"ROLE_ADMIN\"]")){
                FXMLLoader  loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                Parent root = loader.load();
                tfUsername.getScene().setRoot(root);
                AuthentificationController ac = loader.getController();
               }
               else
               {
                    FXMLLoader  loader = new FXMLLoader(getClass().getResource("ProfileClient.fxml"));
                Parent root = loader.load();
                tfUsername.getScene().setRoot(root);
                AuthentificationController ac = loader.getController();
               }           
    }
        else{
            Alert a = new Alert(Alert.AlertType.ERROR,"Vérifier vos coodonnées !",ButtonType.OK);
               a.showAndWait();
        }
    }
    }

    @FXML
    private void OubliPass(ActionEvent event) {
    }

    @FXML
    private void AfficherInscription(ActionEvent event) throws IOException {
         FXMLLoader  loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
            Parent root = loader.load();
            tfUsername.getScene().setRoot(root);
            ConnexionController ac = loader.getController();
    }
    
}