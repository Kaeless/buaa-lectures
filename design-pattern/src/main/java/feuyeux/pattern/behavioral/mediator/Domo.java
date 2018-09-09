package feuyeux.pattern.behavioral.mediator;

import lombok.Builder;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Builder
public class Domo extends Atom {
    @Override
    public String getName() {
        return "domo";
    }

    @Override
    public void receive(String message) {
        log.info("domo received: {}", message);
    }
}
