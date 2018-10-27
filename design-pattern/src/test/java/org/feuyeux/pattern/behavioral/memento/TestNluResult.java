package org.feuyeux.pattern.behavioral.memento;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

@Log4j2
public class TestNluResult {
    @Test
    public void test() {
        /**
         * 发起者
         */
        Originator o = new Originator();
        o.setState("ON");
        o.setValue("TV");
        log.info("BEGIN={}", o);

        /**
         * 看管者
         */
        Caretaker c = new Caretaker();
        c.setNluResult(o.newNluResult());
        o.setState("OFF");
        o.setValue("CAR");
        log.info("AFTER={}", o);

        o.setNluResult(c.getNluResult());
        log.info("FINALLY={}", o);
    }
}
