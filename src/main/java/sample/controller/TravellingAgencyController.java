package sample.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
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
import sample.model.Package;
import sample.model.Status;
import sample.repository.PackageRepository;
import sample.service.DestinationService;
import sample.service.PackageService;

import javax.security.auth.callback.Callback;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TravellingAgencyController implements Initializable {
    public Button btnAddPackage;
    public Button btnModifyPackage;
    public Button btnDeletePackage;
    public TextField tfName;
    public TextField tfPrice;
    public TextField tfExtraDetails;
    public TextField tfCapacity;
    public Button btnDestinations;
    public TableView tablePackages;
    public Button btnBack;
    public DatePicker dpStartDate;
    public DatePicker dpEndDate;
    public TextField tfDestination;

    DestinationService destinationService = new DestinationService();
    PackageRepository packageRepository = new PackageRepository();
    PackageService ps = new PackageService();
    public void goBack(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/resources/fxml/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene sceneProducts = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneProducts);
        window.setTitle("Home Page");
        window.show();
    }

    public void goToPageDestinations(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/resources/fxml/destinations.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene sceneProducts = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneProducts);
        window.setTitle("Destinations");
        window.show();
    }


    public static void createTable(List<Package> listOfPackages, TableView<Package> table){
        table.getItems().clear();
        table.getColumns().clear();
        TableColumn column1 = new TableColumn("Name");
        column1.setCellValueFactory(new PropertyValueFactory<Package, String>("name"));
        TableColumn column2 = new TableColumn("Price");
        column2.setCellValueFactory(new PropertyValueFactory<Package, Integer>("price"));
        TableColumn<Package,String> column3 = new TableColumn<>("Destination");
        column3.setCellValueFactory(d ->
            new ReadOnlyStringWrapper((d.getValue().getDestination().getDestinationName())));

        TableColumn column4 = new TableColumn("Start Date");
        column4.setCellValueFactory(new PropertyValueFactory<Package, LocalDate>("startDate"));
        TableColumn column5 = new TableColumn("End Date");
        column5.setCellValueFactory(new PropertyValueFactory<Package, LocalDate>("endDate"));
        TableColumn column6 = new TableColumn("Extra Details");
        column6.setCellValueFactory(new PropertyValueFactory<Package, String>("extraDetails"));
        TableColumn column7 = new TableColumn("Nr of Bookings");
        column7.setCellValueFactory(new PropertyValueFactory<Package, Integer>("nrOfBookings"));
        TableColumn column8 = new TableColumn("Capacity");
        column8.setCellValueFactory(new PropertyValueFactory<Package, Integer>("capacity"));
        TableColumn column9 = new TableColumn("Status");
        column9.setCellValueFactory(new PropertyValueFactory<Package, Status>("status"));


        ObservableList<Package> list = FXCollections.observableArrayList(listOfPackages);
        table.setItems(list);
        table.getColumns().addAll(column1,column2, column3,column4,column5,column6,column7, column8, column9);

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Package> listOfPackages;
        listOfPackages = ps.retreivePackages();
        createTable(listOfPackages, tablePackages);
    }

    public void handleClickTableView(MouseEvent mouseEvent) {
        Package p = (Package) tablePackages.getSelectionModel().getSelectedItem();
        if (p!= null) {
            tfName.setText(p.getName());
            tfPrice.setText(String.valueOf(p.getPrice()));
            tfExtraDetails.setText(p.getExtraDetails());
            tfCapacity.setText(String.valueOf(String.valueOf(p.getCapacity())));
            dpStartDate.setValue(p.getStartDate());
            dpEndDate.setValue(p.getEndDate());
            tfDestination.setText(p.getDestination().getDestinationName());

        }
    }
}
