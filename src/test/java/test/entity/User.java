package test.entity;

/**
 * Created by carlos on 6/29/17.
 */
public class User {

    private String password;

    private String userName;

    public User(String userName, String password) {
        this.userName=userName;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
