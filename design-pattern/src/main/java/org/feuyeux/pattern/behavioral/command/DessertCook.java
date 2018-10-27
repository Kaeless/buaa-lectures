package org.feuyeux.pattern.behavioral.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DessertCook {
    private static final DessertCook instance = new DessertCook();
    private final Logger logger = LogManager.getLogger(getClass());

    public static DessertCook getInstance() {
        return instance;
    }

    public void action() {
        logger.info("dessert cooking...");
    }
}
