package feuyeux.pattern.behavioral.strategy;

public class DotCallback implements ICallback {

    @Override
    public void invoke(String info) {
        System.out.println("...\t" + info);
    }
}
