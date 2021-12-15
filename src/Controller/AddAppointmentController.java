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
import java.util.ResourceBundle;
import java.util.TimeZone;

/** This class allows user to add appointments to the database.*/

public class AddAppointmentController implements Initializable {

    public TextField addAppID;
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

    /**This method creates a LocalDateTime Start object. This method combines the date selected and the start time into one object.
     @return returns localdatetime object of start date and time*/
public LocalDateTime LDTstart() {
    LocalTime startTime = startTimeCombo.getSelectionModel().getSelectedItem();
    LocalDate appointmentDate = datePicker.getValue();
    LocalDateTime LDTstart = LocalDateTime.of(appointmentDate, startTime);
    return  LDTstart;
}
    /**This method creates a LocalDateTime End object. This method combines the date selected and the end time into one object.
       @return returns localdatetime object of end date and time*/
public LocalDateTime LDTend() {
    LocalTime endTime = endTimeCombo.getSelectionModel().getSelectedItem();
    LocalDate appointmentDate = datePicker.getValue();
    LocalDateTime LDTend = LocalDateTime.of(appointmentDate, endTime);
    return LDTend;
}
/**This method creates a list of appointment types. This method creates an observable list used to populate the appointment type combobox
 @return returns observable list of appointment types*/

    public ObservableList<String> createTypeList() {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.addAll("Initial Intake", "Planning Session", "Follow Up", "Med Check", "Brain Dump", "Process Discussion", "Debriefing", "Termination");
        return types;
    }

/**this method creates a business hour range. This method creates a time range for appointments and sets them to EST to populate the combo box for time options
  @return returns time ranges for business hours*/
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
            if ((t.equals(closeBusiness) || t.isAfter(closeBusiness))) {
                break;
            }
        }
        return comboTimes;
    }
    /**This method checks multiple errors in setting an appointment. This method checks empty text fields and conflicting appointments.
       @param Appointment_ID the auto-generated appointment ID
       @return returns false if there is an error present and returns true if there are no issues*/

    public Boolean errorCheck(String Appointment_ID) {
        ObservableList<Apppointments> allAppointments = JDBC.getAllAppointments();


        if (addAppTitleTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Title Cell is Blank");
            alert.setContentText("Input Appointment Title");
            alert.showAndWait();
            return false;
        }
        if (addAppDescTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Description Cell is Blank");
            alert.setContentText("Input Appointment Description");
            alert.showAndWait();
            return false;
        }
        if (addAppLocTXT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Cell");
            alert.setHeaderText("Location Cell is Blank");
            alert.setContentText("Input Appointment Location");
            alert.showAndWait();
            return false;
        }
        if (contactsCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Contact box is Blank");
            alert.setContentText("Select Appointment Contact");
            alert.showAndWait();
            return false;
        }
        if (typeCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Type box is Blank");
            alert.setContentText("Select Appointment Type");
            alert.showAndWait();
            return false;
        }

        if (datePicker.getChronology().equals(null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Appointment Date is Blank");
            alert.setContentText("Select Appointment Date");
            alert.showAndWait();
            return false;
        }
        if (startTimeCombo.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Appointment Start Time is Blank");
            alert.setContentText("Select Appointment Start Time");
            alert.showAndWait();
            return false;
        }
        if (endTimeCombo.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Appointment End Time is Blank");
            alert.setContentText("Select Appointment End Time");
            alert.showAndWait();
            return false;
        }
        if (customerIDCombo.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank Box");
            alert.setHeaderText("Customer ID box is Blank");
            alert.setContentText("Select Customer ID for Appointment");
            alert.showAndWait();
            return false;
        }
        if (userIDCombo.getSelectionModel().isEmpty()) {
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

    /**This method saves the appointment add. When clicked, update is passed into appointment table
     @param event  clicked*/

    @FXML
    void onActionSaveAppointment(ActionEvent event) throws IOException {
    Boolean gtg = errorCheck(addAppID.getText());
    if (gtg) {

        String appointment_title = addAppTitleTXT.getText();
        String description = addAppDescTXT.getText();
        String location = addAppLocTXT.getText();
        String type = typeCombo.getValue();
        LocalDateTime start = LDTstart();
        LocalDateTime end = LDTend();
        Contacts contacts = contactsCombo.getValue();
        Customers customers = customerIDCombo.getValue();
        Users users = userIDCombo.getValue();


        JDBC.addAppointment(appointment_title, description, location, type, start, end, customers.getCustomer_ID(), users.getUser_ID(), contacts.getContact_ID());
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    }

    /**This method cancels the add process. Navigates back to appointments screen
     @param event clicked*/

    @FXML
    void onActionCancelAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

/**This method sets the combo boxes. This method takes the previous methods and populates the combo boxes necessary for adding the appointment*/
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
