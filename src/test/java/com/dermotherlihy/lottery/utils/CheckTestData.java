package com.dermotherlihy.lottery.utils;

import com.dermotherlihy.lottery.domain.model.Check;
import com.dermotherlihy.lottery.domain.model.Outcome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public class CheckTestData {

    public static Check generateCheck(long ticketId) {
        List<Outcome> outcomes = new ArrayList<Outcome>();
        outcomes.add(OutcomeTestData.generateOutcomeWith0());
        outcomes.add(OutcomeTestData.generateOutcomeWith10());
        outcomes.add(OutcomeTestData.generateOutcomeWith5());
        return new Check(ticketId,outcomes);
    }
}
