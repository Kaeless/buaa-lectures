package feuyeux.pattern.behavioral.strategy.crazyif;

public class Device {
    private final String ip;
    private final String deviceType;
    private String protocol;

    public Device(String ip, String deviceType) {
        this.ip = ip;
        this.deviceType = deviceType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String call(String saying) {
        return protocol + "::" + ip + "::" + saying;
    }
}
