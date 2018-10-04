package org.feuyeux.ddd.aggragate;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class TestTire {
    @Test
    public void test() {
        List<Position> positions = Arrays.asList(
            Position.builder().mileage(5.1).build(),
            Position.builder().mileage(4.2).build(),
            Position.builder().mileage(10.8).build(),
            Position.builder().mileage(1.5).build());
        Tire t = new Tire("1", positions);
        log.info(t.getMileAge());
        log.info(t.getMileAge2());
    }
}
