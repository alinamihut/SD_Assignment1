package sample.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.Destination;
import sample.model.Package;
import sample.model.Status;
import sample.service.DestinationService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DestinationsController implements Initializable {
    public Button btnAddDestination;
    public Button btnDeleteDestination;
    public Button btnBack;
    public TableView tableDestinations;
    public TextField tfName;

    DestinationService ds = new DestinationService();
    public void handleClickTableView(MouseEvent mouseEvent) {
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/resources/fxml/travellingAgency.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene sceneProducts = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneProducts);
        window.setTitle("Travelling Agency");
        window.show();
    }

    public static void createTable(List<Destination> listOfDestinations, TableView<Destination> table){
        table.getItems().clear();
        table.getColumns().clear();
        TableColumn column1 = new TableColumn("Name");
        column1.setCellValueFactory(new PropertyValueFactory<Destination, String>("destinationName"));

        ObservableList<Destination> list = FXCollections.observableArrayList(listOfDestinations);
        table.setItems(list);
        table.getColumns().addAll(column1);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Destination> listOfDestinations = new ArrayList<>();
        listOfDestinations = ds.retreiveDestinations();
        createTable(listOfDestinations, tableDestinations);
    }
}
