package com.dermotherlihy.lottery.domain.factory;

import com.dermotherlihy.lottery.domain.model.Line;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
@Component
public class LineFactory {

    private Random random = new Random();

    public LineFactory(){
    }

    protected void setRandom(Random random) {
        this.random = random;
    }

    public Line createLine() {
        int randomNumber1 = random.nextInt(3);
        int randomNumber2 = random.nextInt(3);
        int randomNumber3 = random.nextInt(3);
        return new Line(randomNumber1,randomNumber2,randomNumber3);
    }
}
