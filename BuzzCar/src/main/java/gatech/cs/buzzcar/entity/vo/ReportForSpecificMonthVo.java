package gatech.cs.buzzcar.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportForSpecificMonthVo {
    private String firstName;
    private String lastName;
    private int numberOfVehicleSold;
    private BigDecimal totalSales;
}
