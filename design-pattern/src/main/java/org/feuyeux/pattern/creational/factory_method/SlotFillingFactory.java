package org.feuyeux.pattern.creational.factory_method;

import org.feuyeux.pattern.pojo.Nlu;
import org.feuyeux.pattern.pojo.SlotFillingNlu;

class SlotFillingFactory {
    public static Nlu getInstance() {
        return new SlotFillingNlu();
    }
}
