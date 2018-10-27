package org.feuyeux.pattern.creational.abstract_factory;

import org.feuyeux.pattern.pojo.Nlu;
import org.feuyeux.pattern.pojo.SlotFillingNlu;
import org.feuyeux.pattern.pojo.StreamingNlu;

class NluFactory {
    public static Nlu getSlotFillingNlu() {
        return new SlotFillingNlu();
    }

    public static Nlu getStreamingNlu() {
        return new StreamingNlu();
    }
}
