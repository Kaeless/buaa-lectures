package org.feuyeux.ddd.aggragate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wheel {
    private String id;
    private WheelLocal local;

    public enum WheelLocal {
        /**
         *
         */
        LF,
        LR,
        RF,
        RR
    }
}