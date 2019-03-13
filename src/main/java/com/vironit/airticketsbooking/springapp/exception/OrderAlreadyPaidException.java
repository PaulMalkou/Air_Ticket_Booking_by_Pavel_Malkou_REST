package com.vironit.airticketsbooking.springapp.exception;

public class OrderAlreadyPaidException extends Exception {

    public OrderAlreadyPaidException(String message) {
        super(message);
    }

    public OrderAlreadyPaidException(Throwable cause) {
        super(cause);
    }

    public OrderAlreadyPaidException(String message, Throwable cause) {
        super(message, cause);
    }
}
