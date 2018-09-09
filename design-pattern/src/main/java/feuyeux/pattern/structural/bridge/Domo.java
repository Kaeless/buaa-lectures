package feuyeux.pattern.structural.bridge;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Domo {
    private Notify notify;

    public Domo(Notify notify) {
        this.notify = notify;
    }

    public void reload() {
        String message = "reload group A";
        log.info(notify.send(message));

        message = "unload group B";
        log.info(notify.send(message));
    }
}
