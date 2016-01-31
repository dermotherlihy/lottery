package com.dermotherlihy.lottery.domain.exception;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public class MaxLinesExceededException extends RuntimeException {

    public MaxLinesExceededException(String message) {
        super(message);
    }

    public MaxLinesExceededException() {
    }
}
