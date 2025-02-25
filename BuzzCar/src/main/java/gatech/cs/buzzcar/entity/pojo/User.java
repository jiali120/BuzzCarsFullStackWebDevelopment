package gatech.cs.buzzcar.entity.pojo;

import lombok.Data;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *

 */
@Getter
@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private String username;


    private String password;


    private String firstName;


    private String lastName;

    private String userRole;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
            "username=" + username +
            ", password=" + password +
            ", firstName=" + firstName +
            ", lastName=" + lastName +
            ", userRole=" + userRole +
        "}";
    }
}
