package gatech.cs.buzzcar.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *

 */
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Color implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String vcolor;

    public void setVcolor(String vcolor) {
        this.vcolor = vcolor;
    }

    @Override
    public String toString() {
        return "Color{" +
            "vcolor=" + vcolor +
        "}";
    }
}
