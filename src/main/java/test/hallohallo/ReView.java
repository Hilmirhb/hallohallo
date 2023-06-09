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
    private Label fxDate;

    @FXML
    private TextField fxNameField;

    @FXML
    private TextArea fxReview;
    @FXML
    private Text fxTourID;

    public void setUserData(String selectedTour, String TourID, String Date) {
        fxTourID.setText(TourID);
        selectedTourLabel.setText(selectedTour);
        fxDate.setText(Date);
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
        Integer rating = fxRatingCombo.getValue();
        String review = fxReview.getText();
        String tourID = fxTourID.getText();

        if (name.isEmpty()  || rating == null || review.isEmpty()) {
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
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection(YourLocation.Command());
            String query = "INSERT INTO Review (rating, name, comment, tour_id) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, rating);
            pstmt.setString(2, name);
            pstmt.setString(3, review);
            pstmt.setString(4, tourID);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
}


