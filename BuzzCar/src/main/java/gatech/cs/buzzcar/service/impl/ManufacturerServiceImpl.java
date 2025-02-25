package gatech.cs.buzzcar.service.impl;

import gatech.cs.buzzcar.entity.pojo.Manufacturer;
import gatech.cs.buzzcar.service.ManufacturerService;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Manufacturer> queryList() {
        String sql = "select vmaker from manufacturer";
        return jdbcTemplate.query(sql, (rs, rowNum)->{
            return new Manufacturer(rs.getString("vmaker"));
        });
    }
}
