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
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private String cid;


    private String street;


    private String city;


    private String state;


    private String postalCode;


    private String phone;


    private String email;

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "cid=" + cid +
            ", street=" + street +
            ", city=" + city +
            ", state=" + state +
            ", postalCode=" + postalCode +
            ", phone=" + phone +
            ", email=" + email +
        "}";
    }
}
