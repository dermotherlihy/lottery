package com.dermotherlihy.lottery.rest.v1.resource.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class LineResponse {

    @JsonProperty("firstNumber")
    private Integer firstNumber;
    @JsonProperty("secondNumber")
    private Integer secondNumber;
    @JsonProperty("thirdNumber")
    private Integer thirdNumber;

    public LineResponse(Integer firstNumber, Integer secondNumber, Integer thirdNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
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
}
