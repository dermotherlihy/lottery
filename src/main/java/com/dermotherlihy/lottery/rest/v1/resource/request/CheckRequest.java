package com.dermotherlihy.lottery.rest.v1.resource.request;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
public class CheckRequest {

    private long ticketId;

    public CheckRequest(long ticketId) {
        this.ticketId = ticketId;
    }
    public CheckRequest() {

    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
}
