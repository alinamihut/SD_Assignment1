package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.service.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Login {
    public TextField tfPassword;
    public TextField tfEmail;
    public Button btnGoBack;
    public Button btnLogin;

    UserService userService = new UserService();
    public void goBack(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/resources/fxml/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene sceneProducts = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneProducts);
        window.setTitle("Home Page");
        window.show();

    }

    public void validateLoginAndGoToNextPage(ActionEvent actionEvent) throws IOException {
        if (userService.validateLogin(tfEmail.getText(), tfPassword.getText())){
            URL url = new File("src/main/resources/fxml/userProfile.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene sceneProducts = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(sceneProducts);
            window.setTitle("Home Page");
            window.show();
        }
        else{
            showAlert(userService.returnLoginStatus(tfEmail.getText(), tfPassword.getText()));
        }

    }
    public void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText(s);
        alert.show();
    }
}
