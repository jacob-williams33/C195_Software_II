package Model;

import java.time.LocalDateTime;

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

    public int getAppointment_ID() {
        return Appointment_ID;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getLocation() {
        return Location;
    }

    public String getType() {
        return Type;
    }

    public LocalDateTime getStart() {
        return Start;
    }

    public LocalDateTime getEnd() {
        return End;
    }

    public Integer getCustomer_ID() {
        return Customer_ID;
    }

    public Integer getUser_ID() {
        return User_ID;
    }

    public Integer getContact_ID() {
        return Contact_ID;
    }
}
