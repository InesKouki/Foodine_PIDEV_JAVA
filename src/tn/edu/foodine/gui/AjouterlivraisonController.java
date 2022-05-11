/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import tn.edu.foodine.entities.Livraison;
import tn.edu.foodine.services.ServiceLivraison;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.mail.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static java.util.Locale.filter;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/*

* To change this license header, choose License Headers in Project Properties.

* To change this template file, choose Tools | Templates

* and open the template in the editor.

 */
/**
 *
 * FXML Controller class
 *
 *
 *
 * @author Aziz
 *
 */
public class AjouterlivraisonController implements Initializable {
        int lid = 0;

    @FXML

    private TextField tfAddresse;

    @FXML

    private TextField tfCodepostal;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfDetails;
    @FXML
    private TableView<Livraison> tvLivraison;
    @FXML
    private TableColumn<Livraison, Integer> col_id;
    @FXML
    
    private TableColumn<Livraison, String> colAddresse;
    @FXML
    private TableColumn<Livraison, String> colCodepostal;
    @FXML
    private TableColumn<Livraison, String> colEmail;
    @FXML
    private TableColumn<Livraison, String> colPhone;
    
    @FXML
    private TableColumn<Livraison, String> colDetails;
    private Button btnUpdate;

    private ServiceLivraison liv = new ServiceLivraison();
    ObservableList<Livraison> livraisonlist;
    private Button comm;
    @FXML
    private Button closeButton;
    @FXML
    private TextField tfsearch;
    @FXML
    private Button btnAjouter;
    ObservableList<Livraison> temp = FXCollections.observableArrayList();
    @FXML
    private Button btnModifier;
    @FXML
    private Button excel;
    

    /**
     *
     * Initializes the controller class.
     *
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        showliv();
    }

    public Connection getConnection() {
        Connection cnx;
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodine", "root", "");
            return cnx;
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
            return null;
        }
    }
    public void showliv(){
        col_id.setCellValueFactory(new PropertyValueFactory<Livraison, Integer>("id"));
        colAddresse.setCellValueFactory(new PropertyValueFactory<Livraison, String>("addresse"));
        colCodepostal.setCellValueFactory(new PropertyValueFactory<Livraison, String>("codepostal"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Livraison, String>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Livraison, String>("phone"));
        colDetails.setCellValueFactory(new PropertyValueFactory<Livraison, String>("details"));
        temp=liv.getAll();
        tvLivraison.setItems(temp);
    }
    @FXML
    private void ajouterlivraison(ActionEvent event) {
if (tfAddresse.getText().equals("") || tfCodepostal.getText().equals("") || tfEmail.getText().equals("") || tfPhone.getText().equals("") ||tfDetails.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.OK);
            a.showAndWait();
        } else {
        Livraison livraison = new Livraison(tfAddresse.getText(), tfCodepostal.getText(), tfEmail.getText(), tfPhone.getText(), tfDetails.getText());
        liv.ajouter(livraison);
        refresh();}
        
        
        
        String from = "foodine01@gmail.com";
            String to = tfEmail.getText();
            System.out.println(to);
            String host = "localhost";
            String sub = "";
            String content = "votre livraison a ete passé" ;

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
                System.out.println("success");

            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    private void insert() {
        String query = "INSERT INTO livraison VALUES('" + tfAddresse.getText() + "','" + tfCodepostal.getText() + "','" + tfEmail.getText() + "','" + tfPhone.getText() + "','" + tfDetails.getText() + "')";
        executeQuery(query);
        refresh();
    }

    private void executeQuery(String query) {
        Connection cnx = getConnection();
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void refresh() {
        temp = liv.getAll();
        tvLivraison.setItems(temp);
    }
/*
    public ObservableList<Livraison> getLivraisonList() {
        ObservableList<Livraison> ll = FXCollections.observableArrayList();
        Connection cnx = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM livraison";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Livraison livraison;
            while (rs.next()) {
                livraison = new Livraison(rs.getInt("id"), rs.getString("addresse"), rs.getString("codepostal"), rs.getString("email"), rs.getString("phone"), rs.getString("details"));
                ll.add(livraison);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ll;
    }*/

    @FXML
    private void rowclicked(MouseEvent event) {
        Livraison livraison = tvLivraison.getSelectionModel().getSelectedItem();
       
        tfAddresse.setText(livraison.getAddresse());
        tfCodepostal.setText(livraison.getCodepostal());
        tfEmail.setText(livraison.getEmail());
        tfPhone.setText(livraison.getPhone());
        tfDetails.setText(livraison.getDetails());
    }
    @FXML
    private void supprimer(ActionEvent event) {
        Livraison livraison = tvLivraison.getSelectionModel().getSelectedItem();
        liv.supprimer(livraison.getId());
        refresh();
    }

