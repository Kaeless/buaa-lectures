package feuyeux.pattern.behavioral.mediator;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class Atom {
    @Setter
    private Mq mq;

    public void send(String message) {
        log.info("{} say:{}", this.getName(), message);
        mq.send(this, message);
    }

    public abstract String getName();

    public abstract void receive(String message);
}
