package test.hallohallo;

import javafx.fxml.FXML;

import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Booking {

    @FXML
    private TextField fxNameField;

    @FXML
    private DatePicker fxDatePicker;

    @FXML
    private ComboBox<Integer> fxNumberOfPeopleCombo;

    @FXML
    private Button fxBookButton;

    @FXML
    private Label fxBookingLabel;

    @FXML

    private TextField fxPhoneNumber;

    @FXML private Button fxBackButton;

    @FXML
    private Label selectedTourLabel;

    public void setUserData(String selectedTour) {
        selectedTourLabel.setText(selectedTour);
    }

    @FXML
    private void handleBookButtonClick() {
        fxBookingLabel.setText("Make Sure you have filled everything in!");
        String name = fxNameField.getText();
        LocalDate date = fxDatePicker.getValue();
        int numberOfPeople = fxNumberOfPeopleCombo.getValue();
        String PhoneNumber = fxPhoneNumber.getText();

        if(!name.isEmpty()) {
            if (PhoneNumber.matches("\\d{7,14}")) {
                String bookingInfo = "You have just booked a trip under the name " + name + " on the " + date.toString() + " for " + numberOfPeople + " persons";
                fxBookingLabel.setText(bookingInfo);
                Pane parent = (Pane) fxBookButton.getParent();
                parent.getChildren().remove(fxBookButton);
            } else {
                fxBookingLabel.setText("Invalid phone number. Please only enter the digits.");
            }
        }

    }
    @FXML
    private void handleBackButtonClick() throws IOException {
        Stage stage = (Stage) fxBackButton.getScene().getWindow();

        Scene scene = stage.getScene();

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        scene.setRoot(root);

        stage.setTitle("Booking");

    }


    public void initialize() {
        fxNumberOfPeopleCombo.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
    }
}


