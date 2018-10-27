package org.feuyeux.pattern.behavioral.mediator;

import org.feuyeux.pattern.behavioral.visitor.AtomicVisitor;
import org.junit.Test;

public class TestMq {
    @Test
    public void test() {
        Mq mediator = Mq.builder().build();
        Domo domo = Domo.builder().build();
        domo.setMq(mediator);
        Uds uds = Uds.builder().build();
        uds.setMq(mediator);

        AtomicVisitor visitor = AtomicVisitor.builder().domo(domo).uds(uds).build();
        mediator.setVisitor(visitor);

        domo.send("Please reload");
        uds.send("Copy, I will reload");
        uds.send("Done");
        domo.send("Roger, I will update the state.");
    }
}
