package com.dermotherlihy.lottery.rest.v1.mapper;

import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.rest.v1.resource.response.LineResponse;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public class LineResponseMapper {

    public static LineResponse mapLineResponse(Line line){
        LineResponse lineResponse = new LineResponse(line.getFirstNumber(), line.getSecondNumber(),
                line.getThirdNumber());
        return lineResponse;
    }
}
