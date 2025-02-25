package gatech.cs.buzzcar.service.impl;

import gatech.cs.buzzcar.entity.pojo.Color;
import gatech.cs.buzzcar.service.ColorService;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Color> queryColors() {
        String sql = "select vcolor from color";
        return jdbcTemplate.query(sql, (rs, rowNum)->{
            return new Color(rs.getString("vcolor"));
        });
    }
}
