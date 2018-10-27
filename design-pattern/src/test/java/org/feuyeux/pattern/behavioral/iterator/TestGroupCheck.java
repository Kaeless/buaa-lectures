package org.feuyeux.pattern.behavioral.iterator;

import org.junit.Test;

public class TestGroupCheck {
    @Test
    public void test() {
        Group group = new Group();
        group.add(new Node("1.2.3.4"));
        group.add(new Node("1.2.3.5"));
        group.check();
    }
}
