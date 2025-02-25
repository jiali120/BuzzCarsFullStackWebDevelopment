package gatech.cs.buzzcar.service.impl;

import gatech.cs.buzzcar.enums.PartsOrderStatusEnum;
import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.dto.PartsDto;
import gatech.cs.buzzcar.entity.dto.PartsStatusDto;
import gatech.cs.buzzcar.entity.vo.PartDetailVo;
import gatech.cs.buzzcar.entity.vo.VehicleVo;
import gatech.cs.buzzcar.enums.SaleStatusEnum;
import gatech.cs.buzzcar.service.InventoryTransactionService;
import gatech.cs.buzzcar.service.PartsOrderService;
import gatech.cs.buzzcar.service.VehicleService;
import gatech.cs.buzzcar.utils.UserUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class PartsOrderServiceImpl implements PartsOrderService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private VehicleService vehicleService;

    @Resource
    private InventoryTransactionService inventoryTransactionService;

    @Override
    public List<PartDetailVo> getPartDetailListByVin(String vin) {
        String sql = "select " +
                " po.purchase_order_number, po.vin, po.`quantity`  , po.`total_of_cost` , po.`vendor_name`, po.`inventory_clerk`, " +
                " po.`description`, po.`status` " +
                " from `parts_order` as po " +
                " where po.`vin` = ? ";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PartDetailVo vo = new PartDetailVo();
            vo.setDescription(rs.getString("description"));
            vo.setStatus(rs.getString("status"));
            vo.setPurchaseOrderNumber(rs.getString("purchase_order_number"));
            vo.setQuantity(rs.getInt("quantity"));
            vo.setTotalOfCost(rs.getBigDecimal("total_of_cost"));
            vo.setVin(rs.getString("vin"));
            vo.setVendorName(rs.getString("vendor_name"));
            vo.setInventoryClerk(rs.getString("inventory_clerk"));
            return vo;
        }, vin);
    }

    @Override
    @Transactional
    public int savePartsOrder(PartsDto dto) {
        String sql = "insert into `parts_order`(`purchase_order_number`,`quantity`,`total_of_cost`,`vin`,`vendor_name`,`inventory_clerk`,`status`,`description`) values(?,?,?,?,?,?,?,?)";
        String countSql = "select count(1) from parts_order where vin=?";
        Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, dto.getVin());

        String purchaseOrderNumber = dto.getVin() + "-";

        if (Objects.isNull(count) || count == 0) {
            purchaseOrderNumber = purchaseOrderNumber + "01";
        } else {
            if (count < 9) {
                purchaseOrderNumber = purchaseOrderNumber + "0" + (count+1);
            } else {
                purchaseOrderNumber = purchaseOrderNumber + (count+1);
            }
        }
        dto.setInventoryClerk(UserUtils.getUsername());
        BigDecimal totalOfCost = dto.getTotalOfCost();

        jdbcTemplate.update(sql, purchaseOrderNumber, dto.getQuantity(), totalOfCost,
                dto.getVin(), dto.getVendorName(), dto.getInventoryClerk(), "ordered", dto.getDescription());

        // update vehicle sale_price
        VehicleVo vehicleByVin = vehicleService.getVehicleByVin(dto.getVin());
        BigDecimal salePrice = vehicleByVin.getSalePrice();
        if(Objects.nonNull(salePrice)){
            BigDecimal updateSalePrice = BigDecimal.valueOf(salePrice.doubleValue() + totalOfCost.doubleValue() * 1.10);
            int row = vehicleService.updateSalePrice(vehicleByVin.getVin(), updateSalePrice);
        }

        return 1;
    }

    @Override
    public Result updatePartsStatus(PartsStatusDto dto) {

        String sql = "select " +
                " po.purchase_order_number, po.vin, po.`quantity`  , po.`total_of_cost` , po.`vendor_name`, po.`inventory_clerk`, " +
                " po.`description`, po.`status` " +
                " from `parts_order` as po " +
                " where po.`purchase_order_number` = ? limit 1";

        List<PartDetailVo> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            PartDetailVo vo = new PartDetailVo();
            vo.setDescription(rs.getString("description"));
            vo.setStatus(rs.getString("status"));
            vo.setPurchaseOrderNumber(rs.getString("purchase_order_number"));
            vo.setQuantity(rs.getInt("quantity"));
            vo.setTotalOfCost(rs.getBigDecimal("total_of_cost"));
            vo.setVin(rs.getString("vin"));
            vo.setVendorName(rs.getString("vendor_name"));
            vo.setInventoryClerk(rs.getString("inventory_clerk"));
            return vo;
        }, dto.getPurchaseOrderNumber());

        if (CollectionUtils.isEmpty(list)) {
            return Result.fail("Unable to update");
        }

        String formStatus = dto.getStatus();

        PartDetailVo partDetailVo = list.get(0);

        String status = partDetailVo.getStatus();

        if (StringUtils.equalsIgnoreCase(status, PartsOrderStatusEnum.installed.value())) {
            return Result.fail("Parts have been installed");
        }

        if (StringUtils.equals(status,
                PartsOrderStatusEnum.received.value())) {
            if (!StringUtils.equals(formStatus, PartsOrderStatusEnum.installed.value())) {
                return Result.fail("Parts order status setting error("+PartsOrderStatusEnum.installed.value()+")");
            }
        }

        if (StringUtils.equals(status,
                PartsOrderStatusEnum.ordered.value())) {
            if (!StringUtils.equalsAny(formStatus, PartsOrderStatusEnum.installed.value(), PartsOrderStatusEnum.received.value())) {
                return Result.fail("Parts order status setting error(" + PartsOrderStatusEnum.installed.value() + " or " + PartsOrderStatusEnum.received.value() + ")");
            }
        }
        String vin = dto.getVin();
        String updateSql = "update parts_order set status=? where purchase_order_number=?";
        int row = jdbcTemplate.update(updateSql, formStatus, dto.getPurchaseOrderNumber());

        String checkSql = "select count(1) from parts_order where vin=? and status!='installed'";

        Integer statusCount = jdbcTemplate.queryForObject(checkSql, Integer.class, vin);

        if(Objects.isNull(statusCount) || statusCount == 0){
            String saleStatus = SaleStatusEnum.saleable.value();
            // select
            // update sale status
            inventoryTransactionService.updateSaleStatus(vin,saleStatus) ;
        }
        return Result.dataByEffectRows(row);
    }

    @Override
    public BigDecimal getPartDetailCostByVin(String vin) {
        String sql = "select  sum(po.total_of_cost) from `parts_order` as po where po.`vin` = ? ";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, vin);
    }


}
