package foodine.gui;

import foodine.entities.Produit;
import foodine.services.ProduitCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class FrontCategorieController implements Initializable {

    @FXML
    private ComboBox<String> sortComboBox;
    @FXML
    private GridPane eventsContainer;

    ProduitCRUD se = new ProduitCRUD();
    private List<Produit> eventsList;
    private List<Produit> temp;

    int column = 0;
    int row = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fill ComboBox
        sortComboBox.getItems().removeAll(sortComboBox.getItems());
        sortComboBox.getItems().addAll("Trier", "Trier par Nom ↑", "Trier par Nom ↓", "Trier par Prix ↑", "Trier par Prix ↓");
        sortComboBox.getSelectionModel().select("Trier");

        eventsList = se.getProduit();
        setEventItems(eventsList);
    }

    @FXML
    private void selectSort(ActionEvent event) {
        String selected = sortComboBox.getSelectionModel().getSelectedItem();
        if (selected.equals("Trier par Nom ↑")) {
            eventsContainer.getChildren().clear();
            temp = se.sortNameAsc();
            setEventItems(temp);

        } else if (selected.equals("Trier par Nom ↓")) {
            eventsContainer.getChildren().clear();
            temp = se.sortNameDesc();
            setEventItems(temp);

        } else if (selected.equals("Trier par Prix ↑")) {
            eventsContainer.getChildren().clear();
            temp = se.sortPriceAsc();
            setEventItems(temp);

        } else if (selected.equals("Trier par Prix ↓")) {
            eventsContainer.getChildren().clear();
            temp = se.sortPriceDesc();
            setEventItems(temp);
        }

    }
    
    private void setEventItems(List<Produit> list) {
        try {
            for (Produit event : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CategorieItem.fxml"));
                AnchorPane itemBox = fxmlLoader.load();
                CategorieItemController itemController = fxmlLoader.getController();
                itemController.setData(event);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                eventsContainer.add(itemBox, column++, row);
                GridPane.setMargin(itemBox, new Insets(40));

            }
        } catch (IOException ex) {
        }
    }

}
