package feuyeux.pattern.structural.adapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestUdsCore {
    private static final Logger log = LogManager.getLogger(TestUdsCore.class);

    @Test
    public void test() {
        final QasCore qasCore = new QasCore();
        UdsCore udsCore = new UdsCore(qasCore);
        log.info(udsCore.dialog("hi"));
        log.info(udsCore.qa("hi"));
    }
}
