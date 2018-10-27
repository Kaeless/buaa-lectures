package org.feuyeux.pattern.behavioral.strategy.crazyif;

public class CrazyIfDevice {

    public static final String TABLET_1 = "Tablet1";
    public static final String TABLET_2 = "Tablet2";
    public static final String SET_TOP_BOX_1 = "SetTopBox1";
    public static final String SET_TOP_BOX_2 = "SetTopBox2";
    public static final String ANDROID_LINUX = "Android-Linux";
    public static final String ANDROID_C_LINUX = "Android-C-Linux";
    public static final String EUROPEAN = "European";
    public static final String CHINESE = "Chinese";
    public static final String OPENWRT = "openwrt";

    public String call(Device device) {
        if (TABLET_1.equals(device.getDeviceType())) {
            device.setProtocol(ANDROID_LINUX);
            return device.call("Hello Tablet1");
        } else if (TABLET_2.equals(device.getDeviceType())) {
            device.setProtocol(ANDROID_C_LINUX);
            return device.call("Hello Tablet2");
        } else if (SET_TOP_BOX_1.equals(device.getDeviceType())) {
            device.setProtocol(EUROPEAN);
            return device.call("Hello SetTopBox1");
        } else if (SET_TOP_BOX_2.equals(device.getDeviceType())) {
            device.setProtocol(CHINESE);
            return device.call("ni hao SetTopBox2");
            // else if ......
        } else {
            device.setProtocol(OPENWRT);
            return device.call("Hello Gateway105");
        }
    }
}
