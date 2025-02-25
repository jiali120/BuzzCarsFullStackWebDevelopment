package gatech.cs.buzzcar.common;

import gatech.cs.buzzcar.entity.pojo.UserSecurity;

public class UserHolder {

    private static final ThreadLocal<UserSecurity> CURRENT_USER = new ThreadLocal<>();


    public static ThreadLocal<UserSecurity> getCurrentUser() {
        return CURRENT_USER;
    }

    public static void setCurrentUser(UserSecurity user){
        CURRENT_USER.set(user);
    }

    public static void clear(){
        CURRENT_USER.remove();
    }
}
