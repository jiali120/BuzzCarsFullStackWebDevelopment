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
@Deprecated
public class Part implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private String partNumber;


    private String description;


    private BigDecimal costOfPart;


    private String status;


    private String purchaseOrderNumber;


    private Integer quantity;

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCostOfPart(BigDecimal costOfPart) {
        this.costOfPart = costOfPart;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Part{" +
            "partNumber=" + partNumber +
            ", description=" + description +
            ", costOfPart=" + costOfPart +
            ", status=" + status +
            ", purchaseOrderNumber=" + purchaseOrderNumber +
            ", quantity=" + quantity +
        "}";
    }
}
