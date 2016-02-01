package com.dermotherlihy.lottery.rest.v1.resource.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public class OutcomeResponse {
    @JsonProperty("line")
    private LineResponse lineResponse;
    @JsonProperty("result")
    private int result;

    public OutcomeResponse(LineResponse lineResponse, int result) {
        this.lineResponse = lineResponse;
        this.result = result;
    }

    public LineResponse getLineResponse() {
        return lineResponse;
    }

    public void setLineResponse(LineResponse lineResponse) {
        this.lineResponse = lineResponse;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
