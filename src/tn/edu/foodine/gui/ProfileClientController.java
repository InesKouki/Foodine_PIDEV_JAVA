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
import static java.lang.Integer.parseInt;
import java.math.BigInteger;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;
import tn.edu.foodine.entities.Client;
import tn.edu.foodine.entities.User;
import tn.edu.foodine.services.ServiceUtilisateur;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ProfileClientController implements Initializable {

    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtNom;
    String fn = null;
    FileChooser fc = new FileChooser();
    String filename = null;
    String filepath = null;
    String uploads = "C:/Users/azizm/Desktop/SEM2/PIDEV/Foodine_PIDEV/public/uploads/";

    ServiceUtilisateur u = new ServiceUtilisateur();
    Client us = (Client) u.find(Integer.parseInt(System.getProperty("id")));
    @FXML
    private ImageView image;
    @FXML
    private Button btnphoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
        AuthentificationController ac = loader.getController();
        txtNom.setText(us.getNom());

        txtAdresse.setText(us.getAddress());

        txtPhone.setText(Integer.toString(us.getPhone()));

        txtPrenom.setText(us.getPrenom());
        txtEmail.setText(us.getEmail());
        image.setImage(new Image("file:" + uploads + us.getFile()));

    }

    @FXML
    private void ModifierInformation(ActionEvent event) {

        if (!txtEmail.getText().contains("@") || !txtEmail.getText().contains(".")) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Modification informations")
                    .text("Email invalide")
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
        } else if (!txtPhone.getText().matches("[0-9]+") || txtPhone.getText().length() > 8) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Modification informations")
                    .text("Numero de téléphone invalide")
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
        } else if (txtNom.getText().matches("[0-9]+") || txtPrenom.getText().matches("[0-9]+")) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Modification informations")
                    .text("Nom et prénom doivent contenir des lettres alphabetiques")
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
            User u = new Client(parseInt(System.getProperty("id")), txtNom.getText(), txtPrenom.getText(), txtEmail.getText(), Integer.parseInt(txtPhone.getText()), txtAdresse.getText(), filename);
            su.modifierInfo((Client) u);
            Notifications notificationBuilder = Notifications.create()
                    .title("Modification informations")
                    .text("Profile modifié avec succes")
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

        }
    }

    @FXML
    private void ModifierPassword(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierPassword.fxml"));
        Parent root = loader.load();
        txtNom.getScene().setRoot(root);
        ModifierPasswordController ac = loader.getController();
    }

    @FXML
    private void upload(ActionEvent event) throws IOException, NoSuchAlgorithmException {
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
            image.setImage(new Image(file.toURI().toString()));

            filename = file.getName();
            filepath = file.getAbsolutePath();
//            System.out.println("INITAL filename : " + filename);
//            System.out.println("filepath : " + filepath);

            String extension = Arrays.stream(filename.split("\\.")).reduce((a, b) -> b).orElse(null);

            byte[] bytesOfMessage = filename.getBytes();

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytesOfMessage, 0, filename.length());

            filename = new BigInteger(1, md.digest()).toString(16) + "." + extension;
            fn = filename;

            FileChannel source = new FileInputStream(filepath).getChannel();
            FileChannel dest = new FileOutputStream(uploads + filename).getChannel();
            dest.transferFrom(source, 0, source.size());
//            System.out.println("HASHED filename : " + filename);

        } else {
            System.out.println("Fichier invalide!");
        }

    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
        Parent root = loader.load();
        txtNom.getScene().setRoot(root);
       AuthentificationController ac = loader.getController();
    }

}
