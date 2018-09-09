package feuyeux.pattern.behavioral.command;

public class Customer {
    private Waitress service;

    public Customer(Waitress service) {
        this.service = service;
    }

    public Waitress getService() {
        return service;
    }
}
