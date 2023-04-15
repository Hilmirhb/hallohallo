package test.hallohallo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminEditor {

    @FXML
    private Button fxLogoutButton;

    @FXML
    private void handleLogoutButtonClick() throws IOException {

            Stage stage = (Stage) fxLogoutButton.getScene().getWindow();
            Scene scene = stage.getScene();


            Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));

            scene.setRoot(root);
            stage.setTitle("Admin Login");
        }


    @FXML
    private Button fxNewTourButton;
    @FXML
    private Button fxDeleteButton;
    @FXML
    private Button fxEditButton;
    @FXML
    private ListView fxTourList;

    @FXML
    private ListView fxReservationList;
}