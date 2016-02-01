package com.dermotherlihy.lottery.domain.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by dermot.herlihy on 28/01/2016.
 */
@Entity
@Table(name="tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Line> lines;

    private Date created;

    private Date modified;

    private Status status;

    public Ticket(){

    }

    public Ticket(List<Line> lines, Status status) {
        this.lines = lines;
        this.status = status;
        this.created = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equal(id, ticket.id) &&
                Objects.equal(lines, ticket.lines) &&
                Objects.equal(created, ticket.created) &&
                Objects.equal(modified, ticket.modified) &&
                Objects.equal(status, ticket.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, lines, created, modified, status);
    }
}
