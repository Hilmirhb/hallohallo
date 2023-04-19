package test.hallohallo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class BookingView {
        @FXML
        private Label fxNameLabel;

        @FXML
        private Label fxPhoneNumberLabel;

        @FXML
        private Label fxDateLabel;

        @FXML
        private Label fxNumberOfPeopleLabel;

        @FXML
        private Button fxBackButton;

    @FXML
    private Label fxHostName;

    @FXML
    private Label fxBookingID;

    @FXML
    private Label fxTourName;

    public void setUserData(String username, String BookingID) {
        fxHostName.setText(username);
        fxBookingID.setText(BookingID);
        retrieveBookingData(BookingID);
    }

    private void retrieveBookingData(String bookingID) {
        try {
            Connection connection = DriverManager.getConnection(YourLocation.Command());
            Statement statement = connection.createStatement();
            String queryBooking = "SELECT *, Tour.title as tour_title, Tour.date as tour_date FROM Booking JOIN Tour ON Booking.tour_id = Tour.id WHERE Booking.id = " + bookingID;

            ResultSet resultSetBooking = statement.executeQuery(queryBooking);
            if (resultSetBooking.next()) {
                String name = resultSetBooking.getString("name");
                String phoneNumber = resultSetBooking.getString("phone");
                int numberOfPeople = resultSetBooking.getInt("number_of_people");
                String tour = resultSetBooking.getString("tour_title");
                String date = resultSetBooking.getString("tour_date");


                fxNameLabel.setText(name);
                fxPhoneNumberLabel.setText(phoneNumber);
                fxNumberOfPeopleLabel.setText(String.valueOf(numberOfPeople));
                fxTourName.setText(tour);
                fxDateLabel.setText(date);
            }
            resultSetBooking.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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


