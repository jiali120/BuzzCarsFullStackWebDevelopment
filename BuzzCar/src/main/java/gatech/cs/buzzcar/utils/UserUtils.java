package gatech.cs.buzzcar.utils;

import gatech.cs.buzzcar.common.UserHolder;
import gatech.cs.buzzcar.entity.pojo.UserSecurity;

import java.util.Objects;

public class UserUtils {

    public static String getUsername(){
        UserSecurity userSecurity = UserHolder.getCurrentUser().get();
        if(Objects.nonNull(userSecurity)){
            return userSecurity.getUsername();
        }else{
            return null;
        }
    }

    public static String getUserRole(){
        UserSecurity userSecurity = UserHolder.getCurrentUser().get();
        if(Objects.nonNull(userSecurity)){
            return userSecurity.getRole();
        }else{
            return null;
        }

    }
}
