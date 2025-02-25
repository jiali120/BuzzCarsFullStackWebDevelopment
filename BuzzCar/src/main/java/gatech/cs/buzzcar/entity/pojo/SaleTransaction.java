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
public class SaleTransaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String salesperson;

    private String buyerId;

    private String vin;

    private LocalDate saleDate;

    private BigDecimal salePrice;

    private BigDecimal originalPurchasePrice;

    public void setSalesperson(String salesperson) {
        this.salesperson = salesperson;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public void setOriginalPurchasePrice(BigDecimal originalPurchasePrice) {
        this.originalPurchasePrice = originalPurchasePrice;
    }

    @Override
    public String toString() {
        return "SaleTransaction{" +
            "salesperson=" + salesperson +
            ", buyerId=" + buyerId +
            ", vin=" + vin +
            ", saleDate=" + saleDate +
            ", salePrice=" + salePrice +
            ", originalPurchasePrice=" + originalPurchasePrice +
        "}";
    }
}
