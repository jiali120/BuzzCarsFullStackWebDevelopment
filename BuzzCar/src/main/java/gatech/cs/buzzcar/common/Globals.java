package gatech.cs.buzzcar.common;

import gatech.cs.buzzcar.enums.UserRoleEnum;

public class Globals {

    public static final int  HTTP_OK = 2000;

    public static final int  HTTP_ERROR = 5000;

    public static final String  HTTP_OK_MSG = "success";

    public static final String  HTTP_ERROR_MSG = "fail";
    public static final String  AUTH_TOKEN = "Auth-Token";

    public static final String MANAGER_AND_OWNER_PERM = UserRoleEnum.Manager.value()+","+UserRoleEnum.Owner.value();
    public static final String SALESPEOPLE_PERM = UserRoleEnum.Salespeople.value();
    public static final String INVENTORY_CLERKS_PERM = UserRoleEnum.InventoryClerks.value();
    public static final String MANAGER_PERM = UserRoleEnum.Manager.value();
    public static final String OWNER_PERM = UserRoleEnum.Owner.value();


}
