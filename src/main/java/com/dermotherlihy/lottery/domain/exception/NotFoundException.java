package com.dermotherlihy.lottery.domain.exception;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}
