package feuyeux.pattern.creational.singleton;

import feuyeux.pattern.pojo.Nlu;
import feuyeux.pattern.pojo.SlotFillingNlu;

class NluFactory {
    private static Nlu nlu;

    private NluFactory() {}

    synchronized public static Nlu getNlu() {
        if (nlu == null) {
            nlu = new SlotFillingNlu();
        }
        return nlu;
    }
}
