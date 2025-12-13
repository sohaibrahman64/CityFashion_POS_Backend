package com.cityfashionpos.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SQLiteLocalDateTimeConverter implements AttributeConverter<LocalDateTime, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute != null ? attribute.format(FORMATTER) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dbData) {
        return dbData != null ? LocalDateTime.parse(dbData, FORMATTER) : null;
    }
}
