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

/**This class allows the user to update appointments*/

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

    /**This method creates a localdatetime start object. Takes the date and start time and combines into one object
     @return returns localdatetime start object*/

    public LocalDateTime LDTstart() {
        LocalTime startTime = upAppStart.getSelectionModel().getSelectedItem();
        LocalDate appointmentDate = upAppDate.getValue();
        LocalDateTime LDTstart = LocalDateTime.of(appointmentDate, startTime);
        return  LDTstart;
    }
    /**This method creates a localdatetime ened object. Takes the date and end time and combines into one object
     @return returns localdatetime end object*/
    public LocalDateTime LDTend() {
        LocalTime endTime = upAppEnd.getSelectionModel().getSelectedItem();
        LocalDate appointmentDate = upAppDate.getValue();
        LocalDateTime LDTend = LocalDateTime.of(appointmentDate, endTime);
        return LDTend;
    }
/**This method creates a list of appointment types.
  @return returns type list*/
    public ObservableList<String> createTypeList() {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.addAll("Initial Intake", "Planning Session", "Follow Up", "Med Check", "Brain Dump", "Process Discussion", "Debriefing", "Termination");
        return types;
    }

    /**This method creates business hours. Method takes range of hours in EST for available time options
     @return returns combo box with business hours*/

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

/**This method populates the text fields and combo boxes with a selected appointment. Takes the selected appointment from the appointments table and populates.
 @param apppointments selected appointment from appointments table*/
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

    /**This method checks for errors. Looks for appointment conflicts and blank cells
     @param Appointment_ID passes in appointment ID of selected appointment
     @return returns true if no errors, false with error alert*/

    public Boolean errorCheck(String Appointment_ID) {
        ObservableList<Apppointments> allAppointments = JDBC.getAllAppointments();

        if (upAppTitle.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Title Cell is Blank");
            alert.setContentText("Input Appointment Title");
            alert.showAndWait();
            return false;
        }
        if (upAppDesc.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Description Cell is Blank");
            alert.setContentText("Input Appointment Description");
            alert.showAndWait();
            return false;
        }
        if (upAppLocation.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Location Cell is Blank");
            alert.setContentText("Input Appointment Location");
            alert.showAndWait();
            return false;
        }
        if (upAppContact.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Contact box is Blank");
            alert.setContentText("Select Appointment Contact");
            alert.showAndWait();
            return false;
        }
        if (upAppType.getSelectionModel().isEmpty())  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Type box is Blank");
            alert.setContentText("Select Appointment Type");
            alert.showAndWait();
            return false;
        }

        if (upAppDate.getChronology().equals(null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Appointment Date is Blank");
            alert.setContentText("Select Appointment Date");
            alert.showAndWait();
            return false;
        }
        if (upAppStart.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Appointment Start Time is Blank");
            alert.setContentText("Select Appointment Start Time");
            alert.showAndWait();
            return false;
        }
        if (upAppEnd.getSelectionModel().isEmpty())  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Appointment End Time is Blank");
            alert.setContentText("Select Appointment End Time");
            alert.showAndWait();
            return false;
        }
        if (upAppCustomerID.getSelectionModel().isEmpty())  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Customer ID box is Blank");
            alert.setContentText("Select Customer ID for Appointment");
            alert.showAndWait();
            return false;
        }
        if (upAppUserID.getSelectionModel().isEmpty())  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("User ID box is Blank");
            alert.setContentText("Select User ID");
            alert.showAndWait();
            return false;
        }

        if (LDTstart().isAfter(LDTend())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Start Time Cannot Be Before End Time");
            alert.setContentText("Select New Time");
            alert.showAndWait();
            return false;
        }
        if (LDTend().isBefore(LDTstart())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("End Time Cannot Be Before Start Time");
            alert.setContentText("Select New Time");
            alert.showAndWait();
            return false;
        }

        for (Apppointments a : allAppointments) {


            LocalDateTime convertedStart = a.getStart();
            LocalDateTime ZDTstart = LDTstart();
            LocalDateTime convertedEnd = a.getEnd();
            LocalDateTime ZDTend = LDTend();

            if (convertedStart.isAfter(ZDTstart)
                    && convertedStart.isBefore(ZDTend)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning: 1");
                alert.setHeaderText("Appointment Conflicts With Another Appointment");
                alert.setContentText("Select New Time");
                alert.showAndWait();
                return false;
            }
            if (convertedStart.isEqual(ZDTstart)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning: 2");
                alert.setHeaderText("Appointment Conflicts With Another Appointment");
                alert.setContentText("Select New Time");
                alert.showAndWait();
                return false;
            }
            if (convertedEnd.isAfter(ZDTstart)
                    && convertedEnd.isBefore(ZDTend)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning: 3");
                alert.setHeaderText("Appointment Conflicts With Another Appointment");
                alert.setContentText("Select New Time");
                alert.showAndWait();
                return false;
            }
            if (convertedEnd.isEqual(ZDTend)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning: 4");
                alert.setHeaderText("Appointment Conflicts With Another Appointment");
                alert.setContentText("Select New Time");
                alert.showAndWait();
                return false;
            }
            if (convertedStart.isBefore(ZDTstart)
                    && convertedEnd.isAfter(ZDTend)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning: 5");
                alert.setHeaderText("Appointment Conflicts With Another Appointment");
                alert.setContentText("Select New Time");
                alert.showAndWait();
                return false;
            }
        }

        return true;
    }

/**This method saves the appointment update. Saves the appointment and returns to appointments table
 @param event click save*/

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

    }

    /**This method cancels the action. Returns to appointment screen with clicked
     @param event clicked button*/

    @FXML
    void onActionCancelUpdateAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

/**This method sets the cells and comboboxes. Takes selected appointment and populates.*/

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
