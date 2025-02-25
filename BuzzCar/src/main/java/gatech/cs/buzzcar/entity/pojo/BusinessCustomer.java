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
public class BusinessCustomer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private String cidTaxNumber;


    private String businessName;


    private String contactTitle;


    private String firstName;


    private String lastName;

    public void setCidTaxNumber(String cidTaxNumber) {
        this.cidTaxNumber = cidTaxNumber;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "BusinessCustomer{" +
            "cidTaxNumber=" + cidTaxNumber +
            ", businessName=" + businessName +
            ", contactTitle=" + contactTitle +
            ", firstName=" + firstName +
            ", lastName=" + lastName +
        "}";
    }
}
