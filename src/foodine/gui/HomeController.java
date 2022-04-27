/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodine.gui;

import foodine.entities.Evenement;
import foodine.services.ServiceEvenement;
import foodine.services.ServicePromotion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

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

}
