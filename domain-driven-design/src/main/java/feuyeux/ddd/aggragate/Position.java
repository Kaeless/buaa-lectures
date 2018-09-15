package feuyeux.ddd.aggragate;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Value Object
 */
@Data
@Builder
public class Position {
    private Date time;
    private double mileage;
}
