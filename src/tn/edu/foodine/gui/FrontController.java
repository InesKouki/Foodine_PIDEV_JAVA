/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import tn.edu.foodine.entities.Reservation;
import tn.edu.foodine.entities.Table;
import tn.edu.foodine.services.servicereservation;
import tn.edu.foodine.services.servicetable;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FrontController implements Initializable {

    @FXML
    private TextField tfnomRES;
    @FXML
    private TextField tfnumtelRES;
    @FXML
    private TextField tfmailRES;
    @FXML
    private DatePicker tfdateRES;
    @FXML
    private ComboBox<Table> cbnumtab;
    @FXML
    private AnchorPane closeButton;
    @FXML
    private ImageView mmm;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showtable();
       
    }

    private void showtable() {
        List<Table> listt = new servicetable().getAll();
        ObservableList<Table> choice = FXCollections.observableArrayList(listt);

        cbnumtab.setItems(choice);
    }
    servicereservation sr = new servicereservation();
    ObservableList<Reservation> list = sr.getAll();

    

    @FXML
    private void ajouterreservationFRONT(ActionEvent event) {
        // QRcode **********************************
 
        
        
        
        try {

            ByteArrayOutputStream out = QRCode.from(tfnomRES.getText())
                    .to(ImageType.PNG).stream();

            String f_name = tfnomRES.getText();
            String Path_name = "D:\\";

            FileOutputStream fout = new FileOutputStream(new File(Path_name + (f_name + ".PNG")));
            fout.write(out.toByteArray());
            fout.flush();
           // Alert a = new Alert(Alert.AlertType.INFORMATION, "un QR-CODE a ete enregistrer dans votre PC sous votre nom contient tous les infomartions", ButtonType.OK);
            //a.showAndWait();

        } catch (Exception e) {
            System.out.println(e);
        }

        if (tfnomRES.getText().isEmpty() || tfdateRES.getValue() == null || tfnumtelRES.getText().length() <8 ) {
             Notifications notificationBuilderr = Notifications.create()
                    .title("Reservation")
                    .text("champs vides")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilderr.showError();
           
        } else if (tfdateRES.getValue().isBefore(LocalDate.now())) {
          Notifications notificationBuilderr = Notifications.create()
                    .title("Reservation")
                    .text("verifier la date")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilderr.showError();
        } else {
            // ****************************** mailing

            Reservation p = new Reservation(cbnumtab.getValue(), tfnomRES.getText(), Date.valueOf(tfdateRES.getValue()), Integer.parseInt(tfnumtelRES.getText()), tfmailRES.getText());
            System.out.println(cbnumtab.getValue());
            sr.ajouter(p);
           
           // refresh();

            String from = "foodine01@gmail.com";
            String to = tfmailRES.getText();
            String host = "localhost";
            String sub = "votre reservation a ete bien effectue";
            String content = "votre reservation a ete bien effectue  ";

            Properties pp = new Properties();
            pp.put("mail.smtp.auth", "true");
            pp.put("mail.smtp.starttls.enable", "true");
            pp.put("mail.smtp.host", "smtp.gmail.com");
            pp.put("mail.smtp.port", "587");
            Session s = Session.getDefaultInstance(pp, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("foodine01@gmail.com", "foodinefoodine");
                }

            });

            try {
                MimeMessage m = new MimeMessage(s);
                m.setFrom(from);
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                m.setSubject(sub);
                m.setText(content);
                Transport.send(m);
                Notifications notificationBuilder = Notifications.create()
                    .title("Reservation")
                    .text("reservation ajouter")
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
             Notifications notificationBuilderr = Notifications.create()
                    .title("Reservation")
                    .text("Un QR-CODE a ete enregistrer dans votre PC")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override 
                        public void handle(ActionEvent event){
                            //System.out.println("Supp");
                        }
                    });
           notificationBuilderr.showConfirm();


            } catch (Exception e) {
                e.printStackTrace();
                //Alert aa = new Alert(Alert.AlertType.INFORMATION, "hh", ButtonType.OK);

            }
        }

    }
  private Parent root;
    Stage recette = new Stage();
    @FXML
    private void passerreservation(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ajouterreservation.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);

        recette.setScene(scene);
        recette.setMaximized(false);//????? pour configurer la fenetre
        recette.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
