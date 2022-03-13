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

public class Register {
    public TextField tfFirstName;
    public TextField tfLastName;
    public TextField tfEmail;
    public TextField tfPassword;


    public Button btnSignUp;
    public Button btnGoBack;

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

    public void insertNewUser (){
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String email = tfEmail.getText();
        String password = tfPassword.getText();
        String userInsertedString = userService.insertUser(firstName,lastName,email,password);
       showAlert(userInsertedString);
    }

    public void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText(s);
        alert.show();
    }
}
