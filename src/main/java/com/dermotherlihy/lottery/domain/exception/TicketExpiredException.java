package com.dermotherlihy.lottery.domain.exception;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class TicketExpiredException extends RuntimeException {
    public TicketExpiredException(String message) {
        super(message);
    }

    public TicketExpiredException() {
    }
}
