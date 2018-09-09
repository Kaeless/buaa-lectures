package feuyeux.pattern.creational.factory_method;

import feuyeux.pattern.pojo.Nlu;
import feuyeux.pattern.pojo.StreamingNlu;

class StreamingFactory {
    public static Nlu getInstance() {
        return new StreamingNlu();
    }
}
