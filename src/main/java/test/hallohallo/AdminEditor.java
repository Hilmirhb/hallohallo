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
    private ListView fxTourList;

    @FXML
    private ListView fxReservationList;

    @FXML
    private Button fxViewReviewButton;

    @FXML
    private Button fxViewReservationButton;

    @FXML
    private Button fxViewTourButton;







    @FXML
    private void HandleNewTourButton() throws IOException {
        Stage stage = (Stage) fxNewTourButton.getScene().getWindow();
        Scene scene = stage.getScene();
        Parent root = FXMLLoader.load(getClass().getResource("NewTour.fxml"));
        scene.setRoot(root);
        stage.setTitle("New Tour");
    }

    @FXML
    private void HandleViewReservationButton() throws IOException {
        Stage stage = (Stage) fxViewReservationButton.getScene().getWindow();
        Scene scene = stage.getScene();
        Parent root = FXMLLoader.load(getClass().getResource("BookingView.fxml"));
        scene.setRoot(root);
        stage.setTitle("Reservation");
    }
    @FXML
    private void HandleViewReviewButton() throws IOException {
        Stage stage = (Stage) fxViewReviewButton.getScene().getWindow();
        Scene scene = stage.getScene();
        Parent root = FXMLLoader.load(getClass().getResource("ReviewLook.fxml"));
        scene.setRoot(root);
        stage.setTitle("Review");
    }
    @FXML
    private void HandleViewTourButton() throws IOException {
        Stage stage = (Stage) fxViewTourButton.getScene().getWindow();
        Scene scene = stage.getScene();
        Parent root = FXMLLoader.load(getClass().getResource("TourView.fxml"));
        scene.setRoot(root);
        stage.setTitle("Tour viewer");
    }






}


