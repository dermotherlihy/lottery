package com.dermotherlihy.lottery.rest.v1.resource.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class LineResponse {

    @JsonProperty("numbers")
    private List<Integer> numbers;

    public LineResponse(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
