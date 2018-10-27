package org.feuyeux.pattern.creational.builder;

import org.feuyeux.pattern.pojo.Nlu;
import org.feuyeux.pattern.pojo.Qas;
import org.feuyeux.pattern.pojo.Uds;

public class UdsBuilder {
    private Nlu nlu;
    private Qas qas;

    public UdsBuilder nlu(Nlu nlu) {
        this.nlu = nlu;
        return this;
    }

    public UdsBuilder qas(Qas qas) {
        this.qas = qas;
        return this;
    }

    public Uds build() {
        return new Uds(nlu, qas);
    }
}
