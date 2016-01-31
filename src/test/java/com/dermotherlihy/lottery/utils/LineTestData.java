package com.dermotherlihy.lottery.utils;

import com.dermotherlihy.lottery.domain.model.Line;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class LineTestData {

    public static Line getLineWorth5(){
        return new Line(0,0,0);
    }

    public static Line getLineWorth10(){
        return new Line(0,0,2);
    }

    public static Line getLineWorth0(){
        return new Line(0,0,1);
    }
}
