package org.feuyeux.pattern.structural.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CompositeService {
    private static final Logger log = LogManager.getLogger(CompositeService.class);

    private final List<AtomicService> parts = new ArrayList<>();

    public void add(AtomicService atomicService) {
        parts.add(atomicService);
    }

    public void reload() {
        parts.forEach(a -> log.info(a.reload()));
    }
}
