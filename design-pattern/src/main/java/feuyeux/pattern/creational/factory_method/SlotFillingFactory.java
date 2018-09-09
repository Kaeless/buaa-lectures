package feuyeux.pattern.creational.factory_method;

import feuyeux.pattern.pojo.Nlu;
import feuyeux.pattern.pojo.SlotFillingNlu;

class SlotFillingFactory {
    public static Nlu getInstance() {
        return new SlotFillingNlu();
    }
}
