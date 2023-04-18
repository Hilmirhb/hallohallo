package test.hallohallo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class ReView {

    @FXML
    private ComboBox<Integer> fxRatingCombo;

    @FXML
    private Button fxBackButton;

    @FXML
    private Button fxSubmitButton;

    @FXML
    private Label fxSubmitLabel;

    @FXML
    private Label selectedTourLabel;

    @FXML
    private DatePicker fxDatePicker;

    @FXML
    private TextField fxNameField;

    @FXML
    private TextArea fxReview;
    @FXML
    private Text fxTourID;

    public void setUserData(String selectedTour, String TourID) {
        fxTourID.setText(TourID);
        selectedTourLabel.setText(selectedTour);
    }



    @FXML
    private void handleBackButtonClick() throws IOException {
        Stage stage = (Stage) fxBackButton.getScene().getWindow();
        Scene scene = stage.getScene();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        scene.setRoot(root);
        stage.setTitle("Booking");
    }
    @FXML
    private void handleSubmitButtonClick() {
        String name = fxNameField.getText();
        LocalDate date = fxDatePicker.getValue();
        Integer rating = fxRatingCombo.getValue();
        String review = fxReview.getText();
        String tourID = fxTourID.getText();

        if (name.isEmpty() || date == null || rating == null || review.isEmpty()) {
            fxSubmitLabel.setText("Please fill in all the fields.");
            return;
        }
        submitReview(rating,name,review,tourID);
        Pane parent = (Pane) fxSubmitButton.getParent();
        parent.getChildren().remove(fxSubmitButton);
    }

    @FXML
    public void initialize() {
        fxRatingCombo.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    public void submitReview(int rating, String name, String review, String tourID) {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the database
            Connection con = DriverManager.getConnection("jdbc:sqlite:/Users/hilmir/Desktop/Tours.db");

            // Prepare the query
            String query = "INSERT INTO Review (rating, name, comment, tour_id) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, rating);
            pstmt.setString(2, name);
            pstmt.setString(3, review);
            pstmt.setString(4, tourID);

            // Execute the query
            pstmt.executeUpdate();

            // Close the connection and statement
            pstmt.close();
            con.close();

            // If everything went well, return true


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            // If an error occurred, return false
        }
    }
}


