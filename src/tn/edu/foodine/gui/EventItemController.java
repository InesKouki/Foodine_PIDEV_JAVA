package tn.edu.foodine.gui;

import com.jfoenix.controls.JFXButton;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import tn.edu.foodine.entities.Evenement;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EventItemController {

    String uploads = "C:/Users/azizm/Desktop/SEM2/PIDEV/Foodine_PIDEV/public/uploads/";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");

    @FXML
    private ImageView img;
    @FXML
    private Label labelName;
    @FXML
    private Label labelDate;

    private Evenement event;
    @FXML
    private JFXButton btnShare;

    public void setData(Evenement event) {
        this.event = event;
        labelName.setText(event.getName());
        labelDate.setText("Du " + event.getDate_deb().toLocalDate().format(formatter).toUpperCase() + " Jusqu'à " + event.getDate_fin().toLocalDate().format(formatter).toUpperCase());
        Image im = new Image("file:" + uploads + event.getImage());
        img.setImage(im);
    }

    @FXML
    private void share(ActionEvent event) throws URISyntaxException, IOException {
        String accessToken = "EAAHX4dyp3lUBAKYxRY5ecGB6JPZA7GS7YtArENFAW7mWPGPeZBEL6HEmHgRYhOyaDzvoua04T85Rupk13HVFQVrkdWyT9WeomM63lxhLq9PwfHn7Hui7SLKtF7kc1m7moE1PWndgpzmDZCKkGDBL51SkkE96zMG7g4zgYu1FzJU2H5LQQAKSSyPkuxpzX3fYRCME3vbtA1mDpHzTz8aZCS0gADnLhq0koZCjuwhliWfxaktLfs9Vhh3yyrNMKDu8ZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.UNVERSIONED);

        Desktop.getDesktop().browse(new URI("https://www.facebook.com/sharer/sharer.php?kid_directed_site=0&sdk=joey&u=http%3A%2F%2F127.0.0.1%3A8000%2Fevenement-" + this.event.getId() + "&display=popup&ref=plugin&src=share_button"));

    }

}
