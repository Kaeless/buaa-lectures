package org.feuyeux.pattern.behavioral.chain_of_responsibility;

public interface Gateway {
    Gateway next();

    void proccess();
}
