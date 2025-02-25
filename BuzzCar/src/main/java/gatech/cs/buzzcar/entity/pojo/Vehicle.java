package gatech.cs.buzzcar.entity.pojo;

import lombok.Data;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Year;

/**
 * <p>
 * 
 * </p>
 *

 */
@Getter
@Data
public class Vehicle implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private String vin;


    private String manufacturerName;


    private String vehicleType;


    private String modelName;


    private Year modelYear;


    private String fuelType;


    private String vehicleDescription;


    private Integer mileage;


    private String condition;

    private BigDecimal salePrice;

    private String colors;

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setModelYear(Year modelYear) {
        this.modelYear = modelYear;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "vin=" + vin +
            ", manufacturerName=" + manufacturerName +
            ", vehicleType=" + vehicleType +
            ", modelName=" + modelName +
            ", modelYear=" + modelYear +
            ", fuelType=" + fuelType +
            ", vehicleDescription=" + vehicleDescription +
            ", mileage=" + mileage +
            ", condition=" + condition +
            ", salePrice=" + salePrice +
            ", colors=" + colors +
        "}";
    }
}
