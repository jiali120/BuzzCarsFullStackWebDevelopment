package gatech.cs.buzzcar.service.impl;

import gatech.cs.buzzcar.entity.dto.SaleTransactionDto;
import gatech.cs.buzzcar.entity.pojo.SaleTransaction;
import gatech.cs.buzzcar.enums.SaleStatusEnum;
import gatech.cs.buzzcar.service.InventoryTransactionService;
import gatech.cs.buzzcar.service.SaleTransactionService;
import gatech.cs.buzzcar.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SaleTransactionServiceImpl implements SaleTransactionService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private InventoryTransactionService inventoryTransactionService;
    @Override
    public int saveSaleTransaction(SaleTransactionDto dto) {
        dto.setSalesperson(UserUtils.getUsername());
        String sql = "insert into `sale_transaction`(`salesperson`,`buyer_id`,`vin`,`sale_date`,`sale_price`,`original_purchase_price`) values(?,?,?,?,?,?)";
        int row = jdbcTemplate.update(sql, dto.getSalesperson(), dto.getBuyerId(), dto.getVin(),dto.getSaleDate(), dto.getSalePrice(), dto.getOriginalPurchasePrice());
        if(row>0){
            // update sale status
            row = inventoryTransactionService.updateSaleStatus(dto.getVin(), SaleStatusEnum.sold.value());
        }
        return row;
    }

    @Override
    public SaleTransaction getByVin(String vin) {
        String sql = "select * from sale_transaction where vin=? limit 1";
        List<SaleTransaction> list = jdbcTemplate.query(sql, (rs, rowNum)->{
            SaleTransaction obj = new SaleTransaction();
            obj.setSalesperson(rs.getString("salesperson"));
            obj.setBuyerId(rs.getString("buyer_id"));
            obj.setSaleDate(rs.getDate("sale_date").toLocalDate());
            obj.setSalePrice(rs.getBigDecimal("sale_price"));
            obj.setOriginalPurchasePrice(rs.getBigDecimal("original_purchase_price"));
            obj.setVin(rs.getString("vin"));
            return obj;
        },vin);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }
}
