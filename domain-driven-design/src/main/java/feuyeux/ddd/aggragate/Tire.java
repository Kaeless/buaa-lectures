package feuyeux.ddd.aggragate;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Entity
 */
@Data
@AllArgsConstructor
public class Tire {
    private String id;
    private List<Position> positions;

    public double getMileAge() {
        return positions.parallelStream().mapToDouble(Position::getMileage).sum();
    }

    public double getMileAge2() {
        return positions.parallelStream()
            .map(p -> p.getMileage())
            .reduce((i, sum) -> sum += i).get();
    }
}
