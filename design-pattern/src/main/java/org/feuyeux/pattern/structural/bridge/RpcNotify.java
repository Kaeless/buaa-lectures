package org.feuyeux.pattern.structural.bridge;

public class RpcNotify implements Notify {
    @Override
    public String send(String message) {
        return "send notify by rpc:" + message;
    }
}
