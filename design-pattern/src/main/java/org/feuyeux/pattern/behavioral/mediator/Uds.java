package org.feuyeux.pattern.behavioral.mediator;

import lombok.Builder;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Builder
public class Uds extends Atom {
    @Override
    public String getName() {
        return "uds";
    }

    @Override
    public void receive(String message) {
        log.info("uds received: {}", message);
    }
}
