package Controller;

import Model.Apppointments;
import Model.JDBC;
import javafx.collections.ObservableList;
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

public class AppointmentsController implements Initializable {
    Stage stage;
    Parent scene;

    ObservableList<Apppointments> appointments;

    @FXML
    public TableView<Apppointments> appointmentsTable;

    @FXML
    public TableColumn<Apppointments, Integer> apptIDCOL;

    @FXML
    public TableColumn<Apppointments, String> titleCOL;

    @FXML
    public TableColumn<Apppointments, String> descriptionCOL;

    @FXML
    public TableColumn<Apppointments, String> locationCOL;

    @FXML
    public TableColumn<Apppointments, String> typeCOL;

    @FXML
    public TableColumn<Apppointments, String> startCOL;

    @FXML
    public TableColumn<Apppointments, String> endCOL;

    @FXML
    public TableColumn<Apppointments, Integer> customerCOL;

    @FXML
    public TableColumn<Apppointments, Integer> userCOL;

    @FXML
    public TableColumn<Apppointments, Integer> contactCOL;

    @FXML
    public RadioButton allAppButton;

    @FXML
    public RadioButton appByMonthButton;

    @FXML
    public RadioButton appByWeekButton;

    @FXML
    public void onActionAppointmentFilters(ActionEvent event) throws IOException {
        if (allAppButton.isSelected()) {
            appointments = JDBC.getAllAppointments();
            appointmentsTable.setItems(appointments);
        }
        else if (appByMonthButton.isSelected()) {
            appointments = JDBC.getAppointmentsByMonth();
            appointmentsTable.setItems(appointments);
        }
        else if (appByWeekButton.isSelected()) {
            appointments = JDBC.getAppointmentsByMonth();
            appointmentsTable.setItems(appointments);
        }
    }

    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/UpdateAppointment.fxml"));
            loader.load();

            UpdateAppointmentController upAppCont = loader.getController();
            upAppCont.populateSelectedAppointment(appointmentsTable.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {

        }
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("This will permanently delete the selected appointment");
        alert.setContentText("Do you wish to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            JDBC.deleteAppointment(appointmentsTable.getSelectionModel().getSelectedItem().getAppointment_ID());
        }
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void onActionMainMenuA(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void onActionLogOutA(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/LogIn.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){

        allAppButton.setSelected(true);
        allAppButton.requestFocus();
        appointmentsTable.setItems(JDBC.getAllAppointments());
        apptIDCOL.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleCOL.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descriptionCOL.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locationCOL.setCellValueFactory(new PropertyValueFactory<>("Location"));
        typeCOL.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startCOL.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCOL.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerCOL.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        userCOL.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        contactCOL.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));

    }
}
