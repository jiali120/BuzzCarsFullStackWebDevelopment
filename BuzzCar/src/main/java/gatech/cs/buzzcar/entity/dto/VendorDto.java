package gatech.cs.buzzcar.entity.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VendorDto {


    @NotBlank
    private String vendorName;

    @NotBlank
    private String street;
    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String postalCode;
    @NotBlank
    private String vendorPhoneNumber;
}
