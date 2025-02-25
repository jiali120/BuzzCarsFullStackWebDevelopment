package gatech.cs.buzzcar.service.impl;

import gatech.cs.buzzcar.entity.pojo.VehicleType;
import gatech.cs.buzzcar.service.VehicleTypeService;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<VehicleType> queryVehicleTypes() {
        String sql = "select vtype from vehicle_type";
        return jdbcTemplate.query(sql, (rs, rowNum)->{
            VehicleType type = new VehicleType();
            type.setVtype(rs.getString("vtype"));
            return type;
        });
    }
}
