package gatech.cs.buzzcar.entity.vo;

import lombok.Data;

@Data
public class PricePerConditionVo {

    private String vehicleType;
    private String excellent;
    private String veryGood;
    private String good;
    private String fair;

}
