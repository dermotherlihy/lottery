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
}
