package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    public Label userName;
    public Label password;
    public Label logIn;
    public Label location;
    public Label timeZone;
    public Button logInButton;
    ResourceBundle myRB = ResourceBundle.getBundle("Main/Language");

    Stage stage;
    Parent scene;

    @FXML
    void onActionLogIn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle rb){

                        logInButton.setText(myRB.getString("LogIn"));
                        userName.setText(myRB.getString("User"));
                        password.setText(myRB.getString("Password"));
                        logIn.setText(myRB.getString("LogIn"));
                        location.setText(myRB.getString("TimeZone"));
                        timeZone.setText(ZoneId.systemDefault().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));

    }
}


