package test.hallohallo;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class NewTour {

    @FXML
    private TextField tourNameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField postcodeField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField slotsField;

    @FXML
    private Button createButton;
    @FXML
    private Button fxBackButton;
    @FXML
    private void handleBackButtonClick() throws IOException {
        Stage stage = (Stage) fxBackButton.getScene().getWindow();

        Scene scene = stage.getScene();

        Parent root = FXMLLoader.load(getClass().getResource("AdminEditor.fxml"));

        scene.setRoot(root);

        stage.setTitle("Booking");
    }



    @FXML
    private void initialize() {
        // Initialize the typeComboBox
        typeComboBox.getItems().addAll("Activity", "Romantic");
    }

    @FXML
    private void handleCreateButtonAction() {
        // Get the values from the form fields
        String tourName = tourNameField.getText();
        String description = descriptionArea.getText();
        String price = priceField.getText();
        String type = typeComboBox.getValue();
        String postcode = postcodeField.getText();
        String address = addressField.getText();
        String country = countryField.getText();
        String slots = slotsField.getText();
    }
}

