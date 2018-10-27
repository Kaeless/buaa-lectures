package org.feuyeux.pattern.behavioral.mediator;

import org.feuyeux.pattern.behavioral.visitor.AtomicVisitor;
import lombok.Builder;
import lombok.Setter;

@Builder
public class Mq {
    @Setter
    private AtomicVisitor visitor;

    public void send(Atom atom, String message) {
        switch (atom.getName()) {
            case "uds":
                visitor.accept((Uds)atom, message);
                break;
            case "domo":
                visitor.accept((Domo)atom, message);
                break;
            default:
        }
    }
}
