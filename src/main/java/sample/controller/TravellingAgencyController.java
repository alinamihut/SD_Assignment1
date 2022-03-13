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
import javafx.scene.input.MouseEvent;
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
    public ChoiceBox cbDestination;

    List<Package> listOfPackages;
    DestinationService destinationService = new DestinationService();
    PackageService ps = new PackageService();
    List<Destination> listOfDestinations;
    private String nameOfTheClickedPackage;
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

        listOfPackages = ps.retrievePackages();
        createTable(listOfPackages, tablePackages);
        listOfDestinations=destinationService.retrieveAllDestinations();
        for (Destination d:listOfDestinations){
            cbDestination.getItems().add(d.getDestinationName());
        }

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
            cbDestination.setValue(p.getDestination().getDestinationName());
            nameOfTheClickedPackage = p.getName();

        }
    }
    public boolean validateNonemptyField(String s) {
        if (s.isEmpty()) {
            return false;
        }
        return true;
    }
    public void clickAddPackageButton(ActionEvent actionEvent) throws IOException {



        if(!validateNonemptyField(tfName.getText()) || !validateNonemptyField(tfPrice.getText()) || !validateNonemptyField(tfCapacity.getText())
                || !validateNonemptyField(tfExtraDetails.getText()) ){
            showAlert( "Please fill all the fields!");
        }
        else {
            String name = tfName.getText();
            Integer price = Integer.parseInt(tfPrice.getText());
            String d = (String) cbDestination.getValue();
            Integer capacity = Integer.parseInt(tfCapacity.getText());
            String extraDetails = tfExtraDetails.getText();
            LocalDate startDate = dpStartDate.getValue();
            LocalDate endDate = dpEndDate.getValue();

            String returnedString = ps.addPackage(name, price, startDate, endDate, extraDetails, capacity, d);
            listOfPackages = ps.retrievePackages();
            createTable(listOfPackages,tablePackages);
            tablePackages.refresh();
            showAlert(returnedString);


        }
    }

    public void clickEditPackageButton(ActionEvent actionEvent) throws IOException {
        if(!validateNonemptyField(tfName.getText()) || !validateNonemptyField(tfPrice.getText()) || !validateNonemptyField(tfCapacity.getText())
                || !validateNonemptyField(tfExtraDetails.getText()) ){
            showAlert( "Please fill all the fields!");
        }
        else {
            String name = tfName.getText();
            Integer price = Integer.parseInt(tfPrice.getText());
            String d = (String) cbDestination.getValue();
            Integer capacity = Integer.parseInt(tfCapacity.getText());
            String extraDetails = tfExtraDetails.getText();
            LocalDate startDate = dpStartDate.getValue();
            LocalDate endDate = dpEndDate.getValue();

            String returnedString = ps.editPackage(nameOfTheClickedPackage, name, price, startDate, endDate, extraDetails, capacity, d);
            listOfPackages = ps.retrievePackages();
            createTable(listOfPackages,tablePackages);
            tablePackages.refresh();
            showAlert(returnedString);


        }
    }

    public void clickDeletePackageButton(ActionEvent actionEvent) throws IOException {
        String name = tfName.getText();
        ps.deletePackage(name);
        tfName.clear();
        listOfPackages = ps.retrievePackages();
        createTable(listOfPackages,tablePackages);
        tablePackages.refresh();
        showAlert("Package deleted successfully!");
    }
    public void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText(s);
        alert.show();
    }
}
