package feuyeux.pattern.structural.proxy;

public class UdsProxy {
    private UdsCore uds;

    public UdsProxy(UdsCore uds) {
        this.uds = uds;
    }

    public String dialog(String query) {
        if (condition()) {
            return uds.dialog(query);
        } else {
            return "3rd party action for " + query;
        }
    }

    private boolean condition() {
        return System.nanoTime() % 2 == 1;
    }
}
