package org.example;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

@Converter(autoApply = true) //Класс является конвертером
public class PclassConverter implements AttributeConverter<Pclass, Integer> {
    public PclassConverter() {
        super();
    }

    @Override
    public Integer convertToDatabaseColumn(Pclass pclass) { //преобразует значение перечисления в числовое значение для сохранения  в БД
        return pclass != null ? pclass.toDbValue() : null;
    }

    @Override
    public Pclass convertToEntityAttribute(Integer integer) { //преобразует числовое значение из БД в значение пересиления
        return integer != null ? Pclass.fromDbValue(integer) : null;
    }
}
