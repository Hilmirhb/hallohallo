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

            Parent root = FXMLLoader.load(getClass().getResource("AdminEditor.fxml"));

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
    @FXML Button FxCreateNewAccount;

    @FXML
    private void handleCreateButtonClick() throws IOException {
        String username = fxHostField.getText();
        String password = Password.getText();

        if (checkUsername(username, password)) {
            Stage stage = (Stage) FxCreateNewAccount.getScene().getWindow();
            Scene scene = stage.getScene();
            Parent root = FXMLLoader.load(getClass().getResource("AdminEditor.fxml"));
            scene.setRoot(root);
            stage.setTitle("Admin Tours");
        } else {
            fxWrongPasswordLabel.setText("Username Taken");
        }
    }



    private boolean checkCredentials(String username, String password) {

        if (username.equals(password)) {
            if (password.equals(username)) {
                return true;
            }
            return false;
        }
        return false;
    }
    private boolean checkUsername(String username, String password) {
        if (username.equals(password)) {
            if (password.equals(username)) {
                return true;
            }
            return false;
        }
        return false;
    }
}





