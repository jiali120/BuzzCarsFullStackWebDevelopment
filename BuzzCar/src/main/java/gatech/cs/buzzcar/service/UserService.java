package gatech.cs.buzzcar.service;

import gatech.cs.buzzcar.entity.dto.LoginDto;
import gatech.cs.buzzcar.entity.pojo.User;

public interface UserService {
    User login(LoginDto loginDto);
}
