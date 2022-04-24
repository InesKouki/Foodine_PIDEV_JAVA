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
import java.net.URL;
import java.nio.channels.FileChannel;
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
String uploads = "C:\\Users\\Asus\\Desktop\\Foodine_PIDEV_JAVA\\src\\images\\";
    private String path = "", imgname = "", fn="";
    
    public static final String ACCOUNT_SID="AC0c6aefab9c7673bcc184a93c7b3faade";
    public static final String AUTH_TOKEN="6f4bd195f6994c9feb1aeb3a90c0e4df";
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
                    new PhoneNumber("+12395108595")
                    ,"Utilisez ce code pour activer votre compte :"+random).create();
            long millis=System.currentTimeMillis();
            java.sql.Date date= new java.sql.Date(millis);
            ServiceUtilisateur su = new ServiceUtilisateur();
            User u = new Client(tfNom.getText(),tfPrenom.getText(),tfUsername.getText(),tfEmail.getText(),tfPassword.getText(),imgname,date,1,Integer.toString(random),Integer.parseInt(tfPhone.getText()));
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
    private void upload(ActionEvent event) throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        path= f.getAbsolutePath();
        imgname = f.getName();
        fn = imgname;
        Image getAbsolutePath = null;

        String dd = uploads + f.getName();
        File dest = new File(dd);
        this.copyFile(f, dest);

        System.out.println(dd);

        uploadIv.setImage(new Image("file:" + dest.getAbsolutePath()));
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
}
