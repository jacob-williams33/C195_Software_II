package Model;

public class Customers {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private int Division_ID;

    public Customers(int customer_ID, String customer_Name, String address, String postal_Code, String phone, int division_ID) {
        Customer_ID = customer_ID;
        Customer_Name = customer_Name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Division_ID = division_ID;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPostal_Code() {
        return Postal_Code;
    }

    public String getPhone() {
        return Phone;
    }

    public int getDivision_ID() {
        return Division_ID;
    }
}
