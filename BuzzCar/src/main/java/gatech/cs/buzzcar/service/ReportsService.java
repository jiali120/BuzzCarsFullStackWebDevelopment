package gatech.cs.buzzcar.service;

import gatech.cs.buzzcar.entity.vo.*;

import java.util.List;

public interface ReportsService {
    List<SellerHistoryVo> querySellerHistory();

    List<AvgTimeInInventoryVo> queryAverageTimeInInventory();

    List<PricePerConditionVo> queryPricePerCondition();

    List<PartsStatisticsVo> queryPartsStat();

    List<MonthlySalesSummaryVo> queryMonthlySalesSummary();

    List<ReportForSpecificMonthVo> queryReportForSpecificMonth(int year, int month);
}
