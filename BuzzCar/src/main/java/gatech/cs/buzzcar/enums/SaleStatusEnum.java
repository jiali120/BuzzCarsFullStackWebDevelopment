package gatech.cs.buzzcar.enums;

public enum SaleStatusEnum {
    saleable,
    forbidden,
    sold;

    public String value(){
        return this.name();
    }

}
