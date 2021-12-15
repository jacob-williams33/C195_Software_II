package Model;

/**This class controls the customer objects*/

public class Customers {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private int Division_ID;
    private int Country_ID;

/**Constructor for Customers
 @param customer_ID ID of customer
 @param customer_Name name of customer
 @param address address of customer
 @param postal_Code zip code or postal code of customer
 @param phone phone number for customer
 @param division_ID state or province or county of customer
 @param country_ID country of customer*/

    public Customers(int customer_ID, String customer_Name, String address, String postal_Code, String phone, int division_ID, int country_ID) {
        Customer_ID = customer_ID;
        Customer_Name = customer_Name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Division_ID = division_ID;
        Country_ID = country_ID;

    }

    /**Method of getting customer ID
     @return returns Customer ID*/

    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**Method of getting customer name
     @return returns Customer name*/

    public String getCustomer_Name() {
        return Customer_Name;
    }

    /**Method of getting customer address
     @return returns Customer address*/

    public String getAddress() {
        return Address;
    }

    /**Method of getting customer postal code
     @return returns Customer postal code*/

    public String getPostal_Code() {
        return Postal_Code;
    }

    /**Method of getting customer phone number
     @return returns Customer phone number*/

    public String getPhone() {
        return Phone;
    }

    /**Method of getting customer division
     @return returns Customer division*/

    public int getDivision_ID() {
        return Division_ID;
    }

    /**Method of getting country ID
     @return returns Customer country */

    public int getCountry_ID() {
        return Country_ID;
    }

    /**method to override toString to populate customer ID combo boxes*/

    @Override
    public String toString() {
        return String.valueOf(Customer_ID);
    }


}
