package com.grostech.flightscompany.flightmanagement.infrastructure;

public class TooManyAttemptsAtGeneratingUniqueIdException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Too many attempts at generating unique ID, attempted %d times";

    public TooManyAttemptsAtGeneratingUniqueIdException(int attempts) {
        super(String.format(ERROR_MESSAGE, attempts));
    }
}
