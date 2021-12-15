package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**This class controls the database connection and queries*/

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

        /**Method to start connection to database*/

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

    /**Method gets all appointments in database.
     @return returns list of all appointments*/

    public static ObservableList<Apppointments> getAllAppointments() {
        ObservableList<Apppointments> aList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID FROM appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int Appointment_ID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Type = rs.getString("Type");
                LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
                int Customer_ID = rs.getInt("Customer_ID");
                int User_ID = rs.getInt("User_ID");
                int Contact_ID = rs.getInt("Contact_ID");
                Apppointments a = new Apppointments(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID);
                aList.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return aList;

    }

    /**Method gets appointments filtered by month.
     @return returns list of appointments filtered by month*/

    public static ObservableList<Apppointments> getAppointmentsByMonth() {
        ObservableList<Apppointments> aList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(current_date())";
            PreparedStatement psam = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = psam.executeQuery();

            while (rs.next()) {
                int Appointment_ID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Type = rs.getString("Type");
                LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
                int Customer_ID = rs.getInt("Customer_ID");
                int User_ID = rs.getInt("User_ID");
                int Contact_ID = rs.getInt("Contact_ID");
                Apppointments a = new Apppointments(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID);
                aList.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return aList;
    }

    /**Method gets list of appointments filtered by week.
     @return returns list of filtered appointments by week*/

    public static ObservableList<Apppointments> getAppointmentsByWeek() {
        ObservableList<Apppointments> aList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments WHERE WEEK(Start) = WEEK(current_date())";
            PreparedStatement psaw = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = psaw.executeQuery();

            while (rs.next()) {
                int Appointment_ID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Type = rs.getString("Type");
                LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
                int Customer_ID = rs.getInt("Customer_ID");
                int User_ID = rs.getInt("User_ID");
                int Contact_ID = rs.getInt("Contact_ID");
                Apppointments a = new Apppointments(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID);
                aList.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return aList;
    }

    /**Method to get number of appointments by type.
     @param type type of appointment
     @return number of appointments*/

    public static Integer getAppointmentCountByType(String type) {

        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM appointments WHERE Type=(?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt("COUNT(*)");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    /**Method to get number of appointments by month.
     @param month month of appointment
     @return number of appointments*/

    public static Integer getAppointmentCountByMonth(Integer month) {

        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM appointments WHERE MONTH(Start) = (?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt("COUNT(*)");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    /**Method to get number of appointments by type and month.
     @param type type of appointment
     @param month month of appointment
     @return number of appointments*/

    public static Integer getAppointmentCountByMonthAndType(Integer month, String type) {

        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM appointments WHERE Type = ? AND MONTH(Start) = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, type);
            ps.setInt(2, month);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt("COUNT(*)");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    /**Method to get number of appointments today.
     @return number of appointments*/

    public static Integer getAppointmentCountToday() {

        int count = 0;
        try {
            LocalDate ld = LocalDate.now();
            System.out.println(ld);
            String sql = "SELECT Start FROM appointments ";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               LocalDate d = rs.getTimestamp("Start").toLocalDateTime().toLocalDate();
                if (d.equals(ld)) {
                    count++;
                }


            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    /**Method to get list of all customers
     @return customer list*/

    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> cList = FXCollections.observableArrayList();
        try {
            String sqlgetallcustomers = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, Country_ID FROM customers, first_level_divisions WHERE customers.Division_ID = first_level_divisions.Division_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlgetallcustomers);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int Customer_ID = rs.getInt("Customer_ID");
                String Customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Postal_Code = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                int Division_ID = rs.getInt("Division_ID");
                int Country_ID = rs.getInt("Country_ID");
                Customers c = new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID, Country_ID);
                cList.add(c);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cList;

    }

    /**Method to get all divisions in database
     @return list of division*/

    public static ObservableList<Divisions> getAllDivisions() {
        ObservableList<Divisions> dList = FXCollections.observableArrayList();
        try {
            String sqlgetalldivisions = "SELECT Division_ID, Division, Country_ID FROM first_level_divisions";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlgetalldivisions);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int Division_ID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                int Country_ID = rs.getInt("Country_ID");
                Divisions d = new Divisions(Division_ID, Division, Country_ID);
                dList.add(d);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return dList;

    }

    /**Method to get all contacts in database
     @return list of contacts*/

    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> coList = FXCollections.observableArrayList();
        try {
            String sqlgetallcontacts = "SELECT Contact_ID, Contact_Name FROM contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlgetallcontacts);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int Contact_ID = rs.getInt("Contact_ID");
                String Contact_Name = rs.getString("Contact_Name");
                Contacts co = new Contacts(Contact_ID, Contact_Name);
                coList.add(co);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return coList;

    }

    /**Method to get all countries in database
     @return list of countries*/

    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> ctryList = FXCollections.observableArrayList();
        try {
            String sqlgetallcountries = "SELECT Country_ID, Country FROM countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlgetallcountries);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int Country_ID = rs.getInt("Country_ID");
                String Country = rs.getString("Country");
                Countries ctry = new Countries(Country_ID, Country);
                ctryList.add(ctry);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ctryList;

    }

    /**Method to get all users in database
     @return list of users*/

    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> uList = FXCollections.observableArrayList();
        try {
            String sqlgetallusers = "SELECT User_ID, User_Name, Password FROM users";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlgetallusers);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int User_ID = rs.getInt("User_ID");
                String User_Name = rs.getString("User_Name");
                String Password = rs.getString("Password");
                Users u  = new Users(User_ID, User_Name, Password);
                uList.add(u);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return uList;

    }

    /**Method to check log in against user names and passwords in database
     @param userName username from log in screen
     @param password password from log in screen
     @return true if user name and password match, false if not*/

    public static Boolean checkLogin(String userName, String password) {

        Integer count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT(*)");
            }
            if (count != 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    /**Method to add customer to database.
     @param customer_Name name of customer
     @param address address of customer
     @param postal_Code postal code of customer
     @param phone phone number of customer
     @param division_ID division of customer
     */
    public static void addCustomer(String customer_Name, String address, String postal_Code, String phone, Integer division_ID)
     {
        try {

            String sqladdcustomer = "INSERT INTO customers VALUES (NULL, ?, ?, ?, ?, now(), 'user', now(), 'user', ?)";
            PreparedStatement psac = JDBC.getConnection().prepareStatement(sqladdcustomer);
            psac.setString(1, customer_Name);
            psac.setString(2, address);
            psac.setString(3, postal_Code);
            psac.setString(4, phone);
            psac.setInt(5, division_ID);
            psac.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**Method to update customer to database.
     @param customer_Name name of customer
     @param address address of customer
     @param postal_Code postal code of customer
     @param phone phone number of customer
     @param division_ID division of customer
     */

    public static void updateCustomer(String customer_Name, String address, String postal_Code, String phone, Integer division_ID, String customer_ID)
    {
        try {

            String sqlupdatecustomer = "UPDATE customers SET  Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";
            PreparedStatement psuc = JDBC.getConnection().prepareStatement(sqlupdatecustomer);
            psuc.setString(1, customer_Name);
            psuc.setString(2, address);
            psuc.setString(3, postal_Code);
            psuc.setString(4, phone);
            psuc.setInt(5, division_ID);
            psuc.setString(6, customer_ID);
            psuc.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**Method to delete customer from database
     @param Customer_ID ID of customer*/

    public static void deleteCustomer(int Customer_ID)
    {
        try {
            String sqldeleteappointment = "DELETE FROM appointments WHERE Customer_ID = (?)";
            String sqldeletecustomerr = "DELETE FROM customers WHERE Customer_ID = (?)";
            PreparedStatement psda = JDBC.getConnection().prepareStatement(sqldeleteappointment);
            PreparedStatement psdc = JDBC.getConnection().prepareStatement(sqldeletecustomerr);
            psda.setInt(1, Customer_ID);
            psdc.setInt(1, Customer_ID);
            psda.execute();
            psdc.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**Method to add an appointment to the database
     @param title title of appointment
     @param description description of appointment
     @param location location of appointment
     @param type type of appointment
     @param start start time of appointment
     @param end end time of appointment
     @param customer_ID ID of customer for appointment
     @param user_ID ID of user who created the appointment
     @param contact_ID ID of contact for appointment*/

    public static void addAppointment(String title, String description, String location,
                                      String type, LocalDateTime start, LocalDateTime end,
                                      Integer customer_ID, Integer user_ID, Integer contact_ID)
    {
        try {

            String sqladdappointment = "INSERT INTO appointments VALUES (NULL, ?, ?, ?, ?, ?, ?, now(), 'user', now(), 'user', ?, ?, ?)";
            PreparedStatement psaa  = JDBC.getConnection().prepareStatement(sqladdappointment);
            psaa.setString(1, title);
            psaa.setString(2, description);
            psaa.setString(3, location);
            psaa.setString(4, type);
            psaa.setTimestamp(5, Timestamp.valueOf(start));
            psaa.setTimestamp(6, Timestamp.valueOf(end));
            psaa.setInt(7, customer_ID);
            psaa.setInt(8, user_ID);
            psaa.setInt(9, contact_ID);
            psaa.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**Method to update an appointment to the database
     @param title title of appointment
     @param description description of appointment
     @param location location of appointment
     @param type type of appointment
     @param start start time of appointment
     @param end end time of appointment
     @param customer_ID ID of customer for appointment
     @param user_ID ID of user who created the appointment
     @param contact_ID ID of contact for appointment*/

    public static void updateAppointment(String title, String description, String location,
                                         String type, LocalDateTime start, LocalDateTime end,
                                         Integer customer_ID, Integer user_ID, Integer contact_ID, String appointment_ID)
    {
        try {

            String sqlupdateappointment = "UPDATE appointments " +
                    "SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, " +
                    "Customer_ID=?, User_ID=?, Contact_ID=? WHERE Appointment_ID=?";

            PreparedStatement psua  = JDBC.getConnection().prepareStatement(sqlupdateappointment);
            psua.setString(1, title);
            psua.setString(2, description);
            psua.setString(3, location);
            psua.setString(4, type);
            psua.setTimestamp(5, Timestamp.valueOf(start));
            psua.setTimestamp(6, Timestamp.valueOf(end));
            psua.setInt(7, customer_ID);
            psua.setInt(8, user_ID);
            psua.setInt(9, contact_ID);
            psua.setString(10, appointment_ID);
            psua.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**Method to delete appointment
     @param Appointment_ID ID for appointment*/

    public static void deleteAppointment(int Appointment_ID)
    {
        try {

            String sqldeleteappointment = "DELETE FROM appointments WHERE Appointment_ID = (?)";
            PreparedStatement psda = JDBC.getConnection().prepareStatement(sqldeleteappointment);
            psda.setInt(1, Appointment_ID);
            psda.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}