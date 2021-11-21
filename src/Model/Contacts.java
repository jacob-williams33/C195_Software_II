package Model;

public class Contacts {
    private Integer Contact_ID;
    private String Contact_Name;

    public Contacts(Integer contact_ID, String contact_Name) {
        Contact_ID = contact_ID;
        Contact_Name = contact_Name;
    }

    public Integer getContact_ID() {
        return Contact_ID;
    }

    public String getContact_Name() {
        return Contact_Name;
    }
}
