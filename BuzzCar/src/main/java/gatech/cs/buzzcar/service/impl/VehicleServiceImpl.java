package gatech.cs.buzzcar.service.impl;

import gatech.cs.buzzcar.entity.dto.VehicleDto;
import gatech.cs.buzzcar.entity.param.PublicSearchParam;
import gatech.cs.buzzcar.entity.pojo.InventoryTransaction;
import gatech.cs.buzzcar.entity.pojo.SaleTransaction;
import gatech.cs.buzzcar.entity.vo.CustomerVo;
import gatech.cs.buzzcar.entity.vo.VehicleCustomerVo;
import gatech.cs.buzzcar.entity.vo.VehicleVo;
import gatech.cs.buzzcar.enums.UserRoleEnum;
import gatech.cs.buzzcar.service.CustomerService;
import gatech.cs.buzzcar.service.InventoryTransactionService;
import gatech.cs.buzzcar.service.SaleTransactionService;
import gatech.cs.buzzcar.service.VehicleService;
import gatech.cs.buzzcar.utils.UserUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {


    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private InventoryTransactionService inventoryTransactionService;

    @Resource
    private SaleTransactionService saleTransactionService;

    @Resource
    private CustomerService customerService;

    private static VehicleVo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return getVehicleVo(resultSet);
    }

    private static VehicleVo getVehicleVo(ResultSet resultSet) throws SQLException {
        VehicleVo vo = new VehicleVo();
        vo.setVin(resultSet.getString("vin"));
        vo.setManufacturerName(resultSet.getString("manufacturer_name"));
        vo.setVehicleType(resultSet.getString("vehicle_type"));
        vo.setModelName(resultSet.getString("model_name"));
        vo.setModelYear(resultSet.getInt("model_year"));
        vo.setFuelType(resultSet.getString("fuel_type"));
        vo.setMileage(resultSet.getInt("mileage"));
        vo.setSalePrice(resultSet.getBigDecimal("sale_price"));
        vo.setColors(resultSet.getString("colors"));
        return vo;
    }

    private static VehicleVo getVehicleVo2(ResultSet resultSet) throws SQLException {
        VehicleVo vo = new VehicleVo();
        vo.setVin(resultSet.getString("vin"));
        vo.setManufacturerName(resultSet.getString("manufacturer_name"));
        vo.setVehicleType(resultSet.getString("vehicle_type"));
        vo.setModelName(resultSet.getString("model_name"));
        vo.setModelYear(resultSet.getInt("model_year"));
        vo.setFuelType(resultSet.getString("fuel_type"));
        vo.setMileage(resultSet.getInt("mileage"));
        vo.setSalePrice(resultSet.getBigDecimal("sale_price"));
        vo.setColors(resultSet.getString("colors"));
        vo.setCondition(resultSet.getString("condition"));
        vo.setVehicleDescription(resultSet.getString("vehicle_description"));
        vo.setSellerId(resultSet.getString("seller_id"));
        vo.setBuyDate(resultSet.getDate("buy_date"));
        vo.setBuyPrice(resultSet.getBigDecimal("buy_price"));
        vo.setSaleStatus(resultSet.getString("sale_status"));
        return vo;
    }

    @Override
    public List<VehicleVo> search(PublicSearchParam publicSearchParam) {

        StringBuilder querySql = new StringBuilder("select " +
                " v.vin, v.`manufacturer_name`, v.`vehicle_type`, v.`model_name`, v.`model_year`, " +
                " v.`fuel_type`, v.`mileage`,v.sale_price , v.colors " +
                " from  `vehicle` v where  v.vin in  ");

        String userRole = UserUtils.getUserRole();
        List<Object> params = new ArrayList<>();

        if(StringUtils.equalsAnyIgnoreCase(userRole, UserRoleEnum.Manager.value(), UserRoleEnum.Owner.value())){
            String saleStatus = publicSearchParam.getSaleStatus();
            if(StringUtils.isNotBlank(saleStatus)){
                if(StringUtils.contains(saleStatus, ",")){
                    String[] sales = StringUtils.split(saleStatus, ",");
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    for (String sale : sales) {
                        if(sales.length == (i+1)){
                            sb.append("'").append(sale).append("'");
                        }else{
                            sb.append("'").append(sale).append("',");
                        }
                        i++;
                    }
                    log.info("sale status: {}", sb);
                    querySql.append(" (select it.vin from `inventory_transaction` it where it.sale_status in (").append(sb).append(")) ");
                }else{
                    querySql.append(" (select it.vin from `inventory_transaction` it where it.sale_status='").append(saleStatus).append("') ");
                }

            }else{
                querySql.append("(select it.vin from `inventory_transaction` it) ");
            }
        }else if(StringUtils.equalsIgnoreCase(userRole, UserRoleEnum.InventoryClerks.value())){
            querySql.append(" (select it.vin from `inventory_transaction` it where it.sale_status in ('saleable', 'forbidden') ) ");
        }else{
            querySql.append(" (select it.vin from `inventory_transaction` it where it.sale_status='saleable') ");
        }
        if (StringUtils.isNotBlank(publicSearchParam.getVehicleType())) {
            querySql.append(" and v.vehicle_type=? ");
            params.add(publicSearchParam.getVehicleType());
        }
        if (StringUtils.isNotBlank(publicSearchParam.getFuelType())) {
            querySql.append(" and v.fuel_type=? ");
            params.add(publicSearchParam.getFuelType());
        }
        if (StringUtils.isNotBlank(publicSearchParam.getManufacturerName())) {
            querySql.append(" and v.manufacturer_name=? ");
            params.add(publicSearchParam.getManufacturerName());
        }

        if (Objects.nonNull(publicSearchParam.getModelYear())) {
            querySql.append(" and v.model_year=? ");
            params.add(publicSearchParam.getModelYear());
        }

        if (Objects.nonNull(publicSearchParam.getColor())) {
            querySql.append(" and v.colors like concat('%',?,'%') ");
            params.add(publicSearchParam.getColor());
        }



        // which searches the manufacturer, model year, model name and description

        if (StringUtils.isNotBlank(publicSearchParam.getKeyword())) {
            querySql.append(" and ( (v.manufacturer_name like concat('%',?,'%') ) OR (v.model_name like concat('%',?,'%')) OR (v.vehicle_description like concat('%',?,'%')) )");
            params.add(publicSearchParam.getKeyword());
            params.add(publicSearchParam.getKeyword());
            params.add(publicSearchParam.getKeyword());
        }

        if(StringUtils.isNotBlank(publicSearchParam.getVin())){
            querySql.append(" and v.vin like concat('%',?,'%') ");
            params.add(publicSearchParam.getVin());
        }

        querySql.append(" order by v.vin asc");


        return jdbcTemplate.query(querySql.toString(), (resultSet, rowNum) -> {
            return getVehicleVo(resultSet);
        }, params.toArray());
    }

    @Override
    public int queryVehicleAvailableQuantityForSale() {
        String cntSql = "select count(1) from `vehicle` v where  v.vin in (select it.vin from `inventory_transaction` it where it.sale_status='saleable') ";
        Integer cnt = jdbcTemplate.queryForObject(cntSql, Integer.class);
        return Objects.nonNull(cnt) ? cnt : 0;
    }


    @Override
    @Transactional
    public int saveVehicle(VehicleDto dto) {
        dto.setInventoryClerk(UserUtils.getUsername());
        int cnt = 0;
        Integer row = jdbcTemplate.queryForObject("select count(1) from vehicle where vin=?", Integer.class, dto.getVin());
        if(Objects.nonNull(row) && row > 0){
            cnt = -1;
        }else{
            String insertSql = "insert into vehicle(vin, `manufacturer_name`,`vehicle_type`,`model_name`,`model_year`,`fuel_type`,`vehicle_description`,`mileage`,`condition`,`sale_price`,`colors`) values(?, ?,?,?,?, ?, ?,?,?,?, ?)";

            // init value
            double salePrice = dto.getBuyPrice().doubleValue() * (1 + 0.25);

            jdbcTemplate.update(
                    insertSql, dto.getVin(), dto.getManufacturerName(), dto.getVehicleType(),
                    dto.getModelName(), dto.getModelYear(), dto.getFuelType(), dto.getVehicleDescription(),dto.getMileage(), dto.getCondition(), new BigDecimal(salePrice), dto.getColors());

            //inventory_transaction
            String insertInventoryTransaction = "insert into inventory_transaction(`vin`, `seller_id`, `inventory_clerk`,`buy_date`, `buy_price`, `sale_status`) values(?,?,?,?,?,?)";
            jdbcTemplate.update(insertInventoryTransaction, dto.getVin(), dto.getSellerId(), dto.getInventoryClerk(), dto.getBuyDate(), dto.getBuyPrice(),"forbidden");
            cnt = 1;
        }
        return cnt;
    }

    @Override
    public VehicleVo getVehicleByVin(String vin) {

        String sql = "select v.*, it.buy_date, it.buy_price, it.seller_id, it.sale_status from vehicle v, inventory_transaction it where it.vin=v.vin and it.vin=?";
        List<VehicleVo> list = jdbcTemplate.query(sql, (rs, rowNum)->{
            return getVehicleVo2(rs);
        }, vin);

        if(!list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public int updateSalePrice(String vin, BigDecimal updateSalePrice) {
        String updateSql = "update vehicle set sale_price=? where vin=?";
        return jdbcTemplate.update(updateSql, updateSalePrice, vin);
    }

    @Override
    public int getVehiclePendingCount() {
        String cntSql = "select count(1) from `vehicle` v where  v.vin in (select it.vin from `inventory_transaction` it where it.sale_status in ('forbidden')) ";
        Integer cnt = jdbcTemplate.queryForObject(cntSql, Integer.class);
        return Objects.nonNull(cnt) ? cnt : 0;

    }



    @Override
    public VehicleCustomerVo getVehicleCustomerInfo(String vin) {
        InventoryTransaction inventoryTransaction = inventoryTransactionService.getByVin(vin);
        SaleTransaction saleTransaction = saleTransactionService.getByVin(vin);
        StringBuilder sellerInfo = new StringBuilder();
        StringBuilder buyerInfo = new StringBuilder();
        CustomerVo sellerCustomer = customerService.getCustomerByCid(inventoryTransaction.getSellerId());
        if(Objects.nonNull(sellerCustomer)){
            sellerInfo.append("purchaser:").append(inventoryTransaction.getInventoryClerk())
                    .append(", original purchase price: ").append(inventoryTransaction.getBuyPrice())
                    .append(", seller: ").append(sellerCustomer.getCname()).append(", phone: ").append(sellerCustomer.getPhone())
                    .append(", email: ").append(sellerCustomer.getEmail());
        }

        if(Objects.nonNull(saleTransaction)){
            CustomerVo buyerCustomer = customerService.getCustomerByCid(saleTransaction.getBuyerId());
            buyerInfo.append("buyer: ").append(buyerCustomer.getCname()).append(", phone: ").append(buyerCustomer.getPhone())
                    .append(", email: ").append(buyerCustomer.getEmail());
        }
        return new VehicleCustomerVo(sellerInfo.toString(), buyerInfo.toString());
    }
}
