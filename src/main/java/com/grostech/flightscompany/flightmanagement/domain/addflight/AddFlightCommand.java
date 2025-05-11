package com.grostech.flightscompany.flightmanagement.domain.addflight;

import com.grostech.util.validation.BeanValidator;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AddFlightCommand {

    @Positive
    private final int numberOfSeats;

    public static AddFlightCommand of(int numberOfSeats, BeanValidator validator) {
        AddFlightCommand addFlightCommand = new AddFlightCommand(numberOfSeats);
        validator.validate(addFlightCommand);
        return addFlightCommand;
    }
}
