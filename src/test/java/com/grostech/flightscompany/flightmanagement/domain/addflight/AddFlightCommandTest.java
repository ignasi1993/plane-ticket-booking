package com.grostech.flightscompany.flightmanagement.domain.addflight;

import com.grostech.util.validation.BeanValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddFlightCommandTest {

    @Test
    void givenValidNumberOfSeats_whenAddFlightCommandOf_thenNoExceptionIsThrown() {
        // given
        BeanValidator validator = new BeanValidator();

        // when
        Executable executable = () -> AddFlightCommand.of(1, validator);

        // then
        assertDoesNotThrow(executable);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void givenInvalidNumberOfSeats_whenAddFlightCommandOf_thenThrowValidationException(int invalidNumberOfSeats) {
        // given
        BeanValidator validator = new BeanValidator();

        // when
        Executable executable = () -> AddFlightCommand.of(invalidNumberOfSeats, validator);

        // then
        assertThrows(IllegalArgumentException.class, executable);
    }
}
