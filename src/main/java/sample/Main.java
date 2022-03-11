package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.service.UserService;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url= new File("src/main/resources/fxml/sample.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        model.User user = new model.User("1", "Alina", "M", "alinamihut@gmail.com", "pass");
        UserService userService = new UserService();
        userService.insertUser(user);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
