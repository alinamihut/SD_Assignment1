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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import sample.model.Destination;
import sample.model.Package;
import sample.model.Status;
import sample.service.DestinationService;
import sample.service.PackageService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserProfile implements Initializable {

    public DatePicker dpStartDate;
    public DatePicker dpEndDate;
    public TableView tablePackages;
    public Button btnFilterPrice;
    public TextField tfPrice;
    public ChoiceBox cbDestination;
    public Button btnBack;
    public Button btnFilterDate;
    public Button btnFilterDestination;

    List<Package> listOfAvailablePackages = new ArrayList<>();
    List<Destination> listOfDestinations;
    PackageService ps = new PackageService();
    DestinationService ds = new DestinationService();
    public void goBack(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/resources/fxml/logjn.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene sceneProducts = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneProducts);
        window.setTitle("Login");
        window.show();
    }

    public static void createTable(List<Package> listOfAvailablePackages, TableView<Package> table){
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


        ObservableList<Package> list = FXCollections.observableArrayList(listOfAvailablePackages);
        table.setItems(list);
        table.getColumns().addAll(column1,column2, column3,column4,column5,column6,column7, column8, column9);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Package>  listOfAllPackages = ps.retrievePackages();
        for (Package p:listOfAllPackages){
            if (p.getStatus()!=Status.BOOKED){
                listOfAvailablePackages.add(p);
            }
        }
        createTable(listOfAvailablePackages, tablePackages);
        listOfDestinations=ds.retrieveAllDestinations();
        for (Destination d:listOfDestinations){
            cbDestination.getItems().add(d.getDestinationName());
        }

    }

    public void filterByPrice(ActionEvent actionEvent){
        Integer price = Integer.parseInt(tfPrice.getText());
        List<Package>  newList = new ArrayList<>();
        for (Package p:listOfAvailablePackages){
            if (p.getPrice().equals(price)){
                newList.add(p);
            }
        }
        if (newList.size() == 0){
            showAlert("There are no available packages with the chosen price!");
        }
        createTable(newList, tablePackages);
        tfPrice.clear();
    }

    public void filterByDestination(ActionEvent actionEvent){
        String destinationName= (String) cbDestination.getSelectionModel().getSelectedItem();
        List<Package>  newList = new ArrayList<>();
        for (Package p:listOfAvailablePackages){
            if (p.getDestination().getDestinationName().equals(destinationName)){
                newList.add(p);
            }
        }
        if (newList.size() == 0){
            showAlert("There are no available packages for the chosen destination!");
        }
        createTable(newList, tablePackages);

    }

    public void filterByDate(ActionEvent actionEvent){
        LocalDate startDate = dpStartDate.getValue();
        LocalDate endDate = dpEndDate.getValue();
        List<Package> newList = new ArrayList<>();
        if (startDate.isAfter(endDate) || endDate.isBefore(LocalDate.now())){
            showAlert("Please choose a valid time interval!");
        }


        else {
            for (Package p : listOfAvailablePackages) {
                if (p.getStartDate().isEqual(startDate) && p.getEndDate().isEqual(endDate)) {
                    newList.add(p);
                }
            }
            if (newList.size() == 0) {
                showAlert("There are no available packages for the chosen time period!");
            }
        }
        createTable(newList, tablePackages);

    }
    public void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText(s);
        alert.show();
    }
}
