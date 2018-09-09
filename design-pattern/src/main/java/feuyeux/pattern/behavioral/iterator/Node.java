package feuyeux.pattern.behavioral.iterator;

import lombok.Data;

@Data
public class Node {
    private String ip;

    public Node(String ip) {
        this.ip = ip;
    }
}