    private Parent root;
    Stage com = new Stage();

    @FXML
    private void btnCommande(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutercommande.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        com.setScene(scene);
        com.setMaximized(true);
        com.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }
    @FXML
    private void techarger_pdf(ActionEvent event) {
        Document doc = new Document();
        String FILE_NAME = "E:\\java_pdf\\chillyfacts.pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\azizm\\Desktop\\liv.pdf"));
            doc.open();
            Paragraph p = new Paragraph();
            p.add("Foodine");
            p.setAlignment(Element.ALIGN_CENTER);
            
            
            doc.add(p);
            PdfPTable table = new PdfPTable(5); // 2 columns.
            table.setSpacingBefore(20f);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Addresse"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Code postal"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Email"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Phone"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Détails"));
            
          
            cell1.setBackgroundColor(BaseColor.RED);
            cell2.setBackgroundColor(BaseColor.RED);
            cell3.setBackgroundColor(BaseColor.RED);
            cell4.setBackgroundColor(BaseColor.RED);
            cell5.setBackgroundColor(BaseColor.RED);
            
            cell1.setPadding(5);
            cell2.setPadding(5);
            cell3.setPadding(5);
            cell4.setPadding(5);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            
            livraisonlist = liv.getAll();
            System.out.println("kkk");
            for (int i=0; i<livraisonlist.size();i++) {
                String addresse=livraisonlist.get(i).getAddresse();
                String code=livraisonlist.get(i).getCodepostal();
                String email=livraisonlist.get(i).getEmail();
                String phone=livraisonlist.get(i).getPhone();
                String details=livraisonlist.get(i).getDetails();
                
                
             
                table.addCell(addresse);
                table.addCell(code);
                table.addCell(email);
                table.addCell(phone);
                table.addCell(details);
                
            }
            doc.add(table);
            
            Desktop.getDesktop().open(new File("C:\\Users\\azizm\\Desktop\\liv.pdf"));
            
            doc.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjouterlivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AjouterlivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AjouterlivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
FilteredList<Livraison> filter = new FilteredList<>(temp, e -> true);
    SortedList<Livraison> sort = new SortedList<>(filter);
    
 
  

    @FXML
    private void search(KeyEvent event) {
         tfsearch.setOnKeyReleased(e -> {
            
            tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
               filter.setPredicate(t -> {
                   if (newValue == null || newValue.isEmpty()) {
                       return true;
                   }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(t.getEmail()).toLowerCase().contains(lowerCaseFilter) ) {
                        return true;
                    } else {
                        return false;
                   }
                });
            });
               
            sort.comparatorProperty().bind(tvLivraison.comparatorProperty());
          tvLivraison.setItems(sort);
      });
    }

    @FXML
    private void modifierliv(ActionEvent event) {
         Livraison livraison = new Livraison(lid, tfAddresse.getText(), tfCodepostal.getText(), tfEmail.getText(), tfPhone.getText(), tfDetails.getText());
        liv.modifier(livraison);
        refresh();
    }

    @FXML
  private void exportExcel(ActionEvent event)throws SQLException, FileNotFoundException, IOException  {
       
      Connection con = getConnection();        
            String query = "Select * from  livraison";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
           
           
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Détails Evenement");
            HSSFRow header = sheet.createRow(0);
           
           
           
           header.createCell(0).setCellValue("addresse");
           header.createCell(1).setCellValue("codepostal");
           header.createCell(2).setCellValue("email");
           
            header.createCell(3).setCellValue("phone");
            header.createCell(4).setCellValue("details");
             

           
            int index = 1;
            while(rs.next()){
                HSSFRow row = sheet.createRow(index);
               
                row.createCell(0).setCellValue(rs.getString("addresse"));
                row.createCell(1).setCellValue(rs.getString("codepostal"));
                row.createCell(2).setCellValue(rs.getString("email"));
                row.createCell(3).setCellValue(rs.getString("phone"));
                row.createCell(4).setCellValue(rs.getString("details"));

                index++;
            }
           
            FileOutputStream file = new FileOutputStream("C:\\Users\\azizm\\Desktop\\Evenement.xls");
            wb.write(file);
            file.close();
            JOptionPane.showMessageDialog(null, "Exportation 'EXCEL' effectuée avec succés");
           
            pst.close();
            rs.close();
   
    }




}
