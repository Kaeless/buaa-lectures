package feuyeux.pattern.structural;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class Counter {
    private AtomicLong count = new AtomicLong();

    public long add() {
        return count.addAndGet(1);
    }
}
