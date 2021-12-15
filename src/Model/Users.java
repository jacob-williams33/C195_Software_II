package Model;

/**This class controls users*/

public class Users {
    private Integer User_ID;
    private String User_Name;
    private String Password;

    /**Constructor for users
     @param user_ID ID of user
     @param user_Name name of user
     @param password password for user*/

    public Users(Integer user_ID, String user_Name, String password) {
        User_ID = user_ID;
        User_Name = user_Name;
        Password = password;
    }

    /**method gets ID of user.
     @return ID of user*/

    public Integer getUser_ID() {
        return User_ID;
    }

    /**method gets name of user.
     @return name of user*/

    public String getUser_Name() {
        return User_Name;
    }

    /**Method gets password of user
     @return password of user*/

    public String getPassword() {
        return Password;
    }

    /**overrides to string for combo boxes*/

    @Override
    public String toString() {
        return String.valueOf(User_ID);
    }
}
