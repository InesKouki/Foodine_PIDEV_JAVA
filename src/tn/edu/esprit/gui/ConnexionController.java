/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;
import tn.edu.esprit.entities.Client;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.services.ServiceUtilisateur;
 
/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ConnexionController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPrenom;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private PasswordField tfConfirmPassword;
    @FXML
    private Button btnupload;
    @FXML
    private ImageView uploadIv;
 String fn = null;
    FileChooser fc = new FileChooser();
    String filename = null;
    String filepath = null;
  
  String uploads = "C:\\Users\\ASUS\\Desktop\\Foodine_PIDEV\\public\\uploads\\";
    public static final String ACCOUNT_SID="AC44458b2e54dfa590060fff32d22e20e3";
    public static final String AUTH_TOKEN="4f4a79fd743f967be1efb82b7227dfae";
    ServiceUtilisateur su = new ServiceUtilisateur();
    int min = 1000;
int max = 9999;
int random = (int)Math.floor(Math.random()*(max-min+1)+min);
    @FXML
    private TextField tfPhone;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterUtilisateur(ActionEvent event) throws IOException {
         if(tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty() ||tfUsername.getText().isEmpty() || tfEmail.getText().isEmpty() || tfPassword.getText().isEmpty() || tfConfirmPassword.getText().isEmpty() || tfPhone.getText().isEmpty()){
            Notifications notificationBuilder = Notifications.create()
                    .title("Inscription")
                    .text("Champs vides")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showError();
        }else if (!tfEmail.getText().contains("@") || !tfEmail.getText().contains(".")){
             Notifications notificationBuilder = Notifications.create()
                    .title("Inscription")
                    .text("Email invalide")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showError();
        }else if(su.findEmail(tfEmail.getText())!=null){
             Notifications notificationBuilder = Notifications.create()
                    .title("Inscription")
                    .text("Email deja utilisé")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showError();
        }
         else if(su.findUsername(tfUsername.getText())!=null){
             Notifications notificationBuilder = Notifications.create()
                    .title("Inscription")
                    .text("Username deja utilisé")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showError();
        
        }else if (tfPassword.getText().length() <8 ){
             Notifications notificationBuilder = Notifications.create()
                    .title("Inscription")
                    .text("Mot de passe doit contenir au moins 8 caractères")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showError();
        }
        else if (!tfPassword.getText().equals(tfConfirmPassword.getText())){
             Notifications notificationBuilder = Notifications.create()
                    .title("Inscription")
                    .text("Varifier votre mot de passe")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showError();
        }else if(tfNom.getText().matches("[0-9]+") ||tfPrenom.getText().matches("[0-9]+")){
             Notifications notificationBuilder = Notifications.create()
                    .title("Inscription")
                    .text("Nom et prénom doit contenir que des lettres")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showError();
        }else if(tfPhone.getText().length() < 8 && !tfPhone.getText().matches("[0-9]+") ) {
             Notifications notificationBuilder = Notifications.create()
                    .title("Inscription")
                    .text("Verifier votre numero de téléphone")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showError();
        }
        
        
        
        else {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            
            Message message = Message.creator(new PhoneNumber("\"+216"+tfPhone.getText()+"\""),
                    new PhoneNumber("+19378264363")
                    ,"Utilisez ce code pour activer votre compte :"+random).create();
            long millis=System.currentTimeMillis();
            java.sql.Date date= new java.sql.Date(millis);
            
            User u = new Client(tfNom.getText(),tfPrenom.getText(),tfUsername.getText(),tfEmail.getText(),tfPassword.getText(),filename,date,1,Integer.toString(random),Integer.parseInt(tfPhone.getText()));
            su.ajouterClient((Client) u);
            Notifications notificationBuilder = Notifications.create()
                    .title("Inscription")
                    .text("Un sms vous a été envoyé")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilder.showInformation();
            
            FXMLLoader  loader = new FXMLLoader(getClass().getResource("ActivationCompte.fxml"));
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
            ActivationCompteController ac = loader.getController();
    }
    }

    @FXML
    private void AfficherConnexion(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
            ConnexionController ac = loader.getController();
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
            uploadIv.setImage(new Image(file.toURI().toString()));

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

}
  
