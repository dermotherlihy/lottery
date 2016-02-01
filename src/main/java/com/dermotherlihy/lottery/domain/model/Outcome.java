package com.dermotherlihy.lottery.domain.model;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * Created by dermot.herlihy on 29/01/2016.
 **/
@Entity
public class Outcome implements Comparable<Outcome>{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToOne(optional=false,cascade=CascadeType.ALL
            ,targetEntity=Line.class)
    private Line line;

    private Integer result;

    public Outcome(){
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public void setResult(Integer result) {
        this.result = result;
    }



    public long getId() {
        return id;
    }

    public Line getLine() {
        return line;
    }

    public Integer getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outcome outcome = (Outcome) o;
        return Objects.equal(id, outcome.id) &&
                Objects.equal(line, outcome.line) &&
                Objects.equal(result, outcome.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, line, result);
    }

    @Override
    public int compareTo(Outcome otherOutcome) {

        return - this.getResult().compareTo(otherOutcome.getResult());
    }
}
