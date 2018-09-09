package feuyeux.pattern.behavioral.memento;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NluResult {
    private String state;
    private String value;
}
