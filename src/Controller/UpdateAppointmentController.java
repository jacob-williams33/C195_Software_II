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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class UpdateAppointmentController implements Initializable {



    Stage stage;
    Parent scene;

    @FXML
    public TextField upAppID;

    @FXML
    public TextField upAppTitle;

    @FXML
    public TextField upAppDesc;

    @FXML
    public TextField upAppLocation;

    @FXML
    public ComboBox<Contacts> upAppContact;

    @FXML
    public ComboBox<String> upAppType;

    @FXML
    public DatePicker upAppDate;

    @FXML
    public ComboBox<LocalTime> upAppStart;

    @FXML
    public ComboBox<LocalTime> upAppEnd;

    @FXML
    public ComboBox<Customers> upAppCustomerID;

    @FXML
    public ComboBox<Users> upAppUserID;

    public LocalDateTime LDTstart() {
        LocalTime startTime = upAppStart.getSelectionModel().getSelectedItem();
        LocalDate appointmentDate = upAppDate.getValue();
        LocalDateTime LDTstart = LocalDateTime.of(appointmentDate, startTime);
        return  LDTstart;
    }
    public LocalDateTime LDTend() {
        LocalTime endTime = upAppEnd.getSelectionModel().getSelectedItem();
        LocalDate appointmentDate = upAppDate.getValue();
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

        ZoneId EST = ZoneId.of("America/New_York");
        ZoneId localZDT = ZoneId.of(TimeZone.getDefault().getID());

        ZonedDateTime openZDT = ZonedDateTime.of(LocalDate.now(), openBusinessEST, EST);
        ZonedDateTime closeZDT = ZonedDateTime.of(LocalDate.now(), closeBusinessEST, EST);

        ZonedDateTime openBusiness = openZDT.withZoneSameInstant(localZDT);
        ZonedDateTime closeBusiness = closeZDT.withZoneSameInstant(localZDT);


        ZonedDateTime t = openBusiness.minusMinutes(30);

        Boolean inRange = t.isBefore(closeBusiness);
        while (inRange = true) {
            t = t.plusMinutes(30);
            comboTimes.add(LocalTime.from(t));
            if (!(!t.equals(closeBusiness) || t.isAfter(closeBusiness))) {
                break;
            }
        }
        return comboTimes;
    }


    public void populateSelectedAppointment(Apppointments apppointments) {


        upAppID.setText(String.valueOf(apppointments.getAppointment_ID()));
        upAppTitle.setText(apppointments.getTitle());
        upAppDesc.setText(apppointments.getDescription());
        upAppLocation.setText(apppointments.getLocation());

        for (Contacts c : upAppContact.getItems()) {
            if (apppointments.getContact_ID() == c.getContact_ID()) {
                upAppContact.setValue(c);
                break;
            }
        }
        upAppType.setValue(apppointments.getType());
        upAppDate.setValue(apppointments.getStart().toLocalDate());
        upAppStart.setValue(apppointments.getStart().toLocalTime());
        upAppEnd.setValue(apppointments.getEnd().toLocalTime());

        for (Customers customers : upAppCustomerID.getItems()) {
            if (apppointments.getCustomer_ID() == customers.getCustomer_ID()) {
                upAppCustomerID.setValue(customers);
                break;
            }
        }

        for (Users users : upAppUserID.getItems()) {
            if (apppointments.getUser_ID() == users.getUser_ID()){
                upAppUserID.setValue(users);
                break;
            }
        }

    }

    public Boolean errorCheck(String Appointment_ID) {
        ObservableList<Apppointments> allAppointments = JDBC.getAllAppointments();

        if (LDTstart().isAfter(LDTend())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Start Time Cannot Be Before End Time");
            alert.setContentText("Select New Time");
            alert.showAndWait();
            return false;
        }
        if (LDTend().isBefore(LDTstart())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("End Time Cannot Be Before Start Time");
            alert.setContentText("Select New Time");
            alert.showAndWait();
            return false;
        }

        for (Apppointments a : allAppointments) {

//            ZoneId localZDT = ZoneId.of(TimeZone.getDefault().getID());
//            ZonedDateTime convertedStart = a.getStart().atZone(localZDT);
//            ZonedDateTime convertedEnd = a.getEnd().atZone(localZDT);
//
//            ZonedDateTime ZDTstart = LDTstart().atZone(localZDT);
//            ZonedDateTime ZDTend = LDTend().atZone(localZDT);
            LocalDateTime convertedStart = a.getStart();
            LocalDateTime ZDTstart = LDTstart();
            LocalDateTime convertedEnd = a.getEnd();
            LocalDateTime ZDTend = LDTend();

            if (convertedStart.isAfter(ZDTstart)
                    && convertedStart.isBefore(ZDTend)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning: 1");
                alert.setHeaderText("Appointment Conflicts With Another Appointment");
                alert.setContentText("Select New Time");
                alert.showAndWait();
                return false;
            }
            if (convertedStart.isEqual(ZDTstart)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning: 2");
                alert.setHeaderText("Appointment Conflicts With Another Appointment");
                alert.setContentText("Select New Time");
                alert.showAndWait();
                return false;
            }
            if (convertedEnd.isAfter(ZDTstart)
                    && convertedEnd.isBefore(ZDTend)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning: 3");
                alert.setHeaderText("Appointment Conflicts With Another Appointment");
                alert.setContentText("Select New Time");
                alert.showAndWait();
                return false;
            }
            if (convertedEnd.isEqual(ZDTend)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning: 4");
                alert.setHeaderText("Appointment Conflicts With Another Appointment");
                alert.setContentText("Select New Time");
                alert.showAndWait();
                return false;
            }
            if (convertedStart.isBefore(ZDTstart)
                    && convertedEnd.isAfter(ZDTend)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning: 5");
                alert.setHeaderText("Appointment Conflicts With Another Appointment");
                alert.setContentText("Select New Time");
                alert.showAndWait();
                return false;
            }


        }



        System.out.println("Good to Go");
        return true;
    }


    @FXML
    void onActionSaveAppointment(ActionEvent event) throws IOException {
        boolean gtg = errorCheck(upAppID.getText());
        if (gtg) {

            String appointment_title = upAppTitle.getText();
            String description = upAppDesc.getText();
            String location = upAppLocation.getText();
            String type = upAppType.getValue();
            LocalDateTime start = LDTstart();
            LocalDateTime end = LDTend();
            Contacts contacts = upAppContact.getValue();
            Customers customers = upAppCustomerID.getValue();
            Users users = upAppUserID.getValue();
            String appointments = upAppID.getText();

            LocalTime openBusinessEST = LocalTime.of(8, 0);
            LocalTime closeBusinessEST = LocalTime.of(22, 0);

            ZoneId EST = ZoneId.of("America/New_York");
            ZoneId localZDT = ZoneId.of(TimeZone.getDefault().getID());

            ZonedDateTime openZDT = ZonedDateTime.of(LocalDate.now(), openBusinessEST, EST);
            ZonedDateTime closeZDT = ZonedDateTime.of(LocalDate.now(), closeBusinessEST, EST);

            ZonedDateTime openBusiness = openZDT.withZoneSameInstant(localZDT);
            ZonedDateTime closeBusiness = closeZDT.withZoneSameInstant(localZDT);


            JDBC.updateAppointment(appointment_title, description, location, type, start, end, customers.getCustomer_ID(), users.getUser_ID(), contacts.getContact_ID(), appointments);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else {
            System.out.println("Broken");
        }
    }

    @FXML
    void onActionCancelUpdateAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle rb){
        upAppType.setVisibleRowCount(5);
        upAppType.setPromptText("Select Appointment Type");
        upAppType.setItems(createTypeList());
        upAppStart.setVisibleRowCount(5);
        upAppStart.setPromptText("Select Start Time");
        upAppStart.setItems(timeRanges());
        upAppEnd.setVisibleRowCount(5);
        upAppEnd.setPromptText("Select End Time");
        upAppEnd.setItems(timeRanges());
        upAppContact.setVisibleRowCount(5);
        upAppContact.setPromptText("Select Contact...");
        upAppContact.setItems(JDBC.getAllContacts());
        upAppCustomerID.setVisibleRowCount(5);
        upAppCustomerID.setPromptText("Select Customer");
        upAppCustomerID.setItems(JDBC.getAllCustomers());
        upAppUserID.setVisibleRowCount(5);
        upAppUserID.setPromptText("Select User");
        upAppUserID.setItems(JDBC.getAllUsers());

    }
}
