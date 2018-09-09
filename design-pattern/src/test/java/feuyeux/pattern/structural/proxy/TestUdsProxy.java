package feuyeux.pattern.structural.proxy;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

@Log4j2
public class TestUdsProxy {
    @Test
    public void test() {
        UdsProxy udsProxy = new UdsProxy(new UdsCore());
        for (int i = 0; i < 10; i++) {
            log.info(udsProxy.dialog("q"));
        }
    }
}
