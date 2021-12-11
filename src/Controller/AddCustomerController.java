package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    public TextField addCustomerIDTXT;
    public TextField addCustomerNameTXT;
    public TextField addAddressTXT;
    public TextField addPostalCodeTXT;
    public TextField addPhoneTXT;
    public ComboBox<Countries> countryCombo;
    public ComboBox<Divisions> divisionCombo;

    Stage stage;
    Parent scene;

    public Boolean errorCheck(String Appointment_ID) {
        ObservableList<Customers> allCustomers = JDBC.getAllCustomers();

        if (addCustomerNameTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Customer Name is Blank");
            alert.setContentText("Input Customer Name");
            alert.showAndWait();
            return false;
        }
        if (addAddressTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Address is Blank");
            alert.setContentText("Input Customer Address");
            alert.showAndWait();
            return false;
        }
        if (addPostalCodeTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Postal Code is Blank");
            alert.setContentText("Input Customer Postal Code");
            alert.showAndWait();
            return false;
        }
        if (addPhoneTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Phone Number is Blank");
            alert.setContentText("Input Customer Phone Number");
            alert.showAndWait();
            return false;
        }
        if (countryCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Country Box is Blank");
            alert.setContentText("Select Country");
            alert.showAndWait();
            return false;
        }
        if (divisionCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Division is Blank");
            alert.setContentText("Select Division");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    void onActionSaveCustomer(ActionEvent event) throws IOException {
        Boolean gtg = errorCheck(addCustomerIDTXT.getText());
        if (gtg) {

            String customer_Name = addCustomerNameTXT.getText();
            String address = addAddressTXT.getText();
            String postal_code = addPostalCodeTXT.getText();
            String phone = addPhoneTXT.getText();
            Divisions divisions = divisionCombo.getValue();

            JDBC.addCustomer(customer_Name, address, postal_code, phone, divisions.getDivision_ID());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    @FXML
    void onActionCancelAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    public ObservableList<Divisions> divisionFilter() {
        ObservableList<Divisions> allDivisions = JDBC.getAllDivisions();
        FilteredList<Divisions> filteredDivisionList = new FilteredList<>(allDivisions, i -> i.getCountry_ID() == countryCombo.getSelectionModel().getSelectedItem().getCountry_ID());
        return filteredDivisionList;
    }
    @FXML
    public void onCountrySelect(ActionEvent actionEvent) {
        divisionCombo.setItems(divisionFilter());
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        countryCombo.setVisibleRowCount(5);
        countryCombo.setPromptText("Select Country...");
        countryCombo.setItems(JDBC.getAllCountries());
        divisionCombo.setVisibleRowCount(5);
        divisionCombo.setPromptText("Select Division");

    }


}
