package gatech.cs.buzzcar.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VehicleDto {

    @NotBlank
    private String vin;

    @NotBlank
    private String manufacturerName;

    @NotBlank
    private String vehicleType;

    @NotBlank
    private String modelName;

    @NotNull
    private int modelYear;

    @NotBlank
    private String fuelType;

    @NotBlank
    private String vehicleDescription;

    @NotNull
    private Integer mileage;

    @NotBlank
    private String condition;

    @NotBlank
    private String colors;

    @NotBlank
    private String sellerId;

    // login
    private String inventoryClerk;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate buyDate;

    @NotNull
    private BigDecimal buyPrice;


}
