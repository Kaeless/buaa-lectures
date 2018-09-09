package feuyeux.pattern.structural.flyweight;

import org.junit.Test;

public class TestNluConnectionPool {
    @Test
    public void test() {
        UdsCore udsCore = new UdsCore();
        for (int i = 0; i < 3; i++) {
            udsCore.dialog("A", "hi");
        }
    }
}
