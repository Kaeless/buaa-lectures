package feuyeux.pattern.creational.abstract_factory;

import feuyeux.pattern.pojo.Nlu;
import feuyeux.pattern.pojo.SlotFillingNlu;
import feuyeux.pattern.pojo.StreamingNlu;

class NluFactory {
    public static Nlu getSlotFillingNlu() {
        return new SlotFillingNlu();
    }

    public static Nlu getStreamingNlu() {
        return new StreamingNlu();
    }
}
