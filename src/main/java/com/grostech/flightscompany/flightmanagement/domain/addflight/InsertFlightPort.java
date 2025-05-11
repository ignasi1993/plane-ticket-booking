package com.grostech.flightscompany.flightmanagement.domain.addflight;

import com.grostech.flightscompany.flightmanagement.domain.Flight;

public interface InsertFlightPort {

    Flight insert(FlightToInsert flight);
}
