package com.dermotherlihy.lottery.rest.v1.resource;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
public class TicketRequest {

    private int numberOfLines;

    public TicketRequest(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }
}
