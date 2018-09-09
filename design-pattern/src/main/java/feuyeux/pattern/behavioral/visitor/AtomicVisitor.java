package feuyeux.pattern.behavioral.visitor;

import feuyeux.pattern.behavioral.mediator.Domo;
import feuyeux.pattern.behavioral.mediator.Uds;
import lombok.Builder;
import lombok.Setter;

@Builder
public class AtomicVisitor {
    @Setter
    private Uds uds;
    @Setter
    private Domo domo;

    public void accept(Uds uds, String message) {
        domo.receive(message);
    }

    public void accept(Domo domo, String message) {
        uds.receive(message);
    }
}
