package com.grostech.flightscompany.flightmanagement.infrastructure;

import com.grostech.flightscompany.flightmanagement.domain.Flight;
import com.grostech.flightscompany.flightmanagement.domain.addflight.FlightToInsert;
import com.grostech.util.id.IdProvider;
import com.grostech.util.id.RandomUUIDProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static com.grostech.test.AssertionsHelper.assertUUID;
import static com.grostech.test.RandomProvider.randomStrictlyPositiveInt;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryFlightRepositoryTest {

    @Test
    void givenFlightToInsertAndIdNotExistent_whenInsert_thenFlightIsSavedCorrectly() {
        // given
        int randomNumberOfSeats = randomStrictlyPositiveInt();
        FlightToInsert flightToInsert = new FlightToInsert(randomNumberOfSeats);
        InMemoryFlightRepository flightRepository = new InMemoryFlightRepository(new RandomUUIDProvider());
        int oldNumberOfFlights = flightRepository.getNumberOfFlights();

        // when
        Flight flight = flightRepository.insert(flightToInsert);

        // then
        assertNotNull(flight);
        assertNotNull(flight.getFlightId());
        assertUUID(flight.getFlightId().toString());
        assertEquals(randomNumberOfSeats, flight.getNumberOfSeats());
        assertEquals(oldNumberOfFlights + 1, flightRepository.getNumberOfFlights());
        assertEquals(flight, flightRepository.getAllFlights().get(flight.getFlightId()));
    }

    @Test
    void givenTooManyAttemptsAtGeneratingId_whenInsert_thenTooManyAttemptsAtGeneratingUniqueIdExceptionIsThrown() {
        // given
        int randomNumberOfSeats = randomStrictlyPositiveInt();
        FlightToInsert flightToInsert = new FlightToInsert(randomNumberOfSeats);
        String fixedUUID = UUID.randomUUID().toString();
        InMemoryFlightRepository flightRepository = new InMemoryFlightRepository(() -> fixedUUID);
        flightRepository.insert(flightToInsert);

        // when
        Executable executable = () -> flightRepository.insert(flightToInsert);

        // then
        assertThrows(TooManyAttemptsAtGeneratingUniqueIdException.class, executable);
    }

    @Test
    void givenFlightToInsertAndIdExistent_whenInsert_thenFlightIsSavedCorrectlyWithANewId() {
        // given
        FlightToInsert flightToInsert = new FlightToInsert(randomStrictlyPositiveInt());
        InMemoryFlightRepository flightRepository = new InMemoryFlightRepository(new IdProvider() {

            private String latestId;

            @Override
            public String provideId() {
                if (latestId != null) {
                    String idToReturn = latestId;
                    latestId = null;
                    return idToReturn;
                }
                latestId = UUID.randomUUID().toString();
                return latestId;
            }
        });
        int oldNumberOfFlights = flightRepository.getNumberOfFlights();
        Flight flight1 = flightRepository.insert(flightToInsert);

        // when
        Flight flight2 = flightRepository.insert(flightToInsert);

        // then
        assertEquals(oldNumberOfFlights + 2, flightRepository.getNumberOfFlights());
        assertNotNull(flight1);
        assertNotNull(flight2);
        assertNotEquals(flight1.getFlightId(), flight2.getFlightId());
    }
}
