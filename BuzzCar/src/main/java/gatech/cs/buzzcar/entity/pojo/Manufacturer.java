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
public class Manufacturer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private String vmaker;

    public void setVmaker(String vmaker) {
        this.vmaker = vmaker;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
            "vmaker=" + vmaker +
        "}";
    }
}
