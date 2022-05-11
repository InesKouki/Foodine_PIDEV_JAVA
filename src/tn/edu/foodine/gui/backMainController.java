package tn.edu.foodine.gui;

import com.jfoenix.controls.JFXTabPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;

public class backMainController {

    @FXML
    private JFXTabPane tabContainer;
    @FXML
    private Tab eventsTab;
    @FXML
    private AnchorPane eventsContainer;
    @FXML
    private Tab promotionsTab;
    @FXML
    private AnchorPane promotionsContainer;
    private double tabWidth = 140.0;
    public static int lastSelectedTabIndex = 0;
    @FXML
    private Tab homeTab;
    @FXML
    private AnchorPane homeContainer;
    @FXML
    private Tab usersTab;
    @FXML
    private AnchorPane userContainer;
    @FXML
    private Tab reclamationTab;
    @FXML
    private AnchorPane reclamationContainer;
    @FXML
    private Tab planningTab;
    @FXML
    private AnchorPane planningContainer;
    @FXML
    private Tab recetteTab;
    @FXML
    private AnchorPane recetteContainer;
    @FXML
    private Tab reservationTab;
    @FXML
    private AnchorPane reservationContainer;
    @FXML
    private Tab tableTab;
    @FXML
    private AnchorPane tableContainer;
    @FXML
    private Tab categorieTab;
    @FXML
    private AnchorPane categorieContainer;
    @FXML
    private Tab produitTab;
    @FXML
    private AnchorPane produitContainer;
    @FXML
    private Tab livraisonTab;
    @FXML
    private AnchorPane livraisonContainer;
    @FXML
    private Tab commandeTab;
    @FXML
    private AnchorPane commandeContainer;

    public void initialize() {
        configureView();
    }

    private void configureView() {
        tabContainer.setTabMinWidth(tabWidth);
        tabContainer.setTabMaxWidth(tabWidth);
        tabContainer.setTabMinHeight(tabWidth);
        tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);

        EventHandler<Event> replaceBackgroundColorHandler = event -> {
            lastSelectedTabIndex = tabContainer.getSelectionModel().getSelectedIndex();

            Tab currentTab = (Tab) event.getTarget();
            if (currentTab.isSelected()) {
                currentTab.setStyle("-fx-background-color: -fx-focus-color;");
            } else {
                currentTab.setStyle("-fx-background-color: -fx-accent;");
            }
        };

        configureTab(homeTab, "Home", "images/calendar.png", homeContainer, getClass().getResource("home.fxml"), replaceBackgroundColorHandler);
        configureTab(eventsTab, "Evénements", "images/calendar.png", eventsContainer, getClass().getResource("events.fxml"), replaceBackgroundColorHandler);
        configureTab(promotionsTab, "Promotions", "images/discounts.png", promotionsContainer, getClass().getResource("promotions.fxml"), replaceBackgroundColorHandler);

        configureTab(usersTab, "Utlisateurs", "images/group.png", userContainer, getClass().getResource("AfficherUtilisateur.fxml"), replaceBackgroundColorHandler);
        configureTab(reclamationTab, "Réclamations", "images/notes.png", reclamationContainer, getClass().getResource("AfficherReclamation.fxml"), replaceBackgroundColorHandler);

        configureTab(planningTab, "Planning", "images/task.png", planningContainer, getClass().getResource("AjouterPlanning.fxml"), replaceBackgroundColorHandler);
        configureTab(recetteTab, "Recette", "images/icons8-porridge-50.png", recetteContainer, getClass().getResource("Recette.fxml"), replaceBackgroundColorHandler);

        configureTab(reservationTab, "Résérvation", "images/booking.png", reservationContainer, getClass().getResource("ajouterreservation.fxml"), replaceBackgroundColorHandler);
        configureTab(tableTab, "Table", "images/chair.png", tableContainer, getClass().getResource("ajoutertable.fxml"), replaceBackgroundColorHandler);

        configureTab(categorieTab, "Catégorie", "images/category.png", categorieContainer, getClass().getResource("AddCategorie.fxml"), replaceBackgroundColorHandler);
        configureTab(produitTab, "Produit", "images/product.png", produitContainer, getClass().getResource("AddProduit.fxml"), replaceBackgroundColorHandler);

        configureTab(livraisonTab, "Livraison", "images/fast-delivery.png", livraisonContainer, getClass().getResource("backlivraison.fxml"), replaceBackgroundColorHandler);
        configureTab(commandeTab, "Commande", "images/prod.png", commandeContainer, getClass().getResource("backcommande.fxml"), replaceBackgroundColorHandler);

        homeTab.setStyle("-fx-background-color: -fx-focus-color;");
    }
    Parent contentView;

    private void configureTab(Tab tab, String title, String iconPath, AnchorPane containerPane, URL resourceURL, EventHandler<Event> onSelectionChangedEvent) {
        double imageWidth = 30.0;

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(iconPath)));
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);

        Label label = new Label(title);
        label.setMaxWidth(tabWidth - 20);
        label.setPadding(new Insets(5, 0, 0, 0));
        label.setStyle("-fx-text-fill: black; -fx-font-size: 12pt; -fx-font-weight: normal;");
        label.setTextAlignment(TextAlignment.CENTER);

        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setCenter(imageView);
        tabPane.setBottom(label);

        tab.setText("");
        tab.setGraphic(tabPane);

        tab.setOnSelectionChanged(onSelectionChangedEvent);

        if (containerPane != null && resourceURL != null) {
            try {
                Parent contentView = FXMLLoader.load(resourceURL);
                containerPane.getChildren().add(contentView);
                AnchorPane.setTopAnchor(contentView, 0.0);
                AnchorPane.setBottomAnchor(contentView, 0.0);
                AnchorPane.setRightAnchor(contentView, 0.0);
                AnchorPane.setLeftAnchor(contentView, 0.0);
            } catch (IOException e) {
            }
        }
    }

}
