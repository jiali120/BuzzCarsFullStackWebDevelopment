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
@Deprecated
public class VehicleColor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private String vin;


    private String color;

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "VehicleColor{" +
            "vin=" + vin +
            ", color=" + color +
        "}";
    }
}
