package org.feuyeux.pattern.behavioral.template_method;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class UdsEngine extends Engine {
    @Override
    public void light() {
        log.info("UDS lighting");
    }

    @Override
    public void process() {
        log.info("UDS processing");
    }

    @Override
    public void ring() {
        log.info("UDS ringing");
    }
}
