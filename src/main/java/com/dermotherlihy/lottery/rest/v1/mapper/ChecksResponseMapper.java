package com.dermotherlihy.lottery.rest.v1.mapper;

import com.dermotherlihy.lottery.domain.model.Check;
import com.dermotherlihy.lottery.domain.model.Outcome;
import com.dermotherlihy.lottery.rest.v1.resource.response.CheckResponse;
import com.dermotherlihy.lottery.rest.v1.resource.response.LineResponse;
import com.dermotherlihy.lottery.rest.v1.resource.response.OutcomeResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */

public class ChecksResponseMapper {

    public static CheckResponse mapCheckResponse(Check check) {
        List<OutcomeResponse> outcomes = new ArrayList<OutcomeResponse>();
        for(Outcome outcome : check.getOutcomes()){
            outcomes.add(getOutcomeResponse(outcome));
        }
        return new CheckResponse(check.getId(), check.getTicketId(),outcomes);
    }

    private static OutcomeResponse getOutcomeResponse(Outcome outcome) {
        LineResponse lineResponse = LinesResponseMapper.mapLineResponse(outcome.getLine());
        OutcomeResponse outcomeResponse = new OutcomeResponse(lineResponse,outcome.getResult());
        return outcomeResponse;
    }
}
