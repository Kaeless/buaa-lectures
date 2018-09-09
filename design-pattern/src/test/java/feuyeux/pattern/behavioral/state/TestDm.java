package feuyeux.pattern.behavioral.state;

import org.junit.Test;

import java.util.UUID;

public class TestDm {
    @Test
    public void test() {
        Dm dm = new Dm();
        final String session = UUID.randomUUID().toString();
        dm.dialog(session, "what's time?");
        dm.dialog(session, "can i go there?");
    }
}
