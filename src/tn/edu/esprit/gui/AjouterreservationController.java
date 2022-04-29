/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.control.AccordionBuilder.create;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.xml.soap.MessageFactory;

import tn.edu.esprit.entities.Reservation;
import tn.edu.esprit.entities.Table;
import tn.edu.esprit.services.servicereservation;
import tn.edu.esprit.services.servicetable;
import tn.edu.esprit.utils.DataSource;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterreservationController implements Initializable {

    @FXML
    private DatePicker tfdateRES;
    @FXML
    private TextField tfnomRES;
    @FXML
    private TextField tfnumtelRES;
    @FXML
    private TextField tfmailRES;
    @FXML
    private TableView<Reservation> reservationaffichage;
    @FXML
    private TableColumn<Reservation, String> nomreservation;
    @FXML
    private TableColumn<Reservation, Integer> numtelreservation;
    @FXML
    private TableColumn<Reservation, String> mailreservation;
    @FXML
    private TableColumn<Reservation, Date> datereservation;
    @FXML
    private TableColumn<Reservation, Integer> numtabreservation;
    @FXML
    private TableColumn<Reservation, Integer> idreservation;
    @FXML
    private ComboBox<Table> cbnumtab;
    servicereservation sr = new servicereservation();
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showreservation();
        showtable();
    }

    ObservableList<Reservation> list = sr.getAll();

    @FXML
    private void ajouterreservation(ActionEvent event) {

        // ****************************** ajout de reservation
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\\\._-]*[a-zA-Z0-9]@[a-zA-Z]+\"\n" +
"                        + \"[a-zA-Z0-9\\\\._-]*[a-zA-Z0-9]+\\\\.[a-zA-Z]{2,4}$";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(tfmailRES.getText());
        
        if (tfnomRES.getText().isEmpty() || tfdateRES.getValue() == null || tfnumtelRES.getText().length() <8 || (controler.matches())  ) {
            Alert a = new Alert(Alert.AlertType.WARNING, "verifier les donnees");
            a.showAndWait();
        } else if (tfdateRES.getValue().isBefore(LocalDate.now())) {
            Alert a = new Alert(Alert.AlertType.WARNING, "verifier la date");
            a.showAndWait();
        } else {
            // ****************************** mailing

            Reservation p = new Reservation(cbnumtab.getValue(), tfnomRES.getText(), Date.valueOf(tfdateRES.getValue()), Integer.parseInt(tfnumtelRES.getText()), tfmailRES.getText());
            System.out.println(cbnumtab.getValue());
            sr.ajouter(p);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "reservation ajoutée");
            a.showAndWait();
            refresh();

            String from = "mootez.gam@esprit.tn";
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
                    return new PasswordAuthentication("mootez.gam@esprit.tn", "tunisie191JMT1979");
                }

            });

            try {
                MimeMessage m = new MimeMessage(s);
                m.setFrom(from);
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                m.setSubject(sub);
                m.setText(content);
                Transport.send(m);
                System.out.println("success");

            } catch (Exception e) {
                e.printStackTrace();
                Alert aa = new Alert(Alert.AlertType.INFORMATION, "hh", ButtonType.OK);

            }

            // QRcode **********************************
            try {

                ByteArrayOutputStream out = QRCode.from(tfnomRES.getText())
                        .to(ImageType.PNG).stream();

                String f_name = tfnomRES.getText();
                String Path_name = "D:\\";

                FileOutputStream fout = new FileOutputStream(new File(Path_name + (f_name + ".PNG")));
                fout.write(out.toByteArray());
                fout.flush();
                Alert aa = new Alert(Alert.AlertType.INFORMATION, "un QR-CODE a ete enregistrer dans votre PC sous votre nom contient tous les infomartions", ButtonType.OK);
                aa.showAndWait();

            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

//Date dateRES=java.sql.Date.valueOf(tfdateRES.getValue());
    /*String nomres=tfnomRES.getText();
        int numres=Integer.parseInt(tfnumtelRES.getText());
        
        String mailres=tfmailRES.getText();
       // Table numtabres = new Table();
        /*Reservation r = new Reservation();
        Table t1 = new Table(tfnumtableRES.getText("t.numerotable"));
       int numtabres= Integer.parseInt(tfnumtableRES.getText());
        
        
        
       Reservation r= new Reservation( numtabres,nomres, dateRES, numres, mailres);
       servicereservation sr = new servicereservation();
        sr.ajouter(r);*/
    public void showreservation() {
        setNumericConstraint();
        nomreservation.setCellValueFactory(new PropertyValueFactory<Reservation, String>("nom"));
        numtelreservation.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("mobile"));
        mailreservation.setCellValueFactory(new PropertyValueFactory<Reservation, String>("email"));
        datereservation.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("datereservation"));
        numtabreservation.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("tableid"));
        idreservation.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id"));

        refresh();
    }

    private void refresh() {
        ObservableList<Reservation> list = sr.getAll();
        reservationaffichage.setItems(list);
        showtable();
    }

    @FXML
    private void btnsupprimerreseervation(ActionEvent event) {
        Reservation res = reservationaffichage.getSelectionModel().getSelectedItem();
        sr.supprimer(res.getId());
        list.remove(res);
        refresh();
    }

    int idres = 0;

    public void modifier() {
        Reservation ev = new Reservation(idres, cbnumtab.getValue(), tfnomRES.getText(), Date.valueOf(tfdateRES.getValue()), Integer.parseInt(tfnumtelRES.getText()), tfmailRES.getText());
        sr.modifier(ev);
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Evénement modifié", ButtonType.OK);
        a.showAndWait();
        refresh();
    }

    /* @FXML
    private void addEvent(ActionEvent event) {
    
            try {
                Reservation p = new Reservation(cbnumtab.getValue(),tfnomRES.getText(), Date.valueOf(tfdateRES.getValue()) ,Integer.parseInt(tfnumtelRES.getText()),tfmailRES.getText());
                sp.ajouter(p);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Promotion ajoutée");
                a.showAndWait();
                refreshData();
                clear();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    

   /* @FXML
    private void updateEvent(ActionEvent event) {
        if (tfPourcentage.getText() == null || tfEvenement.getValue() == null || tfProduit.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Veuillez vérifier les données");
            a.showAndWait();
        } else {
            Promotion p = new Promotion(promID, Double.parseDouble(tfPourcentage.getText()) / 100, tfEvenement.getValue(), tfProduit.getValue());
            sp.modifier(p);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Promotion modifiée");
            a.showAndWait();
            refreshData();
            clear();
            btnupdateProm.setDisable(true);
            btndeleteProm.setDisable(true);
            btnaddProm.setDisable(false);
        }
    }
     
    

     */
    @FXML
    private void btnmodifierreservation(ActionEvent event) {
        modifier();
    }

    @FXML
    private void rowClicked(javafx.scene.input.MouseEvent event) {
        Reservation ev = reservationaffichage.getSelectionModel().getSelectedItem();
        idres = ev.getId();

        tfnomRES.setText(ev.getNom());
        tfnumtelRES.setText(String.valueOf(ev.getMobile()));

        tfmailRES.setText(ev.getEmail());
        tfdateRES.setValue(ev.getDatereservation().toLocalDate());

        cbnumtab.setValue(ev.getTableid());
        /*dateDeb.setValue(ev.getDate_deb().toLocalDate());
        dateFin.setValue(ev.getDate_fin().toLocalDate());*/
        //tfnumtelRES.set(ev.getMobile());
    }

    private void showtable() {
        List<Table> listt = new servicetable().getAll();
        ObservableList<Table> choice = FXCollections.observableArrayList(listt);

        cbnumtab.setItems(choice);
    }

    private void setNumericConstraint() {
        tfnumtelRES.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfnumtelRES.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    private Parent root;
    Stage recette = new Stage();

    @FXML
    private void passeratable(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ajoutertable.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);

        recette.setScene(scene);
        recette.setMaximized(false);//????? pour configurer la fenetre
        recette.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void pdfbutton(ActionEvent event)
            throws Exception {

        Document doc = new Document();
        Connection cnx = DataSource.getInstance().getCnx();
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(null);

        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }
        try {

            String req = "Select r.id ,t.numerotable,r.nom,r.datereservation,r.mobile,r.email from `table` t ,reservation r where t.id=r.tableid_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            PdfWriter.getInstance(doc, new FileOutputStream(path + ".pdf"));
            doc.open();
            //we have four columns in our table
            PdfPTable tb11 = new PdfPTable(5);
            // create a cell object
            MessageFormat header = new MessageFormat("Table De Reservation");

            tb11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

            tb11.addCell("Nom");
            tb11.addCell("mobile");
            tb11.addCell("Email");
            tb11.addCell("Date_Reservation");
            tb11.addCell("Num_Tab");
            tb11.setHeaderRows(1);
            PdfPCell table_cell;
            while (rs.next()) {

                String nom = rs.getString("nom");
                table_cell = new PdfPCell(new Phrase(nom));
                tb11.addCell(table_cell);

                String mobile = rs.getString("mobile");
                table_cell = new PdfPCell(new Phrase(mobile));
                tb11.addCell(table_cell);

                String email = rs.getString("email");
                table_cell = new PdfPCell(new Phrase(email));
                tb11.addCell(table_cell);

                String date = rs.getString("datereservation");
                table_cell = new PdfPCell(new Phrase(date));
                tb11.addCell(table_cell);

                String tableid_id = rs.getString("t.numerotable");
                table_cell = new PdfPCell(new Phrase(tableid_id));
                tb11.addCell(table_cell);
            }
            /* Attach report table to PDF */
            doc.add(tb11);
            doc.close();
            System.out.println("pdf cree");
            /* Close all DB related objects */
            //  rs.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

    }

    @FXML
    private void btnimprimerres(ActionEvent event) {
        // Création du job d'impression.
        final PrinterJob printerJob = PrinterJob.createPrinterJob();

        // Affichage de la boite de dialog de configation de l'impression.    
        if (printerJob.showPrintDialog(reservationaffichage.getScene().getWindow())) {
            final JobSettings settings = printerJob.getJobSettings();
            final PageLayout pageLayout = settings.getPageLayout();
            final double pageWidth = pageLayout.getPrintableWidth();
            final double pageHeight = pageLayout.getPrintableHeight();
            System.out.println(pageWidth);
            System.out.println(pageHeight);
            System.out.println(Printer.getAllPrinters());
            // Mise en page, si nécessaire.
            // Lancement de l'impression.
            if (printerJob.printPage(pageLayout, reservationaffichage)) {
                // Fin de l'impression.
                printerJob.endJob();
            }
        }
    }

    @FXML
    private void passeraufront(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/front.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);

        recette.setScene(scene);
        recette.setMaximized(false);//????? pour configurer la fenetre
        recette.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
