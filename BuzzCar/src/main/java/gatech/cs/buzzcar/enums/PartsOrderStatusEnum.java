package gatech.cs.buzzcar.enums;

public enum PartsOrderStatusEnum {
    ordered,
    received,
    installed;

    public String value(){
        return this.name();
    }
}
