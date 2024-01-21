/*User Class is created in order to represent the User Account*/
public class User {
    /*Username and password are defined as private instance variables*/
    private String username;
    private String password;

    /*Parametrized Constructor*/
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /*Getter methods to get the Username and Password*/
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /*Setter methods to set the Username and Password*/

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
