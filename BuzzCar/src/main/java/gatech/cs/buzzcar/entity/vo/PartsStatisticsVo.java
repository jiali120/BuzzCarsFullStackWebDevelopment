package gatech.cs.buzzcar.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PartsStatisticsVo {
    private String vendorName;
    private int number;
    private BigDecimal amount;
}
