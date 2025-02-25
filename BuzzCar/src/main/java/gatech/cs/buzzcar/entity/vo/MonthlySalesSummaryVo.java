package gatech.cs.buzzcar.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlySalesSummaryVo {

    private int year;
    private int month;
    private int totalNumberOfVehiclesSold;
    private BigDecimal totalSalesIncome;
    private BigDecimal totalIncome;

}
