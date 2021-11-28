package Controller;

import Model.Countries;
import Model.Customers;
import Model.Divisions;
import Model.JDBC;
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
        String customer_ID = updateCustomerIDTXT.getText();
        String customer_Name = updateCustomerNameTXT.getText();
        String address = updateAddressTXT.getText();
        String postal_code = updatePostalCodeTXT.getText();
        String phone = updatePhoneTXT.getText();
        Divisions divisions = updateDivisionCombo.getValue();

        JDBC.updateCustomer(customer_Name, address, postal_code, phone, divisions.getDivision_ID(), customer_ID);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionCancelUpdateCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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
