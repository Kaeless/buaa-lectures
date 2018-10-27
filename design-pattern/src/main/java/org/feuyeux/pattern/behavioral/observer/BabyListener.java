package org.feuyeux.pattern.behavioral.observer;

public interface BabyListener extends java.util.EventListener {
    void onWakeUp(BabyEvent babyEvent);
}
