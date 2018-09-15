package feuyeux.pattern.structural.flyweight;

import lombok.Getter;
import lombok.Setter;

public final class NluConnection {
    @Getter
    @Setter
    private String name;

    public String ask(String query) {
        return "response from nlu for:" + query;
    }
}
