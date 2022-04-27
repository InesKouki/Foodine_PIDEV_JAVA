package foodine.gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    @FXML
    private Button btnFront;
    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private Parent root;

    @FXML
    private void front(ActionEvent event) throws IOException {
        Stage front = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/front_main_view.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);

        front.setScene(scene);
        front.setMaximized(true);
        front.setTitle("Foodine");
        front.getIcons().add(new Image(getClass().getResourceAsStream("../gui/images/icon.png")));
        front.show();
        Stage stage = (Stage) btnFront.getScene().getWindow();
//        stage.close();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage front = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/main_view.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);

        front.setScene(scene);
        front.setMaximized(true);
        front.setTitle("Foodine");
        front.getIcons().add(new Image(getClass().getResourceAsStream("../gui/images/icon.png")));
        front.show();
        Stage stage = (Stage) btnBack.getScene().getWindow();
//        stage.close();

    }

}
