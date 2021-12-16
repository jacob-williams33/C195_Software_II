package Controller;

import Model.Customers;
import Model.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**This class controls the customers table*/

public class CustomersController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    public TableView<Customers> customersTable;

    @FXML
    public TableColumn<Customers, Integer> divisionCOL;

    @FXML
    public TableColumn<Customers, Integer> IDCOL;

    @FXML
    public TableColumn<Customers, String> nameCOL;

    @FXML
    public TableColumn<Customers, String> addressCOL;

    @FXML
    public TableColumn<Customers, String> postalCodeCOL;

    @FXML
    public TableColumn<Customers, String> phoneNumberCOL;

    /**This method navigates to the add customer screen
     @param event clicked*/

    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This method navigates to the update customer screen
     @param event clicked*/

    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/UpdateCustomer.fxml"));
            loader.load();

            UpdateCustomerController updateCustomerController = loader.getController();
            updateCustomerController.populateSelectedCustomer(customersTable.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Error");
            alert.setContentText("No Customer Selected");

            alert.showAndWait();
        }
    }

    /**This method navigates to the main menu screen
     @param event clicked*/

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This method navigates to the log in screen
     @param event clicked*/

    @FXML
    void onActionLogOutC(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/LogIn.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This method deletes the selected customer. Deletes any associated appointments simultaneously.
     @param event clicked*/

    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("This will permanently delete customer " + customersTable.getSelectionModel().getSelectedItem().getCustomer_ID()
        + " " + customersTable.getSelectionModel().getSelectedItem().getCustomer_Name());
        alert.setContentText("Do you wish to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            JDBC.deleteCustomer(customersTable.getSelectionModel().getSelectedItem().getCustomer_ID());
        }
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This method sets the customer table. This method sets each column with customers from database*/
    @Override
    public void initialize(URL url, ResourceBundle rb){

            customersTable.setItems(JDBC.getAllCustomers());
            divisionCOL.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
            IDCOL.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            nameCOL.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            addressCOL.setCellValueFactory(new PropertyValueFactory<>("Address"));
            postalCodeCOL.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
            phoneNumberCOL.setCellValueFactory(new PropertyValueFactory<>("Phone"));

    }
}
