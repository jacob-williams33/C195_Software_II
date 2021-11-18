package Controller;

import Model.Customers;
import Model.DBCustomers;
import Model.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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


    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/UpdateCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void onActionLogOutC(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/LogIn.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){

            customersTable.setItems(DBCustomers.getAllCustomers());
            divisionCOL.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
            IDCOL.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            nameCOL.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            addressCOL.setCellValueFactory(new PropertyValueFactory<>("Address"));
            postalCodeCOL.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
            phoneNumberCOL.setCellValueFactory(new PropertyValueFactory<>("Phone"));

    }
}
