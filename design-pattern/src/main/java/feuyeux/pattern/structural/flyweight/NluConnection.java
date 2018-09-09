package feuyeux.pattern.structural.flyweight;

import lombok.Getter;
import lombok.Setter;

public class NluConnection {
    @Getter
    @Setter
    private String name;

    public String ask(String query) {
        return "response from nlu for:" + query;
    }
}
