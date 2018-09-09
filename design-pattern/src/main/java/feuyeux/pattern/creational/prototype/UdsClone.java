package feuyeux.pattern.creational.prototype;

import feuyeux.pattern.pojo.Uds;
import org.springframework.beans.BeanUtils;

public class UdsClone {
    public static Uds clone(Uds source) {
        Uds sink = new Uds();
        BeanUtils.copyProperties(source, sink);
        return sink;
    }
}
