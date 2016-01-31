package com.dermotherlihy.lottery.rest.v1.resource.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
public class TicketResponse extends ResourceSupport {

    @JsonProperty("ticketId")
    private long ticketId;

    @JsonProperty("lines")
    private List<LineResponse> lines;

    @JsonProperty("status")
    private String status;

    @JsonCreator
    public TicketResponse() {

    }

    public long getTicketId() {
        return ticketId;
    }

    public List<LineResponse> getLines() {
        return lines;
    }

    public void setLines(List<LineResponse> lines) {
        this.lines = lines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
}
