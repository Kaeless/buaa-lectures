package org.feuyeux.pattern.pojo;

public interface Nlu {
    default String whoAmI() {
        return getClass().getCanonicalName();
    }
}
