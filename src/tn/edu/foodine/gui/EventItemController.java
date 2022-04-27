package tn.edu.foodine.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import tn.edu.foodine.entities.Recette;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.controlsfx.control.Rating;
import tn.edu.foodine.entities.rating;
import tn.edu.foodine.services.ServiceRating;
import tn.edu.foodine.services.ServiceRecette;

public class EventItemController {

    String uploads = "C:\\Users\\PC\\Desktop\\Mobile\\Foodine_PIDEV_Desktop\\src\\img\\";

    @FXML
    private ImageView img;
    @FXML
    private Label labelName;
    private Label labeldes;
    private rating rt;
    private Recette rec;
    @FXML
    private Label labelDate;
    @FXML
    private Button btnlien;
    @FXML
    private Rating Rating;
    @FXML
    private Button calcul;
    @FXML
    private Rating R1;
    @FXML
    private Label lbrat;
int c;
    public void setData(Recette rec) {
        this.rec = rec;
        ServiceRating sr =new ServiceRating();
        
        
        try {
            c = sr.rech(rec.getId());
        
        
        lbrat.setText(Integer.toString(sr.rechrat(c)));
            System.out.println(Integer.toString(sr.rechrat(c)));
        labelName.setText(rec.getNom());
        labelDate.setText(rec.getDescription());
        
        Image im = new Image("file:" + uploads + rec.getImage());
        img.setImage(im);
        } catch (SQLException ex) {
            Logger.getLogger(EventItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void openlink1(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.facebook.com/sharer/sharer.php?kid_directed_site=0&sdk=joey&u=http%3A%2F%2F127.0.0.1%3A8000%2Frecette&display=popup&ref=plugin&src=share_button"));

    }

    int i=1,idr=0;
    @FXML
    private void calcul(ActionEvent event) throws SQLException {
         ServiceRating sr =new ServiceRating();
         int c=sr.rech(rec.getId());
         System.out.println(sr.rech(rec.getId()));
         i=i+1;
         if (!sr.verif(rec.getId())){
            sr.ajouter(new rating((float) Rating.getRating(),i,this.rec));
         }
         else{
             sr.update(new rating((((float) Rating.getRating()+sr.rechrat(c))/i),i,this.rec),c);
         }
         
    }
}
