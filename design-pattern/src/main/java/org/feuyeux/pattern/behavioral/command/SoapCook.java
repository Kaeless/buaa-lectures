package org.feuyeux.pattern.behavioral.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoapCook {
    private static final SoapCook instance = new SoapCook();
    private final Logger logger = LogManager.getLogger(getClass());

    public static SoapCook getInstance() {
        return instance;
    }

    public void action() {
        logger.info("soap cooking...");
    }
}
