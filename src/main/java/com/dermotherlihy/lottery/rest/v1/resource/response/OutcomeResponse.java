package com.dermotherlihy.lottery.rest.v1.resource.response;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public class OutcomeResponse {

    private LineResponse lineResponse;
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
