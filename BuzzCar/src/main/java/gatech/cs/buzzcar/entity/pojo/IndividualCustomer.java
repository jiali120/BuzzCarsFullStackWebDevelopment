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
public class IndividualCustomer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String cidDriverLicense;

    private String firstName;

    private String lastName;

    public void setCidDriverLicense(String cidDriverLicense) {
        this.cidDriverLicense = cidDriverLicense;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "IndividualCustomer{" +
            "cidDriverLicense=" + cidDriverLicense +
            ", firstName=" + firstName +
            ", lastName=" + lastName +
        "}";
    }
}
