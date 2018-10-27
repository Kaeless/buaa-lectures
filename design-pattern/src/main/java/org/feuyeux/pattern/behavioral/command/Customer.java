package org.feuyeux.pattern.behavioral.command;

public class Customer {
    private final Waitress service;

    public Customer(Waitress service) {
        this.service = service;
    }

    public Waitress getService() {
        return service;
    }
}
