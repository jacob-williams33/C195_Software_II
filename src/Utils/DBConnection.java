package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

public class DBConnection {
    //JDBC URL Part
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String IPAddress= "//wgudb.ucertify.com/WJ06I7z";

    //JDBC URL
    private static final String jdbcURL = protocol + vendorName + IPAddress;

    //driver and connection interface reference
    public static final String MySQLJDBCDriver = "com.mysql.jdbc.Driver";
    public static Connection conn = null;

    //username
    public static final String username = "U06I7z";

    //password
    public static final String password = "53688771069";

    public static Connection startConnection()
    {
        try {
            Class.forName(MySQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection Successful");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
