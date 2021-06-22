package kasyan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    private double USD_in;
    private double USD_out;
    private long sap_id;
    private String street;
}
