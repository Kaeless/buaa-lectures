package feuyeux.pattern.behavioral.observer;

public class BabyEvent extends java.util.EventObject {
    private static final long serialVersionUID = 1L;
    private BabySource source;

    public BabyEvent(BabySource source) {
        super(source);
        this.source = source;
    }

    @Override
    public BabySource getSource() {
        return source;
    }
}
