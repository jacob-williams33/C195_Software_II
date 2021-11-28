package Controller;

import Model.*;
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

public class AddAppointmentController implements Initializable {

    public TextField addAppTitleTXT;
    public TextField addAppDescTXT;
    public TextField addAppLocTXT;
    public ComboBox<Contacts> contactsCombo;
    public ComboBox<String> typeCombo;
    public ComboBox<Customers> customerIDCombo;
    public ComboBox<Users> userIDCombo;


    Stage stage;
    Parent scene;

//    @FXML
//    void saveAppointment(ActionEvent event) throws IOException {
//        String appointment_title= addAppTitleTXT.getText();
//        String description = addAppDescTXT.getText();
//        String location = addAppLocTXT.getText();
//        Contacts contacts = contactsCombo.getValue();
//        Customers customers = customerIDCombo.getValue();
//        Users users = userIDCombo.getValue();
//
//
//        JDBC.addAppointment(appointment_title, description, location, contacts.getContact_ID(), customers.getCustomer_ID(),users.getUser_ID());
//        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
//        scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
//        stage.setScene(new Scene(scene));
//        stage.show();
//
//    }

    @FXML
    void onActionCancelAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        contactsCombo.setVisibleRowCount(5);
        contactsCombo.setPromptText("Select Contact...");
        contactsCombo.setItems(JDBC.getAllContacts());
        customerIDCombo.setVisibleRowCount(5);
        customerIDCombo.setPromptText("Select Customer");
        customerIDCombo.setItems(JDBC.getAllCustomers());
        userIDCombo.setVisibleRowCount(5);
        userIDCombo.setPromptText("Select User");
        userIDCombo.setItems(JDBC.getAllUsers());

    }
}
