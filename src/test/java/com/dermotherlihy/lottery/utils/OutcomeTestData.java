package com.dermotherlihy.lottery.utils;

import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Outcome;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public class OutcomeTestData {

    public static Outcome generateOutcomeWith5(){
        Line line = LineTestData.getLineWorth5();
        Outcome outcome = new Outcome();
        outcome.setLine(line);
        outcome.setResult(5);
        return outcome;
    }
    public static Outcome generateOutcomeWith0(){
        Line line = LineTestData.getLineWorth0();
        Outcome outcome = new Outcome();
        outcome.setLine(line);
        outcome.setResult(0);
        return outcome;
    }
    public static Outcome generateOutcomeWith10(){
        Line line = LineTestData.getLineWorth10();
        Outcome outcome = new Outcome();
        outcome.setLine(line);
        outcome.setResult(10);
        return outcome;
    }
}
