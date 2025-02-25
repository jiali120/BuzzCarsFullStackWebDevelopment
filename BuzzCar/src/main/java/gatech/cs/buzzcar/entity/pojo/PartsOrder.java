package gatech.cs.buzzcar.entity.pojo;

import lombok.Data;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *

 */
@Getter
@Data
public class PartsOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String purchaseOrderNumber;
    private Integer quantity;
    private BigDecimal totalOfCost;
    private String vin;
    private String vendorName;
    private String inventoryClerk;

    private String description;

    private String status;

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotalOfCost(BigDecimal totalOfCost) {
        this.totalOfCost = totalOfCost;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setInventoryClerk(String inventoryClerk) {
        this.inventoryClerk = inventoryClerk;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PartsOrder{" +
            "purchaseOrderNumber=" + purchaseOrderNumber +
            ", quantity=" + quantity +
            ", totalOfCost=" + totalOfCost +
            ", vin=" + vin +
            ", vendorName=" + vendorName +
            ", inventoryClerk=" + inventoryClerk +
            ", description=" + description +
            ", status=" + status +
        "}";
    }
}
