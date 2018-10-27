package org.feuyeux.pattern.creational.factory_method;

import org.feuyeux.pattern.pojo.Nlu;
import org.feuyeux.pattern.pojo.StreamingNlu;

class StreamingFactory {
    public static Nlu getInstance() {
        return new StreamingNlu();
    }
}
