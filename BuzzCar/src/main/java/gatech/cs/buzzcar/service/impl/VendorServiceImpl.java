package gatech.cs.buzzcar.service.impl;

import gatech.cs.buzzcar.entity.dto.VendorDto;
import gatech.cs.buzzcar.entity.vo.VendorVo;
import gatech.cs.buzzcar.service.VendorService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VendorServiceImpl implements VendorService {


    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveVendor(VendorDto dto) {
        int row;
        String countSql = "select count(1) from vendor where vendor_name=?";
        Integer cnt = jdbcTemplate.queryForObject(countSql, Integer.class, dto.getVendorName());
        if (Objects.nonNull(cnt) && cnt > 0) {
            row = -1;
        } else {
            String insertSql = "INSERT INTO `vendor`(`vendor_name`,`street`,`city`,`state`,`postal_code`,`vendor_phone_number`) VALUES(?,?,?,?,?,?)";
            row = jdbcTemplate.update(insertSql, dto.getVendorName(),
                    dto.getStreet(), dto.getCity(), dto.getState(), dto.getPostalCode(), dto.getVendorPhoneNumber());
        }
        return row;
    }

    @Override
    public List<VendorVo> queryVendorByName(String name) {
        List<Object> params = new ArrayList<>();
        String sql = "select * from vendor ";

        if(StringUtils.isNotBlank(name)){
            sql = sql + " where vendor_name like concat('%',?,'%') ";
            params.add(name);
        }

        return jdbcTemplate.query(sql, (rs, rowNum)->{
            VendorVo vo = new VendorVo();
            vo.setVendorName(rs.getString("vendor_name"));
            vo.setStreet(rs.getString("street"));
            vo.setCity(rs.getString("city"));
            vo.setState(rs.getString("state"));
            vo.setPostalCode(rs.getString("postal_code"));
            vo.setVendorPhoneNumber(rs.getString("vendor_phone_number"));
            return vo;
        }, params.toArray());
    }
}
