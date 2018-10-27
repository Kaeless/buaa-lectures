package org.feuyeux.pattern.structural.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestCompositeService {
    private Logger logger = LogManager.getLogger(TestCompositeService.class);

    @Test
    public void testCompositeCallback() {
        CompositeService compositeService = new CompositeService();
        compositeService.add(new UdsService());
        compositeService.add(new QasService());
        compositeService.reload();
    }
}
