package org.feuyeux.pattern.creational.prototype;

import org.feuyeux.pattern.creational.builder.UdsBuilder;
import org.feuyeux.pattern.pojo.Qas;
import org.feuyeux.pattern.pojo.SlotFillingNlu;
import org.feuyeux.pattern.pojo.Uds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class TestUdsClone {
    private static final Logger log = LogManager.getLogger(TestUdsClone.class);

    private UdsBuilder udsBuilder;

    @Before
    public void before() {
        udsBuilder = new UdsBuilder();
    }

    @Test
    public void test() {
        Uds uds = udsBuilder.nlu(new SlotFillingNlu()).qas(new Qas()).build();
        Uds uds2 = UdsClone.clone(uds);
        log.info(uds2);
        log.info(uds.equals(uds2));
    }

    /*
    public boolean compare(Uds one, Object o) {
        if (o == one) {
            return true;
        } else if (!(o instanceof Uds)) {
            return false;
        } else {
            Uds other = (Uds)o;
            if (!(other instanceof Uds)) {
                return false;
            } else {
                Object nlu = one.getNlu();
                Object other$nlu = other.getNlu();
                if (nlu == null) {
                    if (other$nlu != null) {
                        return false;
                    }
                } else if (!nlu.equals(other$nlu)) {
                    return false;
                }

                Object qas = one.getQas();
                Object other$qas = other.getQas();
                if (qas == null) {
                    if (other$qas != null) {
                        return false;
                    }
                } else if (!qas.equals(other$qas)) {
                    return false;
                }
                return true;
            }
        }
    }
    */
}
