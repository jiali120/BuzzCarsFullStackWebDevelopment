package gatech.cs.buzzcar.enums;

public enum UserRoleEnum {
    InventoryClerks,
    Manager,
    Owner,
    Salespeople;

    public String value(){
        return this.name();
    }
}
