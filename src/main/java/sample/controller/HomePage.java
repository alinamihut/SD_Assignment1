package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HomePage {
    public Button btnTravellingAgency;
    public Button btnLogin;
    public Button btnSignUp;


    public void goToPageTravellingAgency(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/resources/fxml/travellingAgency.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene sceneProducts = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneProducts);
        window.setTitle("Travelling Agency");
        window.show();

    }

    public void goToPageLogin(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/resources/fxml/login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene sceneProducts = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneProducts);
        window.setTitle("Login");
        window.show();

    }

    public void goToPageSignUp(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/resources/fxml/signup.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene sceneProducts = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneProducts);
        window.setTitle("Sign up");
        window.show();

    }
}
