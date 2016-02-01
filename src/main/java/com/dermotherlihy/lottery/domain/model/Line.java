package com.dermotherlihy.lottery.domain.model;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * Created by dermot.herlihy on 28/01/2016.
 */
@Entity
@Table(name ="LINES")
public class Line {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private Integer firstNumber;
    private Integer secondNumber;
    private Integer thirdNumber;

    public Line(){

    }

    public Line(Integer numberOne, Integer numberTwo, Integer numberThree) {
        this.firstNumber = numberOne;
        this.secondNumber = numberTwo;
        this.thirdNumber = numberThree;
    }

    public long getId() {
        return id;
    }

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }

    public Integer getThirdNumber() {
        return thirdNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equal(id, line.id) &&
                Objects.equal(firstNumber, line.firstNumber) &&
                Objects.equal(secondNumber, line.secondNumber) &&
                Objects.equal(thirdNumber, line.thirdNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstNumber, secondNumber, thirdNumber);
    }
}

