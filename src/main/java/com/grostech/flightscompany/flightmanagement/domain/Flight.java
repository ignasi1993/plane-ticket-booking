package com.grostech.flightscompany.flightmanagement.domain;

import lombok.Value;

import static com.grostech.util.validation.ValidationUtils.nonBlank;

@Value
public class Flight {

    Id flightId;
    int numberOfSeats;

    @Value(staticConstructor = "of")
    public static class Id {

        String id;

        public Id(String id) {
            nonBlank(id, this.getClass().getName());
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
