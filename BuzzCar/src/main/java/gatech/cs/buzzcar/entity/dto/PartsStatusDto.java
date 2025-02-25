package gatech.cs.buzzcar.entity.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PartsStatusDto {

    @NotBlank
    private String vin;

    @NotBlank
    private String status;

    @NotBlank
    private String purchaseOrderNumber;
}
