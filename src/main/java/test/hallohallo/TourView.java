package test.hallohallo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class TourView {

    @FXML
    private Button fxDeleteButton;

    @FXML
    private Button fxEditButton;

    @FXML
    private void handelEditButtonClick() throws IOException {
        {
            Stage stage = (Stage) fxEditButton.getScene().getWindow();
            Scene scene = stage.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit Tour.fxml"));
            Parent root = loader.load();
            EditTourController EditTourController = loader.getController();
            EditTourController.setUserData(fxHostName.getText(),fxTourID.getText());
            scene.setRoot(root);
            stage.setTitle("Edit Tour");
        }
    }

    @FXML
    private Label fxHostName;

    @FXML
    private Label fxTourID;

    public void setUserData(String username, String tourID) {
        fxHostName.setText(username);
        fxTourID.setText(tourID);
        fetchTourData(tourID);
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

    private void fetchTourData(String tourID) {
        try {
            Connection connection = DriverManager.getConnection(YourLocation.Command());
            Statement statement = connection.createStatement();

            String queryTour = "SELECT * FROM Tour WHERE id = " + tourID;
            ResultSet resultSetTour = statement.executeQuery(queryTour);

            if (resultSetTour.next()) {
                String title = resultSetTour.getString("Title");
                String description = resultSetTour.getString("Description");
                String address = resultSetTour.getString("Address");
                String post_code = resultSetTour.getString("Post_code");
                String city = resultSetTour.getString("City");
                String country = resultSetTour.getString("Country");
                String time = resultSetTour.getString("time");
                String date = resultSetTour.getString("date");
                int slots = resultSetTour.getInt("available_slots");
                String Type = resultSetTour.getString("Type");
                double price = resultSetTour.getDouble("price");
                fxTourLocationLabel.setText(address + ", " + post_code + " " + city + ", " + country);
                fxTourDateLabel.setText(time + ", " + date);
                fxNumberOfPeopleLabel.setText(String.valueOf(slots));
                fxTourNameLabel.setText(title);
                fxTourDescriptionLabel.setText(description);
            }

            resultSetTour.close();
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
    @FXML
    private void handelDeleteButtonClick() throws IOException {
        deleteTourAndRelatedData();

        Stage stage = (Stage) fxDeleteButton.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEditor.fxml"));
        Parent root = loader.load();
        AdminEditor adminController = loader.getController();
        adminController.setUserData(fxHostName.getText());
        scene.setRoot(root);
        stage.setTitle("Admin Tours");
    }

    private void deleteTourAndRelatedData() {
        try {
            Connection connection = DriverManager.getConnection(YourLocation.Command());

            // Delete associated Reviews
            String deleteReviews = "DELETE FROM Review WHERE tour_id=?";
            PreparedStatement reviewsStatement = connection.prepareStatement(deleteReviews);
            reviewsStatement.setInt(1, Integer.parseInt(fxTourID.getText()));
            reviewsStatement.executeUpdate();
            reviewsStatement.close();

            // Delete associated Bookings
            String deleteBookings = "DELETE FROM Booking WHERE tour_id=?";
            PreparedStatement bookingsStatement = connection.prepareStatement(deleteBookings);
            bookingsStatement.setInt(1, Integer.parseInt(fxTourID.getText()));
            bookingsStatement.executeUpdate();
            bookingsStatement.close();

            // Delete Tour
            String deleteTour = "DELETE FROM Tour WHERE id=?";
            PreparedStatement tourStatement = connection.prepareStatement(deleteTour);
            tourStatement.setInt(1, Integer.parseInt(fxTourID.getText()));
            tourStatement.executeUpdate();
            tourStatement.close();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

