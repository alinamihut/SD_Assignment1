package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Destination;
import sample.model.User;
import sample.service.DestinationService;
import sample.service.PackageService;
import sample.service.UserService;
import sample.model.Package;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/main/resources/fxml/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        //User user = new User("3", "Alina", "M", "alinamihut@gmail.com", "pass");
        //UserService userService = new UserService();
        //userService.insertUser(user);

        Destination d = new Destination(1, "paris");
        LocalDate localDate1 = LocalDate.now();//For reference
        LocalDate localDate2 = LocalDate.of(1988, 5, 5);
        Package p = new Package(1, "p1", 100, localDate1, localDate2, "qqqq", 30, 30, sample.model.Status.BOOKED, d);

        //DestinationService destinationService = new DestinationService();
        //destinationService.addDestination(d);
        //PackageService ps = new PackageService();
        //ps.addPackage(p);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
