package org.feuyeux.pattern.behavioral.memento;

import lombok.Data;

@Data
public class Originator {
    private String state;
    private String value;

    public NluResult newNluResult() {
        return (NluResult.builder().state(state).value(value).build());
    }

    public void setNluResult(NluResult nluResult) {
        state = nluResult.getState();
        value = nluResult.getValue();
    }
}
