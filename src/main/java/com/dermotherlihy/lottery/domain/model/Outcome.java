package com.dermotherlihy.lottery.domain.model;

import org.springframework.util.Assert;

import javax.persistence.*;

/**
 * Created by dermot.herlihy on 29/01/2016.
 **/
@Entity
@Table(name ="OUTCOMES")
public class Outcome {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToOne(optional=false,cascade=CascadeType.ALL
            ,targetEntity=Line.class)
    private Line line;

    private int result;

    public Outcome(Line line) {
        Assert.notNull(line, "Line should never be null");
        this.line = line;
        calculateResult();
    }

    private void calculateResult(){
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


}
