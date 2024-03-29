package Main;

import Model.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**Main class to start program*/
public class Main extends Application {

    /**Sets scene of program
     @param primaryStage first window*/

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/LogIn.fxml"));
        primaryStage.setTitle("Jake's Magical Scheduling Assistant");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**This method sets the connection, runs the program, and closes connection upon exit*/

    public static void main(String[] args) {
        JDBC.makeConnection();

        launch(args);

        JDBC.closeConnection();
    }

}
