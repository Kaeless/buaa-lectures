package org.feuyeux.pattern.structural.facade;

public class NluCore {
    public String q(String query) {
        if (condition()) {
            return "nlu for " + query;
        }
        return "unknown";
    }

    private boolean condition() {
        return System.nanoTime() % 2 == 1;
    }
}
