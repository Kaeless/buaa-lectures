package org.feuyeux.pattern.behavioral.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoapOrder implements Order {
    private final Logger logger = LogManager.getLogger(getClass());
    private final SoapCook cook = SoapCook.getInstance();

    @Override
    public void execute() {
        logger.info("Making soap order...");
        cook.action();
    }
}
