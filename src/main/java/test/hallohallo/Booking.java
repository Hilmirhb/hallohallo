package test.hallohallo;

import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.*;
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
import javafx.scene.text.Text;
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

    @FXML
    private Text fxTourID;

    public void setUserData(String selectedTour, String TourID) {
        selectedTourLabel.setText(selectedTour);
        fxTourID.setText(TourID);
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
                boolean spots = updateAvailableSpots(Integer.parseInt(fxTourID.getText()),numberOfPeople);
                if (spots) {
                    insertBooking(name, PhoneNumber, numberOfPeople, Integer.parseInt(fxTourID.getText()));
                    String bookingInfo = "You have just booked a trip under the name " + name + " on the " + date.toString() + " for " + numberOfPeople + " persons";
                    fxBookingLabel.setText(bookingInfo);
                    Pane parent = (Pane) fxBookButton.getParent();
                    parent.getChildren().remove(fxBookButton);}
                else{
                    fxBookingLabel.setText("There aren't enough spots available;");
                }
            }

            else {
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

    private void insertBooking(String name, String phone, int numberOfPeople, int tourId) {
        String url = YourLocation.Command(); // Replace with your SQLite database name

        try (Connection connection = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO Booking (name, phone, number_of_people, tour_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setInt(3, numberOfPeople);
            statement.setInt(4, tourId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking successfully inserted!");
            } else {
                System.out.println("Booking insertion failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting booking: " + e.getMessage());
        }
    }

    public boolean updateAvailableSpots(int tourId, int bookedSpots) {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the database
            Connection con = DriverManager.getConnection(YourLocation.Command());

            // Get the current available spots for the tour
            String selectQuery = "SELECT available_slots FROM Tour WHERE id = ?";
            PreparedStatement selectStmt = con.prepareStatement(selectQuery);
            selectStmt.setInt(1, tourId);
            ResultSet resultSet = selectStmt.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Error: Tour not found");
                return false;
            }
            int availableSpots = resultSet.getInt("available_slots");

            // Calculate the new available spots
            int newAvailableSpots = availableSpots - bookedSpots;

            // Check if the new available spots are not negative
            if (newAvailableSpots < 0) {
                System.out.println("Error: Not enough available spots");
                return false;
            }

            // Prepare the update query
            String updateQuery = "UPDATE Tour SET available_slots = ? WHERE id = ?";
            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setInt(1, newAvailableSpots);
            updateStmt.setInt(2, tourId);

            // Execute the update query
            updateStmt.executeUpdate();

            // Close the connection, statements, and result set
            resultSet.close();
            selectStmt.close();
            updateStmt.close();
            con.close();

            // If everything went well, return true
            return true;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            // If an error occurred, return false
            return false;
        }
    }




    public void initialize() {
        fxNumberOfPeopleCombo.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
    }
}


