package com.dermotherlihy.lottery.domain.model;

import org.springframework.util.Assert;

import javax.persistence.*;

/**
 * Created by dermot.herlihy on 29/01/2016.
 **/
@Entity
public class Outcome {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToOne(optional=false,cascade=CascadeType.ALL
            ,targetEntity=Line.class)
    private Line line;

    private int result;

    protected Outcome(){
    }

    public Outcome(Line line) {
        Assert.notNull(line, "Line should never be null");
        this.line = line;
        generateResult();
    }

    //Maybe move this into factory
    private void generateResult(){
        if(line.getFirstNumber() + line.getSecondNumber() + line.getThirdNumber() == 2){
            result = 10;
        }
        else if((line.getFirstNumber() == line.getSecondNumber()) && (line.getSecondNumber() == line.getThirdNumber())){
            result = 5;
        }
        else if(line.getFirstNumber() != line.getSecondNumber() && line.getFirstNumber() != line.getThirdNumber()){
            result = 1;
        }else{
            result = 0;
        }
    }

    public long getId() {
        return id;
    }

    public Line getLine() {
        return line;
    }

    public int getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Outcome outcome = (Outcome) o;

        if (id != outcome.id) return false;
        if (result != outcome.result) return false;
        return !(line != null ? !line.equals(outcome.line) : outcome.line != null);

    }

    @Override
    public int hashCode() {
        int result1 = (int) (id ^ (id >>> 32));
        result1 = 31 * result1 + (line != null ? line.hashCode() : 0);
        result1 = 31 * result1 + result;
        return result1;
    }
}
