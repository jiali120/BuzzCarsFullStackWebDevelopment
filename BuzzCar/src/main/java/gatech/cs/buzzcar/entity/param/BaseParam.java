package gatech.cs.buzzcar.entity.param;

import lombok.Data;

@Data
public class BaseParam {
    private int page = 1;
    private int pageSize = 10;
}
