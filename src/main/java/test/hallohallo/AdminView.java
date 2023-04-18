
package test.hallohallo;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

    public class AdminView {

        @FXML
        private TextField fxHostField;
        @FXML
        private PasswordField Password;

        @FXML

        private Label fxWrongPasswordLabel;

        @FXML

        private Button fxBackButton;

        @FXML
        Button fxLoginButton;


        @FXML
        private void handleLoginButtonClick() throws IOException {
            String username = fxHostField.getText();
            String password = Password.getText();

            if (checkCredentials(username, password)) {
                Stage stage = (Stage) fxLoginButton.getScene().getWindow();
                Scene scene = stage.getScene();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEditor.fxml"));
                Parent root = loader.load();
                AdminEditor adminController = loader.getController();
                adminController.setUserData(username);
                scene.setRoot(root);
                stage.setTitle("Admin Tours");


            } else {
                fxWrongPasswordLabel.setText("Wrong Username or Password, Please Try again");
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

        @FXML
        Button FxCreateNewAccount;

        @FXML
        private void handleCreateButtonClick() throws IOException {
            // Get the entered username and password from the text fields
            String username = fxHostField.getText();
            String password = Password.getText();

            Boolean Success = CreateAccount(username,password);
                if (Success == true){
                    fxWrongPasswordLabel.setText("Your account has been created please log in");
                    return;
                }
                fxWrongPasswordLabel.setText("Please try another Username");
            }




        private boolean checkCredentials(String username, String password) {
            try {
                // Load the SQLite JDBC driver
                Class.forName("org.sqlite.JDBC");

                // Connect to the database
                Connection con = DriverManager.getConnection(YourLocation.Command());

                // Create a statement
                Statement stmt = con.createStatement();

                // Prepare the query
                String query = "SELECT COUNT(*) AS count FROM admin WHERE username = ? AND password = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, password);

                // Execute the query and get the result set
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                int count = rs.getInt("count");

                // Close the connection, statement, and result set
                rs.close();
                pstmt.close();
                stmt.close();
                con.close();

                // Return true if there is a matching admin record in the database, otherwise false
                return count > 0;
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
        }
        public boolean CreateAccount(String username, String password) {
            try {
                // Load the SQLite JDBC driver
                Class.forName("org.sqlite.JDBC");

                // Connect to the database
                Connection con = DriverManager.getConnection(YourLocation.Command());

                // Prepare the query
                String query = "INSERT INTO admin (username, password) VALUES (?, ?)";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, password);

                // Execute the query
                pstmt.executeUpdate();

                // Close the connection and statement
                pstmt.close();
                con.close();

                // If everything went well, return true
                return true;

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error: " + e.getMessage());
                // If an error occurred, return false
                return false;
            }
        }

    }




