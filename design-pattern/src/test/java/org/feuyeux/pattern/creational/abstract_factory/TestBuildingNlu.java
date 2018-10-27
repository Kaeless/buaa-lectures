package org.feuyeux.pattern.creational.abstract_factory;

import org.feuyeux.pattern.pojo.Nlu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestBuildingNlu {
    private static final Logger log = LogManager.getLogger(TestBuildingNlu.class);

    @Test
    public void testBuild() {
        Nlu nlu1 = NluFactory.getSlotFillingNlu();
        Nlu nlu2 = NluFactory.getStreamingNlu();
        log.info(nlu1.whoAmI());
        log.info(nlu2.whoAmI());
    }
}
