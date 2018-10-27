package org.feuyeux.pattern.pojo;

import lombok.Data;

@Data
public class Uds {
    private Nlu nlu;
    private Qas qas;

    public Uds() {
    }

    public Uds(Nlu nlu, Qas qas) {
        this.nlu = nlu;
        this.qas = qas;
    }
}
