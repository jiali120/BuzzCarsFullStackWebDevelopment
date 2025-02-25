package gatech.cs.buzzcar.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SellerHistoryVo {

    private String sellerName;

    private int totalPurchaseNumberOfVehicles;

    private BigDecimal avgPurchasePrice;
    private BigDecimal avgNumberOfPartsPerVehicle;
    private BigDecimal avgCostOfPartsPerVehicle;
    private int totalSaleNumberOfVehicles;


}
