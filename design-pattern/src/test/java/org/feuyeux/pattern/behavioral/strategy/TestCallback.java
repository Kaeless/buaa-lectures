package org.feuyeux.pattern.behavioral.strategy;

import org.junit.Test;

public class TestCallback {

    private final int callTime = 3;

    @Test
    public void testDotCallback() {
        System.out.println("Test Dot Callback:");
        Execute callee = new Execute();
        callee.service(new DotCallback(), callTime);
    }

    @Test
    public void testSlashCallback() {
        System.out.println("\nTest Slash Callback:");
        Execute callee = new Execute();
        callee.service(new SlashCallback(), callTime);
    }

    @Test
    public void testAnonymousCallback() {
        System.out.println("\nTest Anonymous Callback:");
        Execute callee = new Execute();
        callee.service(info -> System.out.println("Anonymous invoke: " + info), callTime);
    }
}
