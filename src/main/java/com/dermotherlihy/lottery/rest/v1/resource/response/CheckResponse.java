package com.dermotherlihy.lottery.rest.v1.resource.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
public class CheckResponse extends ResourceSupport{
    @JsonProperty("checkId")
    private long checkId;
    @JsonProperty("ticketId")
    private long ticketId;
    @JsonProperty("outcomes")
    private List<OutcomeResponse> outcomes;


    public CheckResponse(long checkId, long ticketId, List<OutcomeResponse> outcomes) {
        this.checkId = checkId;
        this.ticketId = ticketId;
        this.outcomes = outcomes;
    }

    public long getCheckId() {
        return checkId;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public List<OutcomeResponse> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<OutcomeResponse> outcomes) {
        this.outcomes = outcomes;
    }
}
