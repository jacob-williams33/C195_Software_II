package Controller;


import Main.TimeZoneInterface;
import Model.Apppointments;
import Model.JDBC;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogInController implements Initializable {


    @FXML
    public Label userName;
    @FXML
    public Label password;
    @FXML
    public Label logIn;
    @FXML
    public Label location;
    @FXML
    public Label timeZone;
    @FXML
    public Button logInButton;
    @FXML
    public  TextField userNameTXT;
    @FXML
    public TextField passwordTXT;

    ResourceBundle myRB = ResourceBundle.getBundle("Main/Language");

    Stage stage;
    Parent scene;

    public void successfulLogIn() {
        Logger log = Logger.getLogger("login_activity.txt");

        try {
            FileHandler fh = new FileHandler("login_activity.txt", true);
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            log.addHandler(fh);
        } catch (IOException e) {
            Logger.getLogger(LogInController.class.getName()).log(Level.INFO, null, e);
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, e);}

        ZonedDateTime now = ZonedDateTime.now();
        log.info("User: " + userNameTXT.getText() + " Successfully Logged In At " + now + " time zone");
    }
    public void unsuccessfulLogIn() {
        Logger log = Logger.getLogger("login_activity.txt");

        try {
            FileHandler fh = new FileHandler("login_activity.txt", true);
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            log.addHandler(fh);
        } catch (IOException e) {
            Logger.getLogger(LogInController.class.getName()).log(Level.INFO, null, e);
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, e);}

        ZonedDateTime now = ZonedDateTime.now();
        log.info("User: " + userNameTXT.getText() + " Unsuccessfully Logged In At "  + now + " time zone");
    }

    @FXML
    void onActionLogIn(ActionEvent event) throws IOException {

        String  userName = userNameTXT.getText();
        String password = passwordTXT.getText();
        boolean gtg = JDBC.checkLogin(userName, password);

        if (gtg) {
            successfulLogIn();
            appointmentAlert();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else {
            unsuccessfulLogIn();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(myRB.getString("LogInError"));
            alert.setHeaderText(myRB.getString("Mismatch"));
            alert.setContentText(myRB.getString("Retry"));
            alert.showAndWait();
        }
    }

    public void appointmentAlert() {
        LocalDateTime logInTime = LocalDateTime.now();
        ObservableList<Apppointments> allAppointments = JDBC.getAllAppointments();
        for (Apppointments a : allAppointments) {
            LocalDateTime start = a.getStart();
            long timeDifference = ChronoUnit.MINUTES.between(logInTime, start);
            System.out.println(timeDifference);
            if (timeDifference > 0 && timeDifference <=15) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(myRB.getString("AppointmentAlert"));
                alert.setHeaderText(myRB.getString("Upcoming"));
                alert.setContentText(myRB.getString("Check"));
                alert.showAndWait();
                break;
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(myRB.getString("AppointmentAlert"));
                alert.setHeaderText(myRB.getString("NoUpcoming"));
                alert.setContentText(myRB.getString("Check"));
                alert.showAndWait();
                break;
            }
        }
    }
    //Lambda expression for getting TimeZone display
    TimeZoneInterface getZone = () -> timeZone.setText(ZoneId.systemDefault().toString());

    @Override
    public void initialize(URL url, ResourceBundle rb){

                        logInButton.setText(myRB.getString("LogIn"));
                        userName.setText(myRB.getString("User"));
                        password.setText(myRB.getString("Password"));
                        logIn.setText(myRB.getString("LogIn"));
                        location.setText(myRB.getString("TimeZone"));
                        getZone.timeZone();

    }
}


