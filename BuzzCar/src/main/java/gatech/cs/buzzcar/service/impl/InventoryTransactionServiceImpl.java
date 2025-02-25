package gatech.cs.buzzcar.service.impl;

import gatech.cs.buzzcar.entity.pojo.InventoryTransaction;
import gatech.cs.buzzcar.service.InventoryTransactionService;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class InventoryTransactionServiceImpl implements InventoryTransactionService {



    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int updateSaleStatus(String vin, String saleStatus) {
        String updateSql = "update inventory_transaction set sale_status=? where vin=?";
        return jdbcTemplate.update(updateSql, saleStatus, vin);
    }

    @Override
    public InventoryTransaction getByVin(String vin) {
        String sql = "select * from inventory_transaction where vin=? limit 1";
        List<InventoryTransaction> list = jdbcTemplate.query(sql, (rs, rowNum)->{
            InventoryTransaction obj = new InventoryTransaction();
            obj.setVin(rs.getString("vin"));
            obj.setSellerId(rs.getString("seller_id"));
            obj.setInventoryClerk(rs.getString("inventory_clerk"));
            obj.setBuyDate(rs.getDate("buy_date").toLocalDate());
            obj.setBuyPrice(rs.getBigDecimal("buy_price"));
            obj.setSaleStatus(rs.getString("sale_status"));
            return obj;
        },vin);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }
}
