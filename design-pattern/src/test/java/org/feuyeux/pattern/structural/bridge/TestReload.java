package org.feuyeux.pattern.structural.bridge;

import org.junit.Test;

public class TestReload {
    @Test
    public void test() {
        Domo domo = new Domo(new MessageNotify());
        domo.reload();
    }
}
