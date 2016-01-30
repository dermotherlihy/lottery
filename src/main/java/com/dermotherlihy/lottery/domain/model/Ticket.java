package com.dermotherlihy.lottery.domain.model;

import javax.persistence.*;
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

    private Status status;

    public Ticket(){

    }

    public Ticket(List<Line> lines, Status status) {
        this.lines = lines;
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (id != ticket.id) return false;
        if (lines != null ? !lines.equals(ticket.lines) : ticket.lines != null) return false;
        return status == ticket.status;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (lines != null ? lines.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
