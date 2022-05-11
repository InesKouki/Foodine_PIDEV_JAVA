/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.foodine.gui;

import java.io.IOException;
import tn.edu.foodine.entities.Evenement;
import tn.edu.foodine.services.ServiceEvenement;
import tn.edu.foodine.services.ServicePromotion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class HomeController implements Initializable {

    @FXML
    private PieChart stats;

    ServiceEvenement se = new ServiceEvenement();
    ServicePromotion sp = new ServicePromotion();
    ObservableList<Evenement> eventsList = FXCollections.observableArrayList();

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    @FXML
    private NumberAxis xAxis;
    @FXML
    private CategoryAxis yAxis;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private ImageView logoutButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventsList = se.getAll();
        for (Evenement e : eventsList) {
            pieChartData.add(new PieChart.Data(e.getName(), sp.countPromotions(e.getId())));
        }
        stats.setData(pieChartData);

        XYChart.Series <String, Number> dataSeries1 = new XYChart.Series<>();

        for (Evenement e : eventsList) {
            dataSeries1.getData().add(new XYChart.Data(e.getName(), sp.countPromotions(e.getId())));
        }

        barChart.getData().add(dataSeries1);
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
        Parent root = loader.load();
        logoutButton.getScene().setRoot(root);
        AuthentificationController ac = loader.getController();
    }

}
