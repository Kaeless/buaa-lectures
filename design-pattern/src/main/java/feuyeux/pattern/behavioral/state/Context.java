package feuyeux.pattern.behavioral.state;

import lombok.Builder;
import lombok.Setter;

@Builder
public class Context {
    @Setter
    private State state;

    public String hello() {
        switch (state) {
            case NEW:
                return "welcome";
            default:
            case RUNNING:
                return "hey";
        }
    }
}
