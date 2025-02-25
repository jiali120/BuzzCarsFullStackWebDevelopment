package gatech.cs.buzzcar.entity.pojo;

import lombok.Data;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Data
public class Vendor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private String vendorName;


    private String street;


    private String city;


    private String state;


    private String postalCode;


    private String vendorPhoneNumber;

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    @Override
    public String toString() {
        return "Vendor{" +
            "vendorName=" + vendorName +
            ", street=" + street +
            ", city=" + city +
            ", state=" + state +
            ", postalCode=" + postalCode +
            ", vendorPhoneNumber=" + vendorPhoneNumber +
        "}";
    }
}
