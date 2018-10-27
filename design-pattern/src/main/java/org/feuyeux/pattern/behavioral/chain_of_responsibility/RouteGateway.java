package org.feuyeux.pattern.behavioral.chain_of_responsibility;

import lombok.Builder;
import lombok.extern.log4j.Log4j2;

@Builder
@Log4j2
public class RouteGateway implements Gateway {
    private Gateway next;

    public RouteGateway(Gateway next) {
        this.next = next;
    }

    @Override
    public Gateway next() {
        return next;
    }

    @Override
    public void proccess() {
        log.info("RouteGateway choose node to accept the request");
        if (next() != null) {
            next().proccess();
        }
    }
}
