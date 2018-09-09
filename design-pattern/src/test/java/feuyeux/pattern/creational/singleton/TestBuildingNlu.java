package feuyeux.pattern.creational.singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestBuildingNlu {
    private static final Logger log = LogManager.getLogger(TestBuildingNlu.class);

    @Test
    public void testBuild() {
        log.info(NluFactory.getNlu().whoAmI());
        log.info(NluFactory.getNlu().whoAmI());
    }
}
