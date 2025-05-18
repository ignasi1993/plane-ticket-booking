package com.grostech.flightscompany.flightmanagement.infrastructure;

import com.grostech.flightscompany.flightmanagement.domain.Flight;
import com.grostech.flightscompany.flightmanagement.domain.addflight.FlightToInsert;
import com.grostech.flightscompany.flightmanagement.domain.addflight.InsertFlightPort;
import com.grostech.util.id.IdProvider;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class InMemoryFlightRepository implements InsertFlightPort { // TODO Implement Thread Safety and add Thread Safety tests

    private static final int MAX_NUMBER_OF_ID_GENERATION_ATTEMPTS = 5;

    private final ConcurrentHashMap<Flight.Id, Flight> flights = new ConcurrentHashMap<>();

    private final IdProvider idProvider;

    @Override
    public Flight insert(FlightToInsert flight) {
        Flight.Id flightId = generateUniqueFlightId();
        Flight savedFlight = new Flight(flightId, flight.getNumberOfSeats());
        flights.put(flightId, savedFlight);
        return savedFlight;
    }

    public Map<Flight.Id, Flight> getAllFlights() {
        return Map.copyOf(flights);
    }

    public int getNumberOfFlights() {
        return flights.size();
    }

    private Flight.Id generateUniqueFlightId() {
        for (int i = 0; i < MAX_NUMBER_OF_ID_GENERATION_ATTEMPTS; i++) {
            Flight.Id flightId = Flight.Id.of(idProvider.provideId());
            if (!flights.containsKey(flightId)) {
                return flightId;
            }
        }
        throw new TooManyAttemptsAtGeneratingUniqueIdException(MAX_NUMBER_OF_ID_GENERATION_ATTEMPTS);
    }
}
