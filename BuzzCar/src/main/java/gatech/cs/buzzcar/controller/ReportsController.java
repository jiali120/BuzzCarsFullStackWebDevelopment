package gatech.cs.buzzcar.controller;


import gatech.cs.buzzcar.common.annotation.HasPermission;
import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.vo.*;
import gatech.cs.buzzcar.service.ReportsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportsController {

    @Resource
    private ReportsService reportsService;

    @GetMapping(value = "/api/v1/seller-history")
    @HasPermission(value = "manager,owner")
    public Result querySellerHistory(){
        List<SellerHistoryVo> list = reportsService.querySellerHistory();
        return Result.successData(list);
    }

    @GetMapping(value = "/api/v1/avg-time-in-inventory")
    @HasPermission(value = "manager,owner")
    public Result queryAverageTimeInInventory(){
        List<AvgTimeInInventoryVo> list = reportsService.queryAverageTimeInInventory();
        return Result.successData(list);
    }

    @GetMapping(value = "/api/v1/price-per-condition")
    @HasPermission(value = "manager,owner")
    public Result queryPricePerCondition(){
        List<PricePerConditionVo> list = reportsService.queryPricePerCondition();
        return Result.successData(list);
    }

    @GetMapping(value = "/api/v1/parts-stat")
    @HasPermission(value = "manager,owner")
    public Result queryPartsStat(){
        List<PartsStatisticsVo> list = reportsService.queryPartsStat();
        return Result.successData(list);
    }

    @GetMapping(value = "/api/v1/monthly-sales-summary")
    @HasPermission(value = "manager,owner")
    public Result queryMonthlySalesSummary(){
        List<MonthlySalesSummaryVo> list = reportsService.queryMonthlySalesSummary();
        return Result.successData(list);
    }

    @GetMapping(value = "/api/v1/report-for-specific-month")
    @HasPermission(value = "manager,owner")
    public Result queryReportForSpecificMonth(int year, int month){
        List<ReportForSpecificMonthVo> list = reportsService.queryReportForSpecificMonth(year, month);
        return Result.successData(list);
    }
}
