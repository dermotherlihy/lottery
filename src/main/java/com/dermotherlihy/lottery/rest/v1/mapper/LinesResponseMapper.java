package com.dermotherlihy.lottery.rest.v1.mapper;

import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.rest.v1.resource.response.LineResponse;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public class LinesResponseMapper {

    public static LineResponse mapLineResponse(Line line){
        List<Integer> numberList  = Lists.newArrayList(line.getFirstNumber(),line.getSecondNumber(),line.getThirdNumber());
        LineResponse lineResponse = new LineResponse(numberList);
        return lineResponse;
    }
}
