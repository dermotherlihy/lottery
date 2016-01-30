package com.dermotherlihy.lottery.domain.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */


public class Check {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long ticketId;
    @OneToMany(targetEntity=Outcome.class,
            fetch= FetchType.EAGER)
    private List<Outcome> outcomes;

    public Check(){

    }

    public Check(long ticketId, List<Outcome> outcomes) {
        this.ticketId = ticketId;
        this.outcomes = outcomes;
    }

    public long getId() {
        return id;
    }

    public long getTicketId() {
        return ticketId;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }
}
