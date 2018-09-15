package feuyeux.ddd.aggragate;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Log4j2
public class TestCar {
    @Test
    public void test() {
        List<Position> positions = Arrays.asList(
            Position.builder().mileage(5.1).build(),
            Position.builder().mileage(4.2).build(),
            Position.builder().mileage(10.8).build(),
            Position.builder().mileage(1.5).build());

        Car car = new Car("0001",
            Stream.of(new Wheel("1", Wheel.WheelLocal.LF),
                new Wheel("2", Wheel.WheelLocal.LR),
                new Wheel("3", Wheel.WheelLocal.RF),
                new Wheel("4", Wheel.WheelLocal.RR)).toArray(Wheel[]::new),
            Stream.of(new Tire("1", positions),
                new Tire("2", positions),
                new Tire("3", positions),
                new Tire("4", positions)).toArray(Tire[]::new)
        );
        car.rotate(0, new Wheel("5", null), new Tire("5",positions));
        log.info(car);
    }
}
