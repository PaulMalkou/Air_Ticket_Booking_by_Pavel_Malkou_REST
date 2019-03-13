package com.vironit.airticketsbooking.springapp.exception;

public class OrderAlreadyExistsException extends Exception {

    public OrderAlreadyExistsException(String message) {
        super(message);
    }

    public OrderAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public OrderAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
