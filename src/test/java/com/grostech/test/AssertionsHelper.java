package com.grostech.test;

import lombok.experimental.UtilityClass;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@UtilityClass
public class AssertionsHelper {

    public static void assertUUID(String string) {
        assertNotNull(string);
        assertDoesNotThrow(() -> UUID.fromString(string));
    }
}
