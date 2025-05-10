package com.grostech.flightscompany.flightmanagement.domain.addflight;

import jakarta.validation.constraints.Positive;

public record AddFlightCommand(@Positive int availableSeats) {
}
