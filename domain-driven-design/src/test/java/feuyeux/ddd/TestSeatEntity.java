package feuyeux.ddd;

import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;

@Log4j2
public class TestSeatEntity {
    @Test
    public void test() {
        final AppearanceValueObject a1 = new AppearanceValueObject("BLUE", "plastic", "BIG");
        final AppearanceValueObject a2 = new AppearanceValueObject("RED", "plastic", "SMALL");
        SeatEntity s1 = new SeatEntity("E08-001", a1, "201601");
        SeatEntity s2 = new SeatEntity("E08-001", a2, "201801");
        log.info(s1.equals(s2));
        Assert.assertEquals(s1, s2);
        log.info(a1.equals(a2));
        Assert.assertNotEquals(a1, a2);
    }
}
