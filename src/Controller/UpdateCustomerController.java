package Controller;

import Model.Countries;
import Model.Customers;
import Model.Divisions;
import Model.JDBC;
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

public class UpdateCustomerController implements Initializable {

    public TextField updateCustomerIDTXT;
    public TextField updateCustomerNameTXT;
    public TextField updateAddressTXT;
    public TextField updatePostalCodeTXT;
    public TextField updatePhoneTXT;
    public ComboBox<Countries> updateCountryCombo;
    public ComboBox<Divisions> updateDivisionCombo;

    Stage stage;
    Parent scene;
    public Boolean errorCheck(String Appointment_ID) {
        ObservableList<Customers> allCustomers = JDBC.getAllCustomers();

        if (updateCustomerNameTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Customer Name is Blank");
            alert.setContentText("Input Customer Name");
            alert.showAndWait();
            return false;
        }
        if (updateAddressTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Address is Blank");
            alert.setContentText("Input Customer Address");
            alert.showAndWait();
            return false;
        }
        if (updatePostalCodeTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Postal Code is Blank");
            alert.setContentText("Input Customer Postal Code");
            alert.showAndWait();
            return false;
        }
        if (updatePhoneTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Phone Number is Blank");
            alert.setContentText("Input Customer Phone Number");
            alert.showAndWait();
            return false;
        }
        if (updateCountryCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Country Box is Blank");
            alert.setContentText("Select Country");
            alert.showAndWait();
            return false;
        }
        if (updateDivisionCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Division is Blank");
            alert.setContentText("Select Division");
            alert.showAndWait();
            return false;
        }
        return true;
    }



    public void populateSelectedCustomer (Customers customers) {
        updateCustomerIDTXT.setText(String.valueOf(customers.getCustomer_ID()));
        updateCustomerNameTXT.setText(customers.getCustomer_Name());
        updateAddressTXT.setText(customers.getAddress());
        updatePostalCodeTXT.setText(customers.getPostal_Code());
        updatePhoneTXT.setText(customers.getPhone());
        for (Countries c : updateCountryCombo.getItems()) {
            if (customers.getCountry_ID() == c.getCountry_ID()) {
                updateCountryCombo.setValue(c);
                break;
            }
        }
        for (Divisions d : updateDivisionCombo.getItems()) {
            if (customers.getDivision_ID() == d.getDivision_ID()) {
                updateDivisionCombo.setValue(d);
                break;
            }
        }
    }

    @FXML
    void onActionSaveCustomer(ActionEvent event) throws IOException {
        Boolean gtg = errorCheck(updateCustomerIDTXT.getText());
        if (gtg) {
            String customer_ID = updateCustomerIDTXT.getText();
            String customer_Name = updateCustomerNameTXT.getText();
            String address = updateAddressTXT.getText();
            String postal_code = updatePostalCodeTXT.getText();
            String phone = updatePhoneTXT.getText();
            Divisions divisions = updateDivisionCombo.getValue();

            JDBC.updateCustomer(customer_Name, address, postal_code, phone, divisions.getDivision_ID(), customer_ID);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionCancelUpdateCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public ObservableList<Divisions> divisionFilter() {
        ObservableList<Divisions> allDivisions = JDBC.getAllDivisions();
        FilteredList<Divisions> filteredDivisionList = new FilteredList<>(allDivisions, i -> i.getCountry_ID() == updateCountryCombo.getSelectionModel().getSelectedItem().getCountry_ID());
        return filteredDivisionList;
    }
    @FXML
    public void onCountrySelect(ActionEvent actionEvent) {
        updateDivisionCombo.setItems(divisionFilter());
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        updateCountryCombo.setVisibleRowCount(5);
        updateCountryCombo.setPromptText("Select Country...");
        updateCountryCombo.setItems(JDBC.getAllCountries());
        updateDivisionCombo.setVisibleRowCount(5);
        updateDivisionCombo.setPromptText("Select Division");
        updateDivisionCombo.setItems(JDBC.getAllDivisions());
    }
}
