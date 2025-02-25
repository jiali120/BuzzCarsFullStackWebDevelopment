package gatech.cs.buzzcar.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VehicleVo {

    private String vin;

    private String manufacturerName;

    private String vehicleType;

    private String modelName;

    private int modelYear;

    private String fuelType;

    private Integer mileage;

    private BigDecimal salePrice;

    private String colors;

    private String condition;
    private String vehicleDescription;

    private String sellerId;
    private String saleStatus;
    private Date buyDate;
    private BigDecimal buyPrice;
}
