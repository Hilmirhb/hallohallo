package test.hallohallo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReviewLook {
    @FXML
    private Label fxHostName;

    @FXML
    private Label ReviewID;

    @FXML
    private Label fxTourName;

    @FXML
    private Label fxDateLabel;

    public void setUserData(String username, String reviewID) {
        fxHostName.setText(username);
        ReviewID.setText(reviewID);
        retrieveReviewData(reviewID);

    }

    @FXML
    private Label fxNameLabel;

    @FXML
    private Label fxReview;
    @FXML
    private Label fxRating;
    @FXML
    private Button fxBackButton;

    private void retrieveReviewData(String reviewID) {
        try {
            Connection connection = DriverManager.getConnection(YourLocation.Command());
            Statement statement = connection.createStatement();

            String queryReview = "SELECT Review.*, Tour.title as tour_title, Tour.date as tour_date FROM Review JOIN Tour ON Review.tour_id = Tour.id WHERE Review.id = " + reviewID;
            ResultSet resultSetReview = statement.executeQuery(queryReview);
            if (resultSetReview.next()) {
                String tourName = resultSetReview.getString("tour_title");
                String reviewerName = resultSetReview.getString("name");
                int rating = resultSetReview.getInt("rating");
                String comment = resultSetReview.getString("comment");
                String date = resultSetReview.getString("tour_date");

                fxTourName.setText(tourName);
                fxNameLabel.setText(reviewerName);
                fxRating.setText(String.valueOf(rating));
                fxReview.setText(comment);
                fxDateLabel.setText(date);

            }
            resultSetReview.close();
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

