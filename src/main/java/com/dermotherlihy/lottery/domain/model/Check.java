package com.dermotherlihy.lottery.domain.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */

@Entity
@Table(name="checks")
public class Check {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long ticketId;
    private Date created;

    @OneToMany(targetEntity=Outcome.class,
            fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Outcome> outcomes;

    public Check(){

    }

    public Check(long ticketId, List<Outcome> outcomes) {
        this.ticketId = ticketId;
        this.outcomes = outcomes;
        this.created = new Date();
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        return Objects.equal(id, check.id) &&
                Objects.equal(ticketId, check.ticketId) &&
                Objects.equal(created, check.created) &&
                Objects.equal(outcomes, check.outcomes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, ticketId, created, outcomes);
    }
}
