package com.dermotherlihy.lottery.rest.v1.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
public class TicketResponse extends ResourceSupport {
    private final String content;

    @JsonCreator
    public TicketResponse(@JsonProperty("content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
