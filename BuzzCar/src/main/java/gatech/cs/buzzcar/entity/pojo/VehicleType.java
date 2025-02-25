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
public class VehicleType implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private String vtype;

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    @Override
    public String toString() {
        return "VehicleType{" +
            "vtype=" + vtype +
        "}";
    }
}
