package org.feuyeux.pattern.structural.facade;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

@Log4j2
public class TestDmCore {
    @Test
    public void test() {
        QasCore qasCore = new QasCore();
        NluCore nluCore = new NluCore();
        FnCore fnCore = new FnCore();
        DmCore dmCore = new DmCore(nluCore, qasCore, fnCore);
        for (int i = 0; i < 10; i++) {
            log.info(dmCore.dialog("hello"));
        }
    }
}
