package feuyeux.pattern.behavioral.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DessertOrder implements Order {
    private final Logger logger = LogManager.getLogger(getClass());
    private final DessertCook cook = DessertCook.getInstance();

    @Override
    public void execute() {
        logger.info("Making dessert order...");
        cook.action();
    }
}
