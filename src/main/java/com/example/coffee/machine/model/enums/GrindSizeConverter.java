package com.example.coffee.machine.model.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GrindSizeConverter implements AttributeConverter<GrindSize, Character> {

    @Override
    public Character convertToDatabaseColumn(GrindSize grindSize) {
        if (grindSize == null) {
            return null;
        }
        return grindSize.getCode();
    }

    @Override
    public GrindSize convertToEntityAttribute(Character code) {
        if (code == null) {
            return null;
        }
        return Stream.of(GrindSize.values())
                .filter(s -> s.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
