package Controller;

import Model.Countries;
import Model.Divisions;
import Model.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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



    @FXML
    void onActionSaveCustomer(ActionEvent event) throws IOException {
        String customer_Name = addCustomerNameTXT.getText();
        String address = addAddressTXT.getText();
        String postal_code = addPostalCodeTXT.getText();
        String phone = addPhoneTXT.getText();
        Divisions divisions = divisionCombo.getValue();

        JDBC.addCustomer(customer_Name, address, postal_code, phone, divisions.getDivision_ID());
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

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
