package gatech.cs.buzzcar.service.impl;

import gatech.cs.buzzcar.entity.vo.*;
import gatech.cs.buzzcar.service.ReportsService;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsServiceImpl implements ReportsService {
    @Resource
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<SellerHistoryVo> querySellerHistory() {
        String sql = " SELECT " +
                " IFNULL(b.business_name, CONCAT(i.first_name, ' ', i.last_name)) AS seller_name, " +
                " COUNT(DISTINCT it.vin)               AS total_purchase_number_of_vehicles, " +
                " AVG(it.buy_price)                    AS avg_purchase_price, " +
                " COALESCE(AVG(pa.numofpart), 0)       AS avg_number_of_parts_per_vehicle, " +
                " COALESCE(AVG(pa.totalcostofpart), 0) AS avg_cost_of_parts_per_vehicle, " +
                " count(st.vin) as total_sale_number_of_vehicles " +

                " FROM Inventory_Transaction it " +
                "         LEFT JOIN Business_Customer b ON it.seller_id = b.cid_tax_number " +
                "         LEFT JOIN Individual_Customer i ON it.seller_id = i.cid_driver_license " +
                "         left join  `sale_transaction` st on st.`vin` = it.vin " +
                "         LEFT JOIN " +
                "     (SELECT po.vin                             AS pa_vin, " +
                "             SUM(po.quantity)                   AS numofpart, " +
                "             SUM(po.total_of_cost) AS totalcostofpart " +
                "      FROM Parts_Order po  " +
                "      GROUP BY po.vin) pa  " +
                "    ON it.vin = pa.pa_vin where it.sale_status='sold' " +
                " GROUP BY IFNULL(b.business_name, CONCAT(i.first_name, ' ', " +
                "                                        i.last_name)) " +
                " ORDER BY total_sale_number_of_vehicles desc, avg_purchase_price";

        return jdbcTemplate.query(sql, (rs, rowNum)->{
            SellerHistoryVo vo = new SellerHistoryVo();
            vo.setSellerName(rs.getString("seller_name"));
            vo.setTotalPurchaseNumberOfVehicles(rs.getInt("total_purchase_number_of_vehicles"));
            vo.setAvgPurchasePrice(rs.getBigDecimal("avg_purchase_price"));
            vo.setAvgNumberOfPartsPerVehicle(rs.getBigDecimal("avg_number_of_parts_per_vehicle"));
            vo.setAvgCostOfPartsPerVehicle(rs.getBigDecimal("avg_cost_of_parts_per_vehicle"));
            vo.setTotalSaleNumberOfVehicles(rs.getInt("total_sale_number_of_vehicles"));
            return vo;
        });

    }

    @Override
    public List<AvgTimeInInventoryVo> queryAverageTimeInInventory() {
        String sql = "SELECT v.vtype as vehicle_type, " +
                "       CASE " +
                "           WHEN t2.AveDays IS NOT NULL THEN " +
                "               CAST(t2.AveDays AS CHAR) " +
                "           ELSE " +
                "               'N/A' " +
                "           END AS 'average_time_in_inventory' " +
                " FROM (SELECT vtype FROM Vehicle_Type) v " +
                "         LEFT JOIN " +
                "     ( " +
                " SELECT v.vehicle_type, " +
                "             AVG(DATEDIFF(COALESCE(st.sale_date, CURDATE()), it.buy_date)) " +
                "                 as AveDays " +
                " FROM Vehicle v " +
                "               INNER JOIN Inventory_Transaction as it ON v.vin = it.vin " +
                "               LEFT JOIN Sale_Transaction as st ON it.vin = st.vin " +
                " GROUP BY v.vehicle_type " +
                "      ) as t2 " +
                "     ON v.vtype = t2.vehicle_type";
        return jdbcTemplate.query(sql, (rs, rowNum)->{
            AvgTimeInInventoryVo vo = new AvgTimeInInventoryVo();
            vo.setVehicleType(rs.getString("vehicle_type"));
            vo.setAverageTimeInInventory(rs.getString("average_time_in_inventory"));
            return vo;
        });
    }

    @Override
    public List<PricePerConditionVo> queryPricePerCondition() {
        String sql = " SELECT " +
                " vehicle_type, " +
                " COALESCE(ROUND(AVG(CASE WHEN `condition` = 'excellent' THEN " +
                " it.buy_price ELSE NULL END), 2), '$0') AS 'excellent', " +
                " COALESCE(ROUND(AVG(CASE WHEN `condition` = 'very_good' THEN " +
                " it.buy_price ELSE NULL END), 2), '$0') AS 'very_good', " +
                " COALESCE(ROUND(AVG(CASE WHEN `condition` = 'good' THEN " +
                " it.buy_price ELSE NULL END), 2), '$0') AS 'good', " +
                " COALESCE(ROUND(AVG(CASE WHEN `condition` = 'fair' THEN " +
                " it.buy_price ELSE NULL END), 2), '$0') AS 'fair' " +
                " FROM Vehicle v " +
                " LEFT JOIN Inventory_Transaction it ON v.vin = it.vin " +
                " GROUP BY vehicle_type ";
        return jdbcTemplate.query(sql, (rs, rowNum)->{
            PricePerConditionVo vo = new PricePerConditionVo();
            vo.setExcellent(rs.getString("excellent"));
            vo.setVeryGood(rs.getString("very_good"));
            vo.setGood(rs.getString("good"));
            vo.setFair(rs.getString("fair"));
            vo.setVehicleType(rs.getString("vehicle_type"));
            return vo;
        });
    }

    @Override
    public List<PartsStatisticsVo> queryPartsStat() {
        String sql = "SELECT " +
                " v.vendor_name, " +
                " COUNT(po.quantity) AS 'number', " +
                " IFNULL(SUM(po.total_of_cost), 0) AS 'amount' " +
                " FROM Vendor v " +
                " LEFT JOIN Parts_Order po ON v.vendor_name = po.vendor_name " +
                " GROUP BY v.vendor_name " +
                " WITH ROLLUP ";
        return jdbcTemplate.query(sql, (rs, rowNum)->{
            PartsStatisticsVo vo = new PartsStatisticsVo();
            vo.setVendorName(rs.getString("vendor_name"));
            vo.setNumber(rs.getInt("number"));
            vo.setAmount(rs.getBigDecimal("amount"));
            return vo;
        });
    }

    @Override
    public List<MonthlySalesSummaryVo> queryMonthlySalesSummary() {
        String sql = " SELECT " +
                " YEAR(st.sale_date) AS `year`, " +
                " MONTH(st.sale_date) AS `month`, " +
                " COUNT(st.vin) AS 'total_number_of_vehicles_sold', " +
                " SUM(st.sale_price) AS 'total_sales_income', " +
                " SUM(st.sale_price - it.buy_price - IFNULL(po.Parts_Cost, 0)) AS 'total_income' " +
                " FROM Sale_Transaction st " +
                " INNER JOIN Inventory_Transaction it ON st.vin = it.vin " +
                " LEFT JOIN ( " +
                " SELECT vin, SUM(po.total_of_cost) as Parts_Cost " +
                " FROM Parts_Order po " +
                " GROUP BY vin" +
                " ) po ON st.vin = po.vin " +
                " GROUP BY YEAR(st.sale_date), MONTH(st.sale_date) " +
                " ORDER BY Year DESC, Month DESC ";
        return jdbcTemplate.query(sql, (rs, rowNum)->{
            MonthlySalesSummaryVo vo = new MonthlySalesSummaryVo();
            vo.setYear(rs.getInt("year"));
            vo.setMonth(rs.getInt("month"));
            vo.setTotalNumberOfVehiclesSold(rs.getInt("total_number_of_vehicles_sold"));
            vo.setTotalSalesIncome(rs.getBigDecimal("total_sales_income"));
            vo.setTotalIncome(rs.getBigDecimal("total_income"));
            return vo;
        });
    }

    @Override
    public List<ReportForSpecificMonthVo> queryReportForSpecificMonth(int year, int month) {
        String sql = "SELECT  " +
                " u.first_name, " +
                " u.last_name,  " +
                " COUNT(DISTINCT st.vin) AS 'number_of_vehicle_sold',  " +
                " SUM(st.sale_price) AS 'total_sales'  " +
                "FROM Sale_Transaction st  " +
                "INNER JOIN User u ON st.salesperson = u.username  " +
                "WHERE  " +
                " YEAR(st.sale_date) = ?  " +
                " AND MONTH(st.sale_date) = ? " +
                "GROUP BY u.username, u.first_name, u.last_name  " +
                "ORDER BY Number_of_Vehicle_Sold DESC, Total_Sales DESC ";
        return jdbcTemplate.query(sql, (rs, rowNum)->{
            ReportForSpecificMonthVo vo = new ReportForSpecificMonthVo();
            vo.setFirstName(rs.getString("first_name"));
            vo.setLastName(rs.getString("last_name"));
            vo.setNumberOfVehicleSold(rs.getInt("number_of_vehicle_sold"));
            vo.setTotalSales(rs.getBigDecimal("total_sales"));
            return vo;
        }, year, month);
    }
}
