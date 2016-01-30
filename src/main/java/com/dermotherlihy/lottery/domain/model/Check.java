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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        if (id != check.id) return false;
        if (ticketId != check.ticketId) return false;
        return !(outcomes != null ? !outcomes.equals(check.outcomes) : check.outcomes != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (ticketId ^ (ticketId >>> 32));
        result = 31 * result + (outcomes != null ? outcomes.hashCode() : 0);
        return result;
    }
}
