package com.grostech.flightscompany.flightmanagement.domain.addflight;

import com.grostech.flightscompany.flightmanagement.domain.Flight;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultAddFlightUseCase implements AddFlightUseCase {

    private final InsertFlightPort insertFlightPort;

    @Override
    public Flight addFlight(AddFlightCommand command) {
        return insertFlightPort.insert(mapToFlightToInsert(command));
    }

    private FlightToInsert mapToFlightToInsert(AddFlightCommand command) {
        return new FlightToInsert(command.getNumberOfSeats());
    }
}
