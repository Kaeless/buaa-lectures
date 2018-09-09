package feuyeux.pattern.structural.bridge;

public class MessageNotify implements Notify {

    @Override
    public String send(String message) {
        return "send notify by mq:" + message;
    }
}
