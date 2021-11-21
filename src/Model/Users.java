package Model;

public class Users {
    private Integer User_ID;
    private String User_Name;
    private String Password;

    public Users(Integer user_ID, String user_Name, String password) {
        User_ID = user_ID;
        User_Name = user_Name;
        Password = password;
    }

    public Integer getUser_ID() {
        return User_ID;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public String getPassword() {
        return Password;
    }
}
