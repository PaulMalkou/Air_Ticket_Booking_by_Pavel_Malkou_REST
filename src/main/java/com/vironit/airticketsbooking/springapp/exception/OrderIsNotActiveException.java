package com.vironit.airticketsbooking.springapp.exception;

public class OrderIsNotActiveException extends Exception {

    public OrderIsNotActiveException(String message) {
        super(message);
    }

    public OrderIsNotActiveException(Throwable cause) {
        super(cause);
    }

    public OrderIsNotActiveException(String message, Throwable cause) {
        super(message, cause);
    }
}
