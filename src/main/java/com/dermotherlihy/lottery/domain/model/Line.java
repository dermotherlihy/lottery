package com.dermotherlihy.lottery.domain.model;

/**
 * Created by dermot.herlihy on 28/01/2016.
 */
public class Line {

    private Number firstNumber;
    private Number secondNumber;
    private Number thirdNumber;

    public Line(Number numberOne, Number numberTwo, Number numberThree) {
        this.firstNumber = numberOne;
        this.secondNumber = numberTwo;
        this.thirdNumber = numberThree;
    }

    public Line(Number numberOne, Number numberTwo, Number numberThree, Integer result) {
        this.firstNumber = numberOne;
        this.secondNumber = numberTwo;
        this.thirdNumber = numberThree;

    }




}

