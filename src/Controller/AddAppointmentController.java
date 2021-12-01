package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController<closeBusiness> implements Initializable {

    public TextField addAppTitleTXT;
    public TextField addAppDescTXT;
    public TextField addAppLocTXT;
    public DatePicker datePicker;
    public ComboBox<LocalTime> startTime;
    public ComboBox<LocalTime> endTime;
    public ComboBox<Contacts> contactsCombo;
    public ComboBox<String> typeCombo;
    public ComboBox<Customers> customerIDCombo;
    public ComboBox<Users> userIDCombo;


    Stage stage;
    Parent scene;

    public ObservableList<String> createTypeList() {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.addAll("Initial Intake", "Follow Up", "Med Check", "Brain Dump", "Process Discussion", "Termination");
        return types;
    }


    public ObservableList<LocalTime> timeRanges() {
        ObservableList<LocalTime> comboTimes = FXCollections.observableArrayList();
        LocalTime openBusiness = LocalTime.of(8, 0);
        LocalTime closeBusiness = LocalTime.of(22, 0);
        LocalTime t = LocalTime.of(8, 0);
        Boolean inRange = t.isBefore(closeBusiness);
        while (inRange = true) {
            t = t.plusMinutes(30);
            comboTimes.add(t);
            if (t == closeBusiness) {
                break;
            }
        }
        return comboTimes;
    }

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
//        JDBC.addAppointment(appointment_title, description, location, );
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
        typeCombo.setVisibleRowCount(5);
        typeCombo.setPromptText("Select Appointment Type");
        typeCombo.setItems(createTypeList());
        startTime.setVisibleRowCount(5);
        startTime.setPromptText("Select Start Time");
        startTime.setItems(timeRanges());
        endTime.setVisibleRowCount(5);
        endTime.setPromptText("Select End Time");
        endTime.setItems(timeRanges());
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
