package feuyeux.pattern.behavioral.command;

import org.junit.Test;

public class TestCookCommand {
    @Test
    public void testCookOrdering() {
        Customer xiaoMing = new Customer(new Waitress());
        xiaoMing.getService().takeOrder();
    }
}
