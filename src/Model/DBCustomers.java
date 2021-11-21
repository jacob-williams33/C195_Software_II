package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCustomers {
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> cList = FXCollections.observableArrayList();
        try {
            String sqlgetallcustomers = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlgetallcustomers);
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
    public static void addCustomer(String customer_Name, String address, String postal_Code, String phone, int division_ID
    ) {
        try {
            String sqladdcustomer = "INSERT INTO customers VALUES (NULL, ?, ?, ?, ?, ?)";
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
}
