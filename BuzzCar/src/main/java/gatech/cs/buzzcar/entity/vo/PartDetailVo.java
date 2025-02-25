package gatech.cs.buzzcar.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PartDetailVo {


    private String description;


    private String status;


    private String purchaseOrderNumber;


    private Integer quantity;


    private BigDecimal totalOfCost;


    private String vin;


    private String vendorName;


    private String inventoryClerk;


}
