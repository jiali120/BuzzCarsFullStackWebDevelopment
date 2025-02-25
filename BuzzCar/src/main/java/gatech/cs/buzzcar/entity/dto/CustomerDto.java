package gatech.cs.buzzcar.entity.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDto {

    @NotBlank
    private String cid;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotBlank
    private String customerType;

    // biz

    private String cidTaxNumber;


    private String businessName;


    private String contactTitle;


    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    //individual
    private String cidDriverLicense;



}
