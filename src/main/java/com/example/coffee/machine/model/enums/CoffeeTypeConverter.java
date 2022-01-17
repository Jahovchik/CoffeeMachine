package com.example.coffee.machine.model.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CoffeeTypeConverter implements AttributeConverter<EspressoBasedCoffeeType, Character> {

    @Override
    public Character convertToDatabaseColumn(EspressoBasedCoffeeType coffeeType) {
        if (coffeeType == null) {
            return null;
        }
        return coffeeType.getCode();
    }

    @Override
    public EspressoBasedCoffeeType convertToEntityAttribute(Character code) {
        if (code == null) {
            return null;
        }
        return Stream.of(EspressoBasedCoffeeType.values())
                .filter(t -> t.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
