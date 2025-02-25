package gatech.cs.buzzcar.entity.pojo;

import lombok.Data;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *

 */
@Getter
@Data
public class InventoryTransaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private String vin;


    private String sellerId;


    private String inventoryClerk;


    private LocalDate buyDate;


    private BigDecimal buyPrice;

    /**
     * saleable/forbidden/sold
     */
    private String saleStatus;

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public void setInventoryClerk(String inventoryClerk) {
        this.inventoryClerk = inventoryClerk;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Override
    public String toString() {
        return "InventoryTransaction{" +
            "vin=" + vin +
            ", sellerId=" + sellerId +
            ", inventoryClerk=" + inventoryClerk +
            ", buyDate=" + buyDate +
            ", buyPrice=" + buyPrice +
            ", saleStatus=" + saleStatus +
        "}";
    }
}
