package org.feuyeux.ddd.aggragate;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Entity, Aggregate root
 */
@ToString
@AllArgsConstructor
public class Car {
    private String vehicleId;
    private Wheel[] wheels;
    private Tire[] tires;
    private static final int MAX_INDEX = 3;

    public void rotate(int index, final Wheel w, final Tire t) {
        if (index > MAX_INDEX || index < 0) {
            return;
        }
        Wheel.WheelLocal local = wheels[index].getLocal();
        wheels[index] = w;
        wheels[index].setLocal(local);
        tires[index] = t;
    }
}
