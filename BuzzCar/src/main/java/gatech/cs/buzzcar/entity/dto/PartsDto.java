package gatech.cs.buzzcar.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PartsDto {

    private String purchaseOrderNumber;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal totalOfCost;

    @NotBlank
    private String vin;

    @NotBlank
    private String vendorName;


    private String inventoryClerk;

    @NotBlank
    private String description;
}
