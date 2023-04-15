package test.hallohallo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingView {
        // other code

        @FXML
        private Label fxNameLabel;

        @FXML
        private Label fxPhoneNumberLabel;

        @FXML
        private Label fxDateLabel;

        @FXML
        private Label fxNumberOfPeopleLabel;

        @FXML Button fxBackButton;

        public void GetData(String name, String phoneNumber, String date, String numberOfPeople) {
            fxNameLabel.setText(name);
            fxPhoneNumberLabel.setText(phoneNumber);
            fxDateLabel.setText(date);
            fxNumberOfPeopleLabel.setText(numberOfPeople);
        }
        @FXML
    private void handleBackButtonClick() throws IOException {
        Stage stage = (Stage) fxBackButton.getScene().getWindow();
        Scene scene = stage.getScene();
        Parent root = FXMLLoader.load(getClass().getResource("AdminEditor.fxml"));
        scene.setRoot(root);
        stage.setTitle("AdminPage");

    }




    }


