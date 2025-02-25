package gatech.cs.buzzcar.service.impl;

import gatech.cs.buzzcar.entity.dto.LoginDto;
import gatech.cs.buzzcar.entity.pojo.User;
import gatech.cs.buzzcar.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public User login(LoginDto loginDto) {
        String sql = "select * from user where username=? and password=? limit 1";
        List<User> users =  jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUserRole(rs.getString("user_role"));
            return user;
        }, loginDto.getUsername(), loginDto.getPassword());

        if(CollectionUtils.isEmpty(users)){
            return null;
        }else{
            return users.get(0);
        }
    }
}
