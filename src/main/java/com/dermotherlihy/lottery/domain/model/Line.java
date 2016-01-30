package com.dermotherlihy.lottery.domain.model;

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

        if (id != line.id) return false;
        if (firstNumber != null ? !firstNumber.equals(line.firstNumber) : line.firstNumber != null) return false;
        if (secondNumber != null ? !secondNumber.equals(line.secondNumber) : line.secondNumber != null) return false;
        return !(thirdNumber != null ? !thirdNumber.equals(line.thirdNumber) : line.thirdNumber != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstNumber != null ? firstNumber.hashCode() : 0);
        result = 31 * result + (secondNumber != null ? secondNumber.hashCode() : 0);
        result = 31 * result + (thirdNumber != null ? thirdNumber.hashCode() : 0);
        return result;
    }
}

