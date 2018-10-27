package org.feuyeux.pattern.behavioral.strategy;

public class SlashCallback implements ICallback {

    @Override
    public void invoke(String info) {
        System.out.println("///\t" + info);
    }
}
