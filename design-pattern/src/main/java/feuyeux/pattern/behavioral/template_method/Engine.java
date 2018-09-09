package feuyeux.pattern.behavioral.template_method;

public abstract class Engine {
    public void run() {
        light();
        process();
        ring();
    }

    public abstract void light();

    public abstract void process();

    public abstract void ring();

}
