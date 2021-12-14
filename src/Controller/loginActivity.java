package Controller;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class loginActivity {
    public static void successfulLogIn() {
        Logger log = Logger.getLogger("login_activity.txt");

        try {
            FileHandler fh = new FileHandler("login_activity.txt", true);
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            log.addHandler(fh);
        } catch (IOException e) {
            Logger.getLogger(loginActivity.class.getName()).log(Level.INFO, null, e);
            Logger.getLogger(loginActivity.class.getName()).log(Level.SEVERE, null, e);}

        log.info("Successful Log In");
    }
    public static void unsuccessfulLogIn() {
        Logger log = Logger.getLogger("login_activity.txt");

        try {
            FileHandler fh = new FileHandler("login_activity.txt", true);
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            log.addHandler(fh);
        } catch (IOException e) {
            Logger.getLogger(loginActivity.class.getName()).log(Level.INFO, null, e);
            Logger.getLogger(loginActivity.class.getName()).log(Level.SEVERE, null, e);}

        log.info("Unsuccessful Log In");
    }
}
