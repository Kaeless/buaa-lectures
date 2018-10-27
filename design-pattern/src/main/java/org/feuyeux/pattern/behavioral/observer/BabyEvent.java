package org.feuyeux.pattern.behavioral.observer;

public class BabyEvent extends java.util.EventObject {
    private static final long serialVersionUID = 1L;
    private final BabySource source;

    public BabyEvent(BabySource source) {
        super(source);
        this.source = source;
    }

    @Override
    public BabySource getSource() {
        return source;
    }
}
