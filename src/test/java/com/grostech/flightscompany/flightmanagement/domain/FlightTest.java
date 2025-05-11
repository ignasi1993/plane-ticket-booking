package com.grostech.flightscompany.flightmanagement.domain;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FlightTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "    "})
    @NullSource
    void givenInvalidFlightId_whenFlightOf_thenThrowIllegalArgumentException(String invalidFlightId) {
        // given - when
        Executable executable = () -> Flight.Id.of(invalidFlightId);

        // then
        assertThrows(IllegalArgumentException.class, executable);
    }
}
