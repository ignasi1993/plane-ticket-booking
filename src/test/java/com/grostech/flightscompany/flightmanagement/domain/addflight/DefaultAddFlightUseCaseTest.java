package com.grostech.flightscompany.flightmanagement.domain.addflight;

import com.grostech.flightscompany.flightmanagement.domain.Flight;
import com.grostech.test.RandomProvider;
import com.grostech.util.validation.BeanValidator;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultAddFlightUseCaseTest {

    private InMemoryInsertFlightPort inMemoryInsertFlightPort;
    private DefaultAddFlightUseCase addFlightUseCase;

    @BeforeEach
    void setUp() {
        inMemoryInsertFlightPort = new InMemoryInsertFlightPort();
        addFlightUseCase = new DefaultAddFlightUseCase(inMemoryInsertFlightPort);
    }

    @Test
    void givenAddFlightCommandWithRandomPositiveNumberOfSeats_whenAddFlight_thenFlightIsInserted() {
        // given
        int randomNumberOfSeats = RandomProvider.randomStrictlyPositiveInt();
        AddFlightCommand command = AddFlightCommand.of(randomNumberOfSeats, new BeanValidator());

        // when
        Flight returnedFlight = addFlightUseCase.addFlight(command);

        // then
        assertNotNull(returnedFlight);
        assertNotNull(returnedFlight.getFlightId());
        Flight expectedFlight = new Flight(returnedFlight.getFlightId(), randomNumberOfSeats);
        assertEquals(expectedFlight, returnedFlight);
        assertEquals(expectedFlight, inMemoryInsertFlightPort.getFlights().get(returnedFlight.getFlightId()));
    }

    @Getter
    private static class InMemoryInsertFlightPort implements InsertFlightPort {

        private final Map<Flight.Id, Flight> flights = new HashMap<>();

        @Override
        public Flight insert(FlightToInsert flight) {
            Flight.Id flightId = Flight.Id.of(UUID.randomUUID().toString());
            Flight newFlight = new Flight(flightId, flight.getNumberOfSeats());
            flights.put(flightId, newFlight);
            return newFlight;
        }
    }
}
