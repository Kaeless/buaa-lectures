package org.feuyeux.ddd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "seatNumber")
public class SeatEntity {
    private String seatNumber;
    private AppearanceValueObject appearanceValueObject;
    private String serialNumber;
}
