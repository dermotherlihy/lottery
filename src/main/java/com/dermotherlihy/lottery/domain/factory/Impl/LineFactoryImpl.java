package com.dermotherlihy.lottery.domain.factory.impl;

import com.dermotherlihy.lottery.domain.model.Line;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
@Component
public class LineFactoryImpl implements com.dermotherlihy.lottery.domain.factory.LineFactory {

    private static final int MAX_LINES = 27;
    private Random random = new Random();

    public LineFactoryImpl(){
    }

    protected void setRandom(Random random) {
        this.random = random;
    }

    @Override
    public List<Line> addLines(List<Line> existingLines, int numberOfLines) {
        for(int i = 0 ; i< numberOfLines; i++){
            addLine(existingLines);
        }
        return existingLines;
    }

    @Override
    public List<Line> createUniqueLines(int numberOfLines) {
        List<Line> lines = new ArrayList<Line>();
        for(int i =0; i< numberOfLines; i++){
            addLine(lines);
        }
        return lines;
    }

    private void addLine(List<Line> existingLines) {
        Line line = createLine();
        if(existingLines.contains(line)){
            addLine(existingLines);// keep going until you get a unique line
        }
        else
        {
            existingLines.add(line);
        }
    }

    private Line createLine() {
        int randomNumber1 = random.nextInt(3);
        int randomNumber2 = random.nextInt(3);
        int randomNumber3 = random.nextInt(3);
        return new Line(randomNumber1,randomNumber2,randomNumber3);
    }


}
