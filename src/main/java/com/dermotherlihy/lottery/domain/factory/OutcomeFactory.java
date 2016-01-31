package com.dermotherlihy.lottery.domain.factory;

import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Outcome;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
@Component
public class OutcomeFactory {

    public Outcome createOutcome(Line line){
        Assert.notNull(line, "Line should never be null");
        Outcome outcome = new Outcome();
        outcome.setLine(line);
        outcome.setResult(generateResult(line));
        return outcome;
    }

    private int generateResult(Line line){
        if(line.getFirstNumber() + line.getSecondNumber() + line.getThirdNumber() == 2){
            return 10;
        }
        else if((line.getFirstNumber() == line.getSecondNumber()) && (line.getSecondNumber() == line.getThirdNumber())){
            return 5;
        }
        else if(line.getFirstNumber() != line.getSecondNumber() && line.getFirstNumber() != line.getThirdNumber()){
            return 1;
        }else{
            return 0;
        }
    }
}
