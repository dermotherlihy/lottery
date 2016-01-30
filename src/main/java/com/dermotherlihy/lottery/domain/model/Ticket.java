package com.dermotherlihy.lottery.domain.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dermot.herlihy on 28/01/2016.
 */
@Entity
@Table(name ="TICKETS")
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToMany(targetEntity=Line.class,
            fetch=FetchType.EAGER)
    private List<Line> lines;

    private Status status;


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
