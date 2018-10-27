package org.feuyeux.pattern.behavioral.iterator;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Log4j2
public class Group {
    private final List<Node> l = new ArrayList<>();

    public void add(Node node) {
        l.add(node);
    }

    public void check() {
        for (Node next : l) {
            log.info(next.getIp());
        }

        final Iterator<Node> iter = l.iterator();
        while (iter.hasNext()) {
            Node next = iter.next();
            log.info(next.getIp());
        }

        l.forEach(n -> log.info(n.getIp()));
    }
}
