package test.hallohallo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;



public class AdminEditor {
    @FXML
    private Label fxHostName;

    public void setUserData(String username) {
        fxHostName.setText(username);
        populateLists();
    }

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

    @FXML ListView fxReviewList;

    @FXML
    private Button fxViewReviewButton;

    @FXML
    private Button fxViewReservationButton;

    @FXML
    private Button fxViewTourButton;


    @FXML
    private void HandleNewTourButton() throws IOException {
        Stage stage = (Stage) fxViewTourButton.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewTour.fxml"));
        Parent root = loader.load();
        NewTour Controller = loader.getController();
        Controller.setUserData(fxHostName.getText());
        scene.setRoot(root);
        stage.setTitle("New Tour");
    }

    @FXML
    private void HandleViewReservationButton() throws IOException {

        Stage stage = (Stage) fxViewTourButton.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingView.fxml"));
        Parent root = loader.load();
        BookingView Controller = loader.getController();
        Controller.setUserData(fxHostName.getText());
        scene.setRoot(root);
        stage.setTitle("Reservation");


    }

    @FXML
    private void HandleViewReviewButton() throws IOException {

        Stage stage = (Stage) fxViewTourButton.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReviewLook.fxml"));
        Parent root = loader.load();
        ReviewLook Controller = loader.getController();
        Controller.setUserData(fxHostName.getText());
        scene.setRoot(root);
        stage.setTitle("Review");
    }
    @FXML
    private void HandleViewTourButton() throws IOException {
        Stage stage = (Stage) fxViewTourButton.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TourView.fxml"));
        Parent root = loader.load();
        TourView Controller = loader.getController();
        Controller.setUserData(fxHostName.getText());
        scene.setRoot(root);
        stage.setTitle("Tour viewer");
    }
    @FXML
    private Label fxTourID;
    @FXML
    private Label fxReviewID;
    @FXML
    private Label fxReservationID;

    @FXML
    private void populateLists() {
            fxTourList.getItems().clear();
            fxReviewList.getItems().clear();
            fxReservationList.getItems().clear();
            String hostName = fxHostName.getText();


        try {
            Connection connection = DriverManager.getConnection(YourLocation.Command());
            Statement statement = connection.createStatement();

            // Populate tours
            String queryTours = "SELECT id, title FROM Tour WHERE host = '" + hostName + "'";
            ResultSet resultSetTours = statement.executeQuery(queryTours);
            while (resultSetTours.next()) {
                int tourId = resultSetTours.getInt("id");
                String title = resultSetTours.getString("title");
                String tourInfo = "ID: " + tourId + ", " + title;
                fxTourList.getItems().add(tourInfo);
            }
            resultSetTours.close();

            // Add a listener for Tour selection
            fxTourList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    String selectedTourInfo = (String) fxTourList.getSelectionModel().getSelectedItem();
                    String tourID = selectedTourInfo.split(",")[0].split(" ")[1];
                    fxTourID.setText(tourID);
                }
            });

            // Populate reviews
                String queryReviews = "SELECT Review.*, Tour.title as tour_title FROM Review JOIN Tour ON Review.tour_id = Tour.id WHERE Tour.host = '" + hostName + "' ORDER BY Review.id DESC";
                ResultSet resultSetReviews = statement.executeQuery(queryReviews);
                while (resultSetReviews.next()) {
                    int reviewId = resultSetReviews.getInt("id");
                    int rating = resultSetReviews.getInt("rating");
                    String tourTitle = resultSetReviews.getString("tour_title");
                    String reviewInfo = "ID: " + reviewId + ", Rating: " + rating + ", " + tourTitle;
                    fxReviewList.getItems().add(reviewInfo);
                }
                resultSetReviews.close();

                fxReviewList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        String selectedReviewInfo = (String) fxReviewList.getSelectionModel().getSelectedItem();
                        String reviewID = selectedReviewInfo.split(",")[0].split(" ")[1];
                        fxReviewID.setText(reviewID);
                    }
                });



                // Populate reservations
                String queryReservations = "SELECT Booking.*, Tour.title as tour_title FROM Booking JOIN Tour ON Booking.tour_id = Tour.id WHERE Tour.host = '" + hostName + "'ORDER BY Booking.id DESC";
                ResultSet resultSetReservations = statement.executeQuery(queryReservations);
                while (resultSetReservations.next()) {
                    int reservationId = resultSetReservations.getInt("id");
                    String name = resultSetReservations.getString("name");
                    int numberOfPeople = resultSetReservations.getInt("number_of_people");
                    String tourTitle = resultSetReservations.getString("tour_title");
                    String reservationInfo = "ID: " + reservationId + ", " + tourTitle + ", Name: " + name + ", No People: " + numberOfPeople;
                    fxReservationList.getItems().add(reservationInfo);
                }

                resultSetReservations.close();
                fxReservationList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        String selectedReservationInfo = (String) fxReservationList.getSelectionModel().getSelectedItem();
                        String reservationID = selectedReservationInfo.split(",")[0].split(" ")[1];
                        fxReservationID.setText(reservationID);
                    }
                });
                resultSetReservations.close();
                fxReservationList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        String selectedReservationInfo = (String) fxReservationList.getSelectionModel().getSelectedItem();
                        String reservationID = selectedReservationInfo.split(",")[0].split(" ")[1];
                        fxReservationID.setText(reservationID);
                    }
                });
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }










