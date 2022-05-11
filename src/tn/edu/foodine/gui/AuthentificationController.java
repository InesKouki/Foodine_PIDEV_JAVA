/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.edu.foodine.entities.Client;
import tn.edu.foodine.entities.User;
import tn.edu.foodine.services.ServiceUtilisateur;
import tn.edu.foodine.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AuthentificationController implements Initializable {

    public static int idplayer;
    public static String role;
    public static int etat;
    Connection cnx = DataSource.getInstance().getCnx();
    public User connectedUser;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfPas;
    @FXML
    private ImageView imgShow;
    @FXML
    private ImageView imgHide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfPas.setVisible(false);
        imgHide.setVisible(false);
    }

    @FXML
    private void Authentification(ActionEvent event) throws SQLException, IOException {

        if (tfUsername.getText().isEmpty() || tfPassword.getText().isEmpty()) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Authentification")
                    .text("champs vides")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //System.out.println("Supp");
                        }
                    });
            notificationBuilder.showError();

        } else {
            ServiceUtilisateur su = new ServiceUtilisateur();

            if (su.authentifier(tfUsername.getText(), tfPassword.getText()) == 1) {
                PreparedStatement statement;
                try {
                    statement = cnx.prepareStatement("SELECT * FROM user WHERE username=?");
                    statement.setString(1, tfUsername.getText());

                    ResultSet rs = statement.executeQuery();

                    if (rs.next()) {
                        System.setProperty("id", Integer.toString(rs.getInt(1)));
                        role = rs.getString("roles");
                        etat = rs.getInt("etat");
                        connectedUser = su.find(rs.getInt(1));
                    }
                    System.out.println(System.setProperty("id", Integer.toString(rs.getInt(1))));
                    System.out.println(role);

                } catch (SQLException ex) {
                    ex.getMessage();
                }
                if (etat == 1) {

                    Notifications notificationBuilder = Notifications.create()
                            .title("Authentification")
                            .text("Bienvenue")
                            .graphic(null)
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    //System.out.println("Supp");
                                }
                            });
                    notificationBuilder.showConfirm();

                    if (role.contains("[\"ROLE_ADMIN\"]")) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_view.fxml"));
                        Parent root = loader.load();
                        tfUsername.getScene().setRoot(root);
                        backMainController ac = loader.getController();
                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("front_main_view.fxml"));
                        Parent root = loader.load();
                        tfUsername.getScene().setRoot(root);
                        FrontMainController ac = loader.getController();
                    }

                } else {
                    Notifications notificationBuilder = Notifications.create()
                            .title("Authentification")
                            .text("Vous etes bloqué")
                            .graphic(null)
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    //System.out.println("Supp");
                                }
                            });
                    notificationBuilder.showWarning();
                }
            } else {
                Notifications notificationBuilder = Notifications.create()
                        .title("Authentification")
                        .text("Verifier vos coordonées")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                //System.out.println("Supp");
                            }
                        });
                notificationBuilder.showError();
            }
        }
    }

    @FXML
    private void OubliPass(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPassword.fxml"));
        Parent root = loader.load();
        tfUsername.getScene().setRoot(root);
        ForgetPasswordController ac = loader.getController();
    }

    @FXML
    private void AfficherInscription(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
        Parent root = loader.load();
        tfUsername.getScene().setRoot(root);
        ConnexionController ac = loader.getController();
    }

    @FXML
    private void ShowPass(MouseEvent event) {
        String s = tfPassword.getText();
        tfPas.setVisible(true);
        tfPassword.setVisible(false);
        imgHide.setVisible(true);
        imgShow.setVisible(false);
        tfPas.setText(s);
    }

    @FXML
    private void hidePass(MouseEvent event) {
        tfPas.setVisible(false);
        imgShow.setVisible(true);
        imgHide.setVisible(false);
        tfPassword.setVisible(true);
    }

}
