package test.hallohallo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class TourView {
    @FXML
    private Label fxHostName;

    public void setUserData(String username) {
        fxHostName.setText(username);
    }


    @FXML
    private Label fxTourNameLabel;

    @FXML
    private Label fxTourDescriptionLabel;

    @FXML
    private Label fxTourDateLabel;

    @FXML
    private Label fxTourLocationLabel;

    @FXML
    private Label fxNumberOfPeopleLabel;
    @FXML
    private Button fxBackButton;

    public void initData(String tourName, String tourDescription, String tourDate, String tourLocation, String numberOfPeople) {
        fxTourNameLabel.setText(tourName);
        fxTourDescriptionLabel.setText(tourDescription);
        fxTourDateLabel.setText(tourDate);
        fxTourLocationLabel.setText(tourLocation);
        fxNumberOfPeopleLabel.setText(numberOfPeople);
    }
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

}

