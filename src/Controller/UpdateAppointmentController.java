package Controller;

import Model.Apppointments;
import Model.Contacts;
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

public class UpdateAppointmentController implements Initializable {


    Stage stage;
    Parent scene;

    @FXML
    private TextField upAppID;

    @FXML
    private TextField upAppTitle;

    @FXML
    private TextField upAppDesc;

    @FXML
    private TextField location;

    @FXML
    private ComboBox<Contacts> upAppContact;

    @FXML
    private TextField upAppType;






    @FXML
    void onActionCancelUpdateAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void sendAppointment(Apppointments apppointments) {
//        Appointment_ID = appointment_ID;
//        Title = title;
//        Description = description;
//        Location = location;
//        Type = type;
//        Start = start;
//        End = end;
//        Customer_ID = customer_ID;
//        User_ID = user_ID;
//        Contact_ID = contact_ID;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}
