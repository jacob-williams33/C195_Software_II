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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    public TextField addAppTitleTXT;
    public TextField addAppDescTXT;
    public TextField addAppLocTXT;
    public DatePicker datePicker;
    public ComboBox<LocalTime> startTimeCombo;
    public ComboBox<LocalTime> endTimeCombo;
    public ComboBox<Contacts> contactsCombo;
    public ComboBox<String> typeCombo;
    public ComboBox<Customers> customerIDCombo;
    public ComboBox<Users> userIDCombo;


    Stage stage;
    Parent scene;

public LocalDateTime LDTstart() {
    LocalTime startTime = startTimeCombo.getSelectionModel().getSelectedItem();
    LocalDate appointmentDate = datePicker.getValue();
    LocalDateTime LDTstart = LocalDateTime.of(appointmentDate, startTime);
    return  LDTstart;
}
public LocalDateTime LDTend() {
    LocalTime endTime = endTimeCombo.getSelectionModel().getSelectedItem();
    LocalDate appointmentDate = datePicker.getValue();
    LocalDateTime LDTend = LocalDateTime.of(appointmentDate, endTime);
    return LDTend;
}

    public ObservableList<String> createTypeList() {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.addAll("Initial Intake", "Planning Session", "Follow Up", "Med Check", "Brain Dump", "Process Discussion", "Debriefing", "Termination");
        return types;
    }


    public ObservableList<LocalTime> timeRanges() {
        ObservableList<LocalTime> comboTimes = FXCollections.observableArrayList();
        LocalTime openBusinessEST = LocalTime.of(8, 0);
        LocalTime closeBusinessEST = LocalTime.of(22, 0);
        LocalTime openBusiness = openBusinessEST;
        LocalTime closeBusiness = closeBusinessEST;
        LocalTime t = openBusiness;
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


    @FXML
    void onActionSaveAppointment(ActionEvent event) throws IOException {
        String appointment_title= addAppTitleTXT.getText();
        String description = addAppDescTXT.getText();
        String location = addAppLocTXT.getText();
        String type = typeCombo.getValue();
        LocalDateTime start = LDTstart();
        LocalDateTime end = LDTend();
        Contacts contacts = contactsCombo.getValue();
        Customers customers = customerIDCombo.getValue();
        Users users = userIDCombo.getValue();


        JDBC.addAppointment(appointment_title, description, location, type, start, end, customers.getCustomer_ID(), users.getUser_ID(), contacts.getContact_ID());
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

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
        startTimeCombo.setVisibleRowCount(5);
        startTimeCombo.setPromptText("Select Start Time");
        startTimeCombo.setItems(timeRanges());
        endTimeCombo.setVisibleRowCount(5);
        endTimeCombo.setPromptText("Select End Time");
        endTimeCombo.setItems(timeRanges());
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
