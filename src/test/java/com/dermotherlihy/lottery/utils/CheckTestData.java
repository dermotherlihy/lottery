package com.dermotherlihy.lottery.utils;

import com.dermotherlihy.lottery.domain.model.Check;
import com.dermotherlihy.lottery.domain.model.Outcome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public class CheckTestData {

    public static Check generateRandomCheck(long ticketId) {
        List<Outcome> outcomes = new ArrayList<Outcome>();
        outcomes.add(new Outcome(LineTestData.getLineWorth0()));
        outcomes.add(new Outcome(LineTestData.getLineWorth5()));
        outcomes.add(new Outcome(LineTestData.getLineWorth10()));
        return new Check(ticketId,outcomes);
    }
}
