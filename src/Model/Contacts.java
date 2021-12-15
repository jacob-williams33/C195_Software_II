package Model;

/**This class controls contacts*/

public class Contacts {
    private Integer Contact_ID;
    private String Contact_Name;

    /**Constructor for Contacts
     @param contact_ID ID for contact
     @param contact_Name name of contact*/

    public Contacts(Integer contact_ID, String contact_Name) {
        Contact_ID = contact_ID;
        Contact_Name = contact_Name;
    }

    /**Method to get ID of contact
     @return returns Contact ID*/

    public Integer getContact_ID() {
        return Contact_ID;
    }

    /**Method to get name of contact
     @return returns contact name*/

    public String getContact_Name() {
        return Contact_Name;
    }

    /**This method overrides to string method to populate combo box names*/

    @Override
    public String toString() {
        return Contact_Name;
    }
}
