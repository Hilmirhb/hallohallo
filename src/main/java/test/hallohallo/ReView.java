package test.hallohallo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
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

    public void setUserData(String selectedTour) {

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
    private void handleSubmitButtonClick() throws IOException {
        String name = fxNameField.getText();
        LocalDate date = fxDatePicker.getValue();
        Integer rating = fxRatingCombo.getValue();
        String review = fxReview.getText();

        if (name.isEmpty() || date == null || rating == null || review.isEmpty()) {
            fxSubmitLabel.setText("Please fill in all the fields.");
            return;
        }

        fxSubmitLabel.setText("Your review has been submitted.");
        Pane parent = (Pane) fxSubmitButton.getParent();
        parent.getChildren().remove(fxSubmitButton);
    }

    @FXML
    public void initialize() {
        fxRatingCombo.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}


