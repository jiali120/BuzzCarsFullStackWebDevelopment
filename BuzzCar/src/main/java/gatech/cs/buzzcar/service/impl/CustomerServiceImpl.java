package gatech.cs.buzzcar.service.impl;

import gatech.cs.buzzcar.enums.CustomerTypeEnum;
import gatech.cs.buzzcar.entity.dto.CustomerDto;
import gatech.cs.buzzcar.entity.vo.CustomerVo;
import gatech.cs.buzzcar.service.CustomerService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public int saveCustomer(CustomerDto dto) {
        String customerType = dto.getCustomerType();

        if(StringUtils.equalsIgnoreCase(customerType, CustomerTypeEnum.Business.toString())){

            String mainSql = "insert into `customer`(`cid`,`street`,`city`,`state`,`postal_code`,`phone`,`email`) values(?,?,?,?,?,?,?)";
            jdbcTemplate.update(mainSql, dto.getCid(), dto.getStreet(), dto.getCity(), dto.getState(), dto.getPostalCode(), dto.getPhone(), dto.getEmail());

            String bizSql = "INSERT INTO `business_customer`(`cid_tax_number`,`business_name`,`contact_title`,`first_name`,`last_name`) VALUES(?,?,?,?,?)";
            jdbcTemplate.update(bizSql, dto.getCid(), dto.getBusinessName(), dto.getContactTitle(),dto.getFirstName(), dto.getLastName());
        }else if(StringUtils.equalsIgnoreCase(customerType, CustomerTypeEnum.Individual.toString())){

            String mainSql = "insert into `customer`(`cid`,`street`,`city`,`state`,`postal_code`,`phone`,`email`) values(?,?,?,?,?,?,?)";
            jdbcTemplate.update(mainSql, dto.getCid(), dto.getStreet(), dto.getCity(), dto.getState(), dto.getPostalCode(), dto.getPhone(), dto.getEmail());

            String indSql = "insert into `individual_customer`(`cid_driver_license`,`first_name`,`last_name`) values(?,?,?)";
            jdbcTemplate.update(indSql, dto.getCid(), dto.getFirstName(), dto.getLastName());
        }
        return 1;
    }


    @Override
    public List<CustomerVo> queryCustomersByNum(String num) {
        String sql = " select c.*, ext.* from customer as c left join " +
                " ( select  bc.`cid_tax_number` as num, bc.`first_name`, bc.`last_name`, bc.`business_name` as cname, bc.`contact_title` as title from `business_customer` as bc " +
                " UNION ALL " +
                " select ic.`cid_driver_license` as num, ic.`first_name`, ic.`last_name`, '' as cname, '' as title  from `individual_customer` as ic) ext " +
                " on c.cid = ext.num ";

        List<Object> params = new ArrayList<>();

        if(StringUtils.isNotBlank(num)){
            sql = sql + " where ext.num like concat('%',?,'%') ";
            params.add(num);
        }

        return jdbcTemplate.query(sql, (rs, rowNum)->{
            CustomerVo vo = new CustomerVo();
            vo.setCid(rs.getString("cid"));
            vo.setStreet(rs.getString("street"));
            vo.setCity(rs.getString("city"));
            vo.setState(rs.getString("state"));
            vo.setPostalCode(rs.getString("postal_code"));
            vo.setPhone(rs.getString("phone"));
            vo.setEmail(rs.getString("email"));
            vo.setNum(rs.getString("num"));
            vo.setCname(rs.getString("cname"));
            vo.setTitle(rs.getString("title"));
            vo.setFirstName(rs.getString("first_name"));
            vo.setLastName(rs.getString("last_name"));
            return vo;
        }, params.toArray());

    }

    public CustomerVo getCustomerByCid(String cid){
        String sql = "SELECT  " +
                "IFNULL(bc.business_name, CONCAT(ic.first_name, ' ', ic.last_name)) AS cname " +
                ", c.* " +
                " FROM `customer` c LEFT JOIN `individual_customer` ic ON c.cid=ic.`cid_driver_license`  " +
                " LEFT JOIN `business_customer` bc " +
                " ON c.cid=bc.`cid_tax_number`  WHERE c.cid = ?";

        List<CustomerVo> list =  jdbcTemplate.query(sql, (rs, rowNum)->{
            CustomerVo vo = new CustomerVo();
            vo.setCid(rs.getString("cid"));
            vo.setStreet(rs.getString("street"));
            vo.setCity(rs.getString("city"));
            vo.setState(rs.getString("state"));
            vo.setPostalCode(rs.getString("postal_code"));
            vo.setPhone(rs.getString("phone"));
            vo.setEmail(rs.getString("email"));
            vo.setCname(rs.getString("cname"));
            return vo;
        }, cid);

        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }
}
