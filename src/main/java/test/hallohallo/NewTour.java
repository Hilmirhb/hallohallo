package test.hallohallo;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;

import java.io.IOException;


public class NewTour {
    @FXML
    private Label fxHostName;

    public void setUserData(String username) {
        fxHostName.setText(username);

    }

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEditor.fxml"));
        Parent root = loader.load();
        AdminEditor adminController = loader.getController();
        adminController.setUserData(fxHostName.getText());
        scene.setRoot(root);
        stage.setTitle("Admin Tours");
    }



    @FXML
    private void initialize() {
        // Initialize the typeComboBox
        typeComboBox.getItems().addAll("Activity", "Romantic", "Food and Drinks");
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

