package com.dermotherlihy.lottery.domain.factory;

import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Outcome;

/**
 * Created by dermot.herlihy on 01/02/2016.
 */
public interface OutcomeFactory {
    Outcome createOutcome(Line line);
}
