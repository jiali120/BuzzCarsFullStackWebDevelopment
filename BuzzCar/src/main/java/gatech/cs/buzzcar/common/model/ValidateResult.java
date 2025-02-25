package gatech.cs.buzzcar.common.model;

import gatech.cs.buzzcar.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ValidateResult {

    private String field;

    private String message;


    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

}
