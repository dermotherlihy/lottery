package com.dermotherlihy.lottery.rest.v1.resource;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
public class TicketRequest {

    private Integer numberOfLines;

    public TicketRequest(){

    }

    public TicketRequest(Integer numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public Integer getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(Integer numberOfLines) {
        this.numberOfLines = numberOfLines;
    }
}
