package test.hallohallo;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class NewTour {
    @FXML
    private Label fxHostName;

    public void setUserData(String username) {
        fxHostName.setText(username);
    }
    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField fxTourName;
    @FXML
    private TextArea fxTourDescription;
    @FXML
    private TextField fxPrice;
    @FXML
    private TextField fxPostcode;
    @FXML
    private TextField fxAddress;
    @FXML
    private TextField fxCountry;
    @FXML
    private TextField fxAvailableSpots;
    @FXML
    private DatePicker fxDate;
    @FXML
    private TextField fxTime;
    @FXML
    private Button fxCreateTourButton;
    @FXML
    private Button fxBackButton;
    @FXML
    private TextField fxCity;

    @FXML
    private Label fxFilled;

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
        typeComboBox.getItems().addAll("Activity", "Romantic", "Food and Drinks");
    }

    @FXML
    private void handleCreateButtonAction() {
        if (!(fxTourName.getText().isEmpty() || fxTourDescription.getText().isEmpty() || fxPrice.getText().isEmpty() || fxHostName.getText().isEmpty() || typeComboBox.getValue() == null || fxCountry.getText().isEmpty() || fxAddress.getText().isEmpty() || fxPostcode.getText().isEmpty() || fxCity.getText().isEmpty() || fxDate.getValue() == null || fxTime.getText().isEmpty() || fxAvailableSpots.getText().isEmpty())) {
            try {


                Connection connection = DriverManager.getConnection(YourLocation.Command());
                String insertTour = "INSERT INTO Tour (title, description, price, host, type, country, address, post_code, city, date, time, available_slots) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertTour);
                preparedStatement.setString(1, fxTourName.getText());
                preparedStatement.setString(2, fxTourDescription.getText());
                preparedStatement.setDouble(3, Double.parseDouble(fxPrice.getText()));
                preparedStatement.setString(4, fxHostName.getText());
                preparedStatement.setString(5, typeComboBox.getValue());
                preparedStatement.setString(6, fxCountry.getText());
                preparedStatement.setString(7, fxAddress.getText());
                preparedStatement.setString(8, fxPostcode.getText());
                preparedStatement.setString(9, fxCity.getText());
                preparedStatement.setString(10, fxDate.getValue().toString());
                preparedStatement.setString(11, fxTime.getText());
                preparedStatement.setInt(12, Integer.parseInt(fxAvailableSpots.getText()));

                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();

                Pane parent = (Pane) fxCreateTourButton.getParent();
                parent.getChildren().remove(fxCreateTourButton);

                String filled = "Your Tour has been created";
                fxFilled.setText(filled);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            String filled = "please make sure you've filled everything in";
            fxFilled.setText(filled);
        }
    }
}

