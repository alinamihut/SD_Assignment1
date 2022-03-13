package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.Destination;
import sample.service.DestinationService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DestinationsController implements Initializable {
    public Button btnAddDestination;
    public Button btnDeleteDestination;
    public Button btnBack;
    public TableView tableDestinations;
    public TextField tfName;

    DestinationService ds = new DestinationService();

    List<Destination> listOfDestinations;

    public void goBack(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/resources/fxml/travellingAgency.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene sceneProducts = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneProducts);
        window.setTitle("Travelling Agency");
        window.show();
    }

    public  void createTable(List<Destination> listOfDestinations, TableView<Destination> table){
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
        List<Destination> listOfDestinations;
        listOfDestinations = ds.retrieveAllDestinations();
        createTable(listOfDestinations, tableDestinations);
    }


    public void handleClickTableView(MouseEvent mouseEvent) {
        Destination d = (Destination) tableDestinations.getSelectionModel().getSelectedItem();
        if (d!= null) {
            tfName.setText(d.getDestinationName());


        }
    }

    public void pressButtonAddDestination(){
        String destinationName = tfName.getText();

        String resultString = ds.addDestination(destinationName);
        listOfDestinations = ds.retrieveAllDestinations();
        createTable(listOfDestinations, tableDestinations);
        tableDestinations.refresh();
        showAlert(resultString);
        tfName.clear();
    }

    public void pressButtonDeleteDestination(){
        String destinationName = tfName.getText();
        ds.deleteDestination(destinationName);
        tfName.clear();
        listOfDestinations = ds.retrieveAllDestinations();
        createTable(listOfDestinations, tableDestinations);
        tableDestinations.refresh();
        showAlert("Destination deleted successfully!");
    }

    public void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText(s);
        alert.show();
    }
}
