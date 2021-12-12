package Controller;

import Model.JDBC;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    @FXML
    public ComboBox appType;
    @FXML
    public ComboBox appMonth;
    @FXML
    public Label countLabel;

    Stage stage;
    Parent scene;

    public ObservableList<String> createTypeList() {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.addAll("Initial Intake", "Planning Session", "Follow Up", "Med Check", "Brain Dump", "Process Discussion", "Debriefing", "Termination");
        return types;
    }
    public ObservableList<Month> createMonthList() {
        ObservableList<Month> months = FXCollections.observableArrayList();
        months.addAll(Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
        return months;
    }
    @FXML
    public Integer onActionSelectType(ActionEvent event) throws IOException {
        String type = (String) appType.getValue();
        Integer count = JDBC.getAppointmentCountByType(type);

        return count;
    }

    @FXML
    public Integer onActionSelectMonth(ActionEvent event) throws IOException {
        Month monthSelection = (Month) appMonth.getValue();
        Integer month = monthSelection.getValue();
        Integer count = JDBC.getAppointmentCountByMonth(month);

        return count;
    }





    @FXML
    void onActionMainMenuR1(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void onActionMainMenuR2(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void onActionMainMenuR3(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

        @Override
        public void initialize(URL url, ResourceBundle rb){
            appType.setVisibleRowCount(5);
            appType.setPromptText("Select Appointment Type");
            appType.setItems(createTypeList());
            appMonth.setVisibleRowCount(5);
            appMonth.setPromptText("Select Appointment Month");
            appMonth.setItems(createMonthList());

        }
}
