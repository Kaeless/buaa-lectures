package feuyeux.pattern.behavioral.observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class BabySource {
    private final Logger logger = LogManager.getLogger(BabySource.class);
    private final String name;
    private final Set<BabyListener> wakeUpListenerSet = new LinkedHashSet<>();

    public BabySource(String name) {
        this.name = name;
    }

    public void registerWakeUpListener(BabyListener... wakeUpListeners) {
        Collections.addAll(wakeUpListenerSet, wakeUpListeners);
    }

    private void wakeUpNotify() {
        for (BabyListener aWakeUpListenerSet : wakeUpListenerSet) {
            aWakeUpListenerSet.onWakeUp(new BabyEvent(this));
        }
    }

    public String getName() {
        return name;
    }

    public void wakeUp() {
        logger.info("Ma Mi--");
        wakeUpNotify();
    }
}