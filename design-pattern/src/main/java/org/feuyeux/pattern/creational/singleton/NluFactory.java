package org.feuyeux.pattern.creational.singleton;

import org.feuyeux.pattern.pojo.Nlu;
import org.feuyeux.pattern.pojo.SlotFillingNlu;

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
