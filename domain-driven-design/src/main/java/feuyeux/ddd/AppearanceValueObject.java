package feuyeux.ddd;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class AppearanceValueObject {
    private String color;
    private String material;
    private String size;
}