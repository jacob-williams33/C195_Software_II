package Model;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import java.sql.*;

public class JDBC {
 private static final String protocol = "jdbc";
     private static final String vendor = ":mysql:";
         private static final String location = "//localhost/";
             private static final String databaseName = "client_schedule";
                 private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
        private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
        private static final String userName = "sqlUser"; // Username
        private static String password = "Passw0rd!"; // Password
        private static Connection connection = null;  // Connection Interface
        private static PreparedStatement preparedStatement;

         public static void makeConnection() {

          try {
              Class.forName(driver); // Locate Driver
              //password = Details.getPassword(); // Assign password
              connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
              System.out.println("Connection successful!");
          }
                  catch(ClassNotFoundException e) {
                      System.out.println("Error:" + e.getMessage());
                  }
                  catch(SQLException e) {
                      System.out.println("Error:" + e.getMessage());
                  }
          }

            public static Connection getConnection() {
                return connection;
            }
             public static void closeConnection() {
                 try {
                     connection.close();
                     System.out.println("Connection closed!");
                 } catch (SQLException e) {
                     System.out.println(e.getMessage());
                 }
             }

       public static void makePreparedStatement(String sqlStatement, Connection conn) throws SQLException {
           if (conn != null)
               preparedStatement = conn.prepareStatement(sqlStatement);
           else
               System.out.println("Prepared Statement Creation Failed!");
       }
       public static PreparedStatement getPreparedStatement() throws SQLException {
           if (preparedStatement != null)
               return preparedStatement;
           else System.out.println("Null reference to Prepared Statement");
           return null;
       }
        public static ObservableList<Customers> getAllCustomers() {
             ObservableList<Customers> cList = FXCollections.observableArrayList();
             try {
                 String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers";
                 PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
                 ResultSet rs = ps.executeQuery();

                 while(rs.next()) {
                     int Customer_ID = rs.getInt("Customer_ID");
                     String Customer_Name = rs.getString("Customer_Name");
                     String Address = rs.getString("Address");
                     String Postal_Code = rs.getString("Postal_Code");
                     String Phone = rs.getString("Phone");
                     int Division_ID = rs.getInt("Division_ID");
                     Customers c = new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID);
                     cList.add(c);
                 }
             }
             catch (SQLException ex) {
                 ex.printStackTrace();
             }

             return cList;
        }



}