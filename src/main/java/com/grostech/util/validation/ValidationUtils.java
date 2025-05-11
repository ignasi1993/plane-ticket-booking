package com.grostech.util.validation;

public class ValidationUtils {

    public static void nonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(String.format("%s cannot be null or blank", fieldName));
        }
    }
}
