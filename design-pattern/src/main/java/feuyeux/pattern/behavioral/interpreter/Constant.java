package feuyeux.pattern.behavioral.interpreter;

public class Constant extends Expression {
    private boolean value;

    public Constant(boolean value) {
        this.value = value;
    }

    @Override
    public boolean interpret(Context ctx) {
        return value;
    }

    @Override
    public String toString() {
        return Boolean.valueOf(value).toString();
    }
}