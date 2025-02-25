package gatech.cs.buzzcar.entity.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PublicSearchParam extends BaseParam{

    private String vehicleType;

    private String manufacturerName;

    private Integer modelYear;

    private String fuelType;

    private String color;

    private String keyword;
    private String vin;
    /**
     * manager
     */
    private String saleStatus;
}
