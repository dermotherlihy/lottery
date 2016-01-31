package com.dermotherlihy.lottery.domain.factory;

import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Outcome;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;


/**
 * ToDo : Maybe rename some of tests
 */
public class OutcomeFactoryUTest {

    private OutcomeFactory testObj = new OutcomeFactory();

    @Test
    public void testResultIs5WhenNumbersAreTheSameAndZeros(){
        Line line = new Line(0,0,0);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(5));
    }

    @Test
    public void testResultIs0WhenNumbersZeroZeroOne(){
        Line line = new Line(0,0,1);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreZeroZeroTwo(){
        Line line = new Line(0,0,2);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIsZeroWhenNumbersZeroOneZero(){
        Line line = new Line(0,1,0);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreZeroOneOne(){
        Line line = new Line(0,1,1);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIs1WhenForNumbersZeroOneTwo(){
        Line line = new Line(0,1,2);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreZeroTwoZero(){
        Line line = new Line(0,2,0);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIs1WhenForNumbersZeroTwoOne(){
        Line line = new Line(0,2,1);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs1WhenForNumbersZeroTwoTwo(){
        Line line = new Line(0,2,2);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs1WhenForNumbersOneZeroZero(){
        Line line = new Line(1,0,0);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreOneZeroOne(){
        Line line = new Line(1,0,1);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIs1WhenForNumbersOneZeroTwo(){
        Line line = new Line(1,0,2);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreOneOneZero(){
        Line line = new Line(1,1,0);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIs5WhenAllNumbersAreTheSameAndOnes(){
        Line line = new Line(1,1,1);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(5));
    }

    @Test
    public void testResultIs0WhenNumbersOneOneTwo(){
        Line line = new Line(1,1,2);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs1WhenForNumbersOneTwoZero(){
        Line line = new Line(1,2,0);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs0WhenNumbersOneTwoOne(){
        Line line = new Line(1,2,1);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs1WhenForNumbersOneTwoTwo(){
        Line line = new Line(1,2,2);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreTwoZeroZero(){
        Line line = new Line(2,0,0);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIs1WhenForNumbersTwoZeroOne(){
        Line line = new Line(2,0,1);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs0WhenForNumbersTwoZeroTwo(){
        Line line = new Line(2,0,2);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs1WhenForNumbersTwoOneZero(){
        Line line = new Line(2,1,0);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs1WhenForNumbersTwoOneOne(){
        Line line = new Line(2,1,1);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs0WhenForNumbersTwoOneTwo(){
        Line line = new Line(2,1,2);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs0WhenForNumbersTwoTwoZero(){
        Line line = new Line(2,2,0);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }


    @Test
    public void testResultIs0WhenForNumbersTwoTwoOne(){
        Line line = new Line(2,2,1);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs5WhenAllNumbersAreTheSameAndTwos(){
        Line line = new Line(2,2,2);
        Outcome outcome = testObj.createOutcome(line);
        Assert.assertThat(outcome.getResult(), is(5));
    }
}
