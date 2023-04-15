package test.hallohallo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloControler {

    @FXML
    private Button fxAdminButton;

    @FXML

    private Label fxNothingSelectedLabel;

    @FXML
    private Button fxBookButton;

    @FXML
    private Button fxAddReviewButton;

    @FXML
    private ListView<String> fxTourList;

    @FXML
    private Label fxName;



    @FXML
    private void handleAdminButtonClick() throws IOException {
        Stage stage = (Stage) fxAdminButton.getScene().getWindow();
        Scene scene = stage.getScene();
        Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));

        scene.setRoot(root);

        stage.setTitle("Admin Login");

    }


    @FXML
    private void handleReviewButtonClick() throws IOException {
        if((fxTourList.getSelectionModel().getSelectedItem() != null) ) {
            Stage stage = (Stage) fxAddReviewButton.getScene().getWindow();
            Scene scene = stage.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReView.fxml"));
            Parent root = loader.load();
            ReView ReViewController = loader.getController();
            String selectedTour = fxTourList.getSelectionModel().getSelectedItem();
            ReViewController.setUserData(selectedTour);
            scene.setRoot(root);
            stage.setTitle("Add a review");
        }
        else {
            fxNothingSelectedLabel.setText("Please make sure you have selected a tour");
        }
    }
    @FXML
    private TextField fxSearchByText;

    @FXML
    private void handleSearchButtonClick() {

        ObservableList<String> tourItems = FXCollections.observableArrayList("Tour 1", "Tour 2", "Tour 3","ekki tour");
        fxTourList.setItems(tourItems);



        String searchText = fxSearchByText.getText();
        if (searchText != null && !searchText.isEmpty()) {
            ObservableList<String> items = fxTourList.getItems();
            ObservableList<String> filteredItems = items.filtered(item -> item.contains(searchText));
            fxTourList.setItems(filteredItems);
        }
    }




    @FXML
    private void handleBookButtonClick() throws IOException {
        if((fxTourList.getSelectionModel().getSelectedItem() != null) ) {
        Stage stage = (Stage) fxBookButton.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Booking.fxml"));
        Parent root = loader.load();
        Booking bookingController = loader.getController();
        String selectedTour = fxTourList.getSelectionModel().getSelectedItem();
        bookingController.setUserData(selectedTour);
        scene.setRoot(root);
        stage.setTitle("Booking");
        }
        else {
            fxNothingSelectedLabel.setText("Please make sure you have selected a tour");
        }
    }

    public void initialize() {
        ObservableList<String> tourItems = FXCollections.observableArrayList("Tour 1", "Tour 2", "Tour 3","ekki tour");
        fxTourList.setItems(tourItems);
        fxTourList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fxName.setText(newValue);
            }
        }
        );
    }
}


