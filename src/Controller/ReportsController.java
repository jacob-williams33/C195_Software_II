package Controller;

import Model.Apppointments;
import Model.Contacts;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;

/**This class allows the user to view multiple reports*/

public class ReportsController implements Initializable {
    @FXML
    public ComboBox appType;
    @FXML
    public ComboBox appMonth;
    @FXML
    public Label monthCount;
    @FXML
    public Label typeCount;
    @FXML
    public Label totalCount;
    @FXML
    public ComboBox<Contacts> contactsCombo;
    @FXML
    public TableView appTable;
    @FXML
    public TableColumn appID;
    @FXML
    public TableColumn appTitle;
    @FXML
    public TableColumn appDesc;
    @FXML
    public TableColumn appLoc;
    @FXML
    public TableColumn appContact;
    @FXML
    public TableColumn appTypeCOL;
    @FXML
    public TableColumn appStart;
    @FXML
    public TableColumn appEnd;
    @FXML
    public TableColumn appCustomer;
    @FXML
    public TableColumn appUser;
    @FXML
    public Label appToday;


    Stage stage;
    Parent scene;

    /**This method creates a list of appointment types
      @return types, the list of available appointment types*/

    public ObservableList<String> createTypeList() {
        ObservableList<String> types = FXCollections.observableArrayList();

        types.addAll("Initial Intake", "Planning Session", "Follow Up", "Med Check", "Brain Dump", "Process Discussion", "Debriefing", "Termination");
        return types;
    }

    /**This method creates a list of months
     @return months, all months of the year*/

    public ObservableList<Month> createMonthList() {
        ObservableList<Month> months = FXCollections.observableArrayList();
        months.addAll( Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
        return months;
    }

    /**THis method generates the appointment count by month and type
     @param event clicked*/

    public void onActionGenerate(ActionEvent event) {
        try {
            Month monthSelection = (Month) appMonth.getValue();
            Integer month = monthSelection.getValue();
            String type = (String) appType.getValue();
            if (type == null || type.isBlank()) {
                throw new Exception();
            }
                Integer count = JDBC.getAppointmentCountByMonthAndType(month, type);
                totalCount.setText(String.valueOf(count));

        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Month or Type is Blank");
            alert.setContentText("Select Type and Month");
            alert.showAndWait();
        }
    }

    /**This method returns to main menu
     @param event clicked*/

    @FXML
    void onActionMainMenuR1(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This method returns to main menu
     @param event clicked*/
    @FXML
    void onActionMainMenuR2(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**This method returns to main menu
     @param event clicked*/
    @FXML
    void onActionMainMenuR3(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This method filters appointments by contact. When a contact is selected, the appointment list is filtered
     @return filteredContactList, returns filtered list dependent on contact selected*/

    public ObservableList<Apppointments> contactFilter() {
        ObservableList<Apppointments> allAppointments= JDBC.getAllAppointments();
        FilteredList<Apppointments> filteredContactList = new FilteredList<>(allAppointments, i -> i.getContact_ID() == contactsCombo.getSelectionModel().getSelectedItem().getContact_ID());

        return filteredContactList;

    }

    /**This method filters the appointments when contact is selected
      @param event clicked */

    @FXML
    public void onActionSelectContact(ActionEvent event) {

        appTable.setItems(contactFilter());
    }
    Integer apptCount = JDBC.getAppointmentCountToday();
    appTodayInterface getAppcount = () -> appToday.setText(String.valueOf(apptCount));

    /**This appointment calls a lambda expression to get count of appointments on the current day*/

    public void appointmentsToday() {

        getAppcount.apptToday();
    }

    /**This method sets the appointment table and combo boxes*/

        @Override
        public void initialize(URL url, ResourceBundle rb){
            appType.setVisibleRowCount(5);
            appType.setPromptText("Select Appointment Type");
            appType.setItems(createTypeList());
            appMonth.setVisibleRowCount(5);
            appMonth.setPromptText("Select Appointment Month");
            appMonth.setItems(createMonthList());
            contactsCombo.setVisibleRowCount(5);
            contactsCombo.setPromptText("Select Contact");
            contactsCombo.setItems(JDBC.getAllContacts());
            appTable.setItems(JDBC.getAllAppointments());
            appID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            appTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
            appDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
            appLoc.setCellValueFactory(new PropertyValueFactory<>("Location"));
            appTypeCOL.setCellValueFactory(new PropertyValueFactory<>("Type"));
            appStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
            appEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
            appCustomer.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            appUser.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
            appContact.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
            appointmentsToday();
        }
}
