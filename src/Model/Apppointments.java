package Model;

import java.time.LocalDateTime;

/**This class controls appointment objects*/

public class Apppointments {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private Integer Customer_ID;
    private Integer User_ID;
    private Integer Contact_ID;

    /**This method is a constructor for the appointment objects
     @param appointment_ID integer appointment ID
     @param title name of appointment
     @param description summary of appointment
     @param location where the appointment is
     @param type what kind of appointment
     @param start beginning of appointment
     @param end end of appointment
     @param customer_ID ID for customer of appointment
     @param user_ID ID of user logged into the system
     @param contact_ID ID for appointment contact*/

    public Apppointments(int appointment_ID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, Integer customer_ID, Integer user_ID, Integer contact_ID) {
        Appointment_ID = appointment_ID;
        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        Customer_ID = customer_ID;
        User_ID = user_ID;
        Contact_ID = contact_ID;
    }

    /**Method to get Appointment ID
     @return returns appointment ID*/

    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /**Method to get Title
     @return returns appointment title*/

    public String getTitle() {
        return Title;
    }

    /**Method to get Appointment descrption
     @return returns appointment description*/

    public String getDescription() {
        return Description;
    }

    /**Method to get Appointment location
     @return returns where appointment is*/

    public String getLocation() {
        return Location;
    }

    /**Method to get Appointment Type
     @return returns type of appointment*/

    public String getType() {
        return Type;
    }

    /**Method to get Appointment Start Time
     @return returns when appointment begins*/

    public LocalDateTime getStart() {
        return Start;
    }

    /**Method to get Appointment End
     @return returns when appointment ends*/

    public LocalDateTime getEnd() {
        return End;
    }

    /**Method to get Customer_ID
     @return returns customer ID for appointment*/

    public Integer getCustomer_ID() {
        return Customer_ID;
    }

    /**Method to get User_ID
     @return returns User ID for appointment*/

    public Integer getUser_ID() {
        return User_ID;
    }

    /**Method to get Contact_ID
     @return returns Contact ID for appointment*/

    public Integer getContact_ID() {
        return Contact_ID;
    }
}
