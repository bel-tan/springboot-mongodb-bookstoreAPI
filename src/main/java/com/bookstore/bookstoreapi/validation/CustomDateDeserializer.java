package com.bookstore.bookstoreapi.validation;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.bookstore.bookstoreapi.exception.DateFormatException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomDateDeserializer extends StdDeserializer<LocalDate> {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CustomDateDeserializer() {
        this(null);
    }

    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
        String fieldName = jsonparser.currentName();
        String date = jsonparser.getText();
        //MongoDB does not allow a NULL date. To set a convention default value.
        if(date==null || date.isEmpty()){
            date = "1900-01-01";
        }
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DateFormatException(fieldName + ": " + date + " is an invalid date format. Please provide date in the format of YYYY-MM-DD");
        }
    }
}
