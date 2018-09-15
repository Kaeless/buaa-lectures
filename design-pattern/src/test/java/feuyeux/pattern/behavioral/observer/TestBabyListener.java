package feuyeux.pattern.behavioral.observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestBabyListener {
    private final Logger logger = LogManager.getLogger(TestBabyListener.class);

    @Test
    public void testListener() {
        BabySource boy = new BabySource("Tom");
        boy.registerWakeUpListener(babyEvent -> logger.info("{} wake up!", babyEvent.getSource().getName()));
        boy.wakeUp();
    }
}
