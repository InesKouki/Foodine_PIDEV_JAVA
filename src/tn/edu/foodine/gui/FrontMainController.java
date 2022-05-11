package tn.edu.foodine.gui;

import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FrontMainController {

    @FXML
    private JFXTabPane tabContainer;
    @FXML
    private Tab homeTab;
    @FXML
    private AnchorPane homeContainer;
    @FXML
    private Tab eventsTab;
    @FXML
    private AnchorPane eventsContainer;

    private double tabWidth = 140.0;
    public static int lastSelectedTabIndex = 0;
    @FXML
    private Tab profileTab;
    @FXML
    private AnchorPane profileContainer;
    @FXML
    private Tab reclamationTab;
    @FXML
    private AnchorPane reclamationContainer;
    @FXML
    private Tab recetteTab;
    @FXML
    private AnchorPane recetteContainer;
    private Tab logoutTab;
    private AnchorPane logoutContainer;
    @FXML
    private Tab reservationTab;
    @FXML
    private AnchorPane reservationContainer;
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
    @FXML
    private Tab panierTab;
    @FXML
    private AnchorPane panierContainer;

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

        configureTab(homeTab, "Home", "images/calendar.png", homeContainer, getClass().getResource("frontHome.fxml"), replaceBackgroundColorHandler);
        configureTab(eventsTab, "Evénements", "images/calendar.png", eventsContainer, getClass().getResource("frontEvents.fxml"), replaceBackgroundColorHandler);
        configureTab(profileTab, "Profile", "images/user.png", profileContainer, getClass().getResource("ProfileClient.fxml"), replaceBackgroundColorHandler);
        configureTab(reclamationTab, "Réclamation", "images/notes.png", reclamationContainer, getClass().getResource("AjouterReclamation.fxml"), replaceBackgroundColorHandler);
        configureTab(recetteTab, "Recette", "images/icons8-porridge-50.png", recetteContainer, getClass().getResource("FrontRecette.fxml"), replaceBackgroundColorHandler);
        configureTab(reservationTab, "Résérvation", "images/booking.png", reservationContainer, getClass().getResource("front.fxml"), replaceBackgroundColorHandler);
        configureTab(produitTab, "Produits", "images/product.png", produitContainer, getClass().getResource("frontCategorie.fxml"), replaceBackgroundColorHandler);
        configureTab(commandeTab, "Commande", "images/product.png", commandeContainer, getClass().getResource("ajoutercommande.fxml"), replaceBackgroundColorHandler);
        configureTab(livraisonTab, "Livraison", "images/fast-delivery.png", livraisonContainer, getClass().getResource("ajouterlivraison.fxml"), replaceBackgroundColorHandler);
        configureTab(panierTab, "Panier", "images/product.png", panierContainer, getClass().getResource("Cart.fxml"), replaceBackgroundColorHandler);

        homeTab.setStyle("-fx-background-color: -fx-focus-color;");
    }

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
