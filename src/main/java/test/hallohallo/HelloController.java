package test.hallohallo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelloController {

    @FXML
    private Button fxAdminButton;

    @FXML

    private Label fxNothingSelectedLabel;

    @FXML
    private Button fxBookButton;

    @FXML
    private Button fxAddReviewButton;

    @FXML
    private ListView<String> fxTourList;

    @FXML
    private Label fxName;
    @FXML
    private TextFlow fxDescription;

    @FXML
    private Text fxLocation;
    @FXML
    private Text fxType;
    @FXML
    private Text fxHost;
    @FXML
    private Text fxPrice;
    @FXML
    private Text fxSlots;
    @FXML
    private ListView<String> fxReviewListview;
    @FXML Text fxTourID;




    @FXML
    private void handleAdminButtonClick() throws IOException {
        Stage stage = (Stage) fxAdminButton.getScene().getWindow();
        Scene scene = stage.getScene();
        Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));

        scene.setRoot(root);

        stage.setTitle("Admin Login");
    }

    @FXML
    private void handleReviewButtonClick() throws IOException {
        if ((fxTourList.getSelectionModel().getSelectedItem() != null)) {
            Stage stage = (Stage) fxAddReviewButton.getScene().getWindow();
            Scene scene = stage.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReView.fxml"));
            Parent root = loader.load();
            ReView ReViewController = loader.getController();
            String selectedTour = fxTourList.getSelectionModel().getSelectedItem();
            String TourID = fxTourID.getText();
            ReViewController.setUserData(selectedTour,TourID,fxTime.getText());
            scene.setRoot(root);
            stage.setTitle("Add a review");
        } else {
            fxNothingSelectedLabel.setText("Please make sure you have selected a tour");
        }
    }
    @FXML
    private TextField fxSearchByText;
    @FXML
    private Text fxTime;

    @FXML
    private DatePicker fxDatePicker;

    @FXML
    private void handleSearchButtonClick() {
            String searchText = fxSearchByText.getText();
            LocalDate searchDate = fxDatePicker.getValue();
            String searchDateString = searchDate != null ? searchDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;

            try {
                Class.forName("org.sqlite.JDBC");
                Connection con = DriverManager.getConnection(YourLocation.Command());
                Statement stmt = con.createStatement();
                StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Tour WHERE 1");
                if (searchText != null && !searchText.isEmpty()) {
                    queryBuilder.append(" AND Title LIKE '%" + searchText + "%'");
                }
                if (searchDateString != null) {
                    queryBuilder.append(" AND date = '" + searchDateString + "'");
                }
                ResultSet rs = stmt.executeQuery(queryBuilder.toString());
                fxTourList.getItems().clear();
                while (rs.next()) {
                    String title = rs.getString("Title");
                    fxTourList.getItems().add(title);
                }
                rs.close();
                stmt.close();
                con.close();
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    @FXML
    private void handleBookButtonClick() throws IOException {
        if ((fxTourList.getSelectionModel().getSelectedItem() != null)) {
            Stage stage = (Stage) fxBookButton.getScene().getWindow();
            Scene scene = stage.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Booking.fxml"));
            Parent root = loader.load();
            Booking bookingController = loader.getController();
            String selectedTour = fxTourList.getSelectionModel().getSelectedItem();
            String TourID = fxTourID.getText();
            bookingController.setUserData(selectedTour,TourID,fxTime.getText());
            scene.setRoot(root);
            stage.setTitle("Booking");
        } else {
            fxNothingSelectedLabel.setText("Please make sure you have selected a tour");
        }
    }




    public void initialize() {
        try {

            Class.forName("org.sqlite.JDBC");


            Connection con = DriverManager.getConnection(YourLocation.Command());


            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Tour");

            while (rs.next()) {
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String address = rs.getString("Address");
                String post_code = rs.getString("Post_code");
                String city = rs.getString("City");
                String country = rs.getString("Country");
                String time = rs.getString("time");
                String date = rs.getString("date");
                int slots = rs.getInt("available_slots");
                String Type = rs.getString("Type");
                String Host = rs.getString("Host");
                double price = rs.getDouble("price");
                String ID = rs.getString("id");
                fxTourList.getItems().add(title);

                fxTourList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue != null && newValue.equals(title)) {
                                fxDescription.getChildren().clear();
                                fxDescription.getChildren().add(new Text(description));
                                fxLocation.setText(address + ", " + post_code + " " + city + ", " + country);
                                fxName.setText(title);
                                fxTime.setText(time + ", " + date);
                                fxType.setText("Type: " + Type);
                                fxHost.setText("Host: " + Host);
                                fxPrice.setText("price: " + price + " ISK");
                                fxSlots.setText("Available Slots: " + slots);
                                fxTourID.setText(ID);
                                fxReviewListview.getItems().clear();
                                populateReviews(ID);
                            }
                        }
                );
            }


            rs.close();
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    private void populateReviews(String tourID) {
        try {
            Connection con = DriverManager.getConnection(YourLocation.Command());
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Review WHERE Tour_ID = ?");
            preparedStatement.setInt(1, Integer.parseInt(tourID));
            ResultSet resultSetReview = preparedStatement.executeQuery();

            ObservableList<String> reviewList = FXCollections.observableArrayList();
            while (resultSetReview.next()) {
                String review = resultSetReview.getString("Comment");
                String rating = resultSetReview.getString("rating");
                reviewList.add("Rating: " + rating + " Comment: " + review);
            }

            fxReviewListview.setItems(reviewList);

            resultSetReview.close();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}








