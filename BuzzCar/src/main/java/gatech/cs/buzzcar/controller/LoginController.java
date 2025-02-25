package gatech.cs.buzzcar.controller;

import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.dto.LoginDto;
import gatech.cs.buzzcar.entity.pojo.User;
import gatech.cs.buzzcar.service.UserService;
import gatech.cs.buzzcar.utils.JwtTokenUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginDto loginDto){
        User user = userService.login(loginDto);
        if(Objects.nonNull(user)){
            String token = JwtTokenUtil.generateToken(loginDto.getUsername(), user.getUserRole());
            return Result.successData(token);
        }else{
            return Result.fail("login failure");
        }
    }


}
