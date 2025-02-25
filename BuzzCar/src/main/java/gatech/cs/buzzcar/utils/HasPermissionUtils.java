package gatech.cs.buzzcar.utils;

import gatech.cs.buzzcar.enums.UserRoleEnum;
import org.apache.commons.lang3.ArrayUtils;

public class HasPermissionUtils {

    public static String permission(UserRoleEnum ... roleEnums){
        StringBuilder sb = new StringBuilder();
        if(ArrayUtils.isNotEmpty(roleEnums)){
            for (UserRoleEnum roleEnum : roleEnums) {
                sb.append(roleEnum.value()).append(",");
            }
        }
        return sb.toString();
    }
}
