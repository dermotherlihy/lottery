package com.dermotherlihy.lottery.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;


/**
 * Test 27 combinations
 * May rename tests
 */
public class OutcomeUTest {

    @Test
    public void testResultIs5WhenNumbersAreTheSameAndZeros(){
        Line line = new Line(0,0,0);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(5));
    }

    @Test
    public void testResultIs0WhenNumbersZeroZeroOne(){
        Line line = new Line(0,0,1);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreZeroZeroTwo(){
        Line line = new Line(0,0,2);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIsZeroWhenNumbersZeroOneZero(){
        Line line = new Line(0,1,0);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreZeroOneOne(){
        Line line = new Line(0,1,1);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIs1WhenForNumbersZeroOneTwo(){
        Line line = new Line(0,1,2);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreZeroTwoZero(){
        Line line = new Line(0,2,0);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIs1WhenForNumbersZeroTwoOne(){
        Line line = new Line(0,2,1);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs1WhenForNumbersZeroTwoTwo(){
        Line line = new Line(0,2,2);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs1WhenForNumbersOneZeroZero(){
        Line line = new Line(1,0,0);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreOneZeroOne(){
        Line line = new Line(1,0,1);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIs1WhenForNumbersOneZeroTwo(){
        Line line = new Line(1,0,2);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreOneOneZero(){
        Line line = new Line(1,1,0);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIs5WhenAllNumbersAreTheSameAndOnes(){
        Line line = new Line(1,1,1);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(5));
    }

    @Test
    public void testResultIs0WhenNumbersOneOneTwo(){
        Line line = new Line(1,1,2);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs1WhenForNumbersOneTwoZero(){
        Line line = new Line(1,2,0);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs0WhenNumbersOneTwoOne(){
        Line line = new Line(1,2,1);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs1WhenForNumbersOneTwoTwo(){
        Line line = new Line(1,2,2);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs10WhenSumOfLineIsTwoAnNumbersAreTwoZeroZero(){
        Line line = new Line(2,0,0);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(10));
    }

    @Test
    public void testResultIs1WhenForNumbersTwoZeroOne(){
        Line line = new Line(2,0,1);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs0WhenForNumbersTwoZeroTwo(){
        Line line = new Line(2,0,2);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs1WhenForNumbersTwoOneZero(){
        Line line = new Line(2,1,0);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs1WhenForNumbersTwoOneOne(){
        Line line = new Line(2,1,1);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(1));
    }

    @Test
    public void testResultIs0WhenForNumbersTwoOneTwo(){
        Line line = new Line(2,1,2);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs0WhenForNumbersTwoTwoZero(){
        Line line = new Line(2,2,0);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }


    @Test
    public void testResultIs0WhenForNumbersTwoTwoOne(){
        Line line = new Line(2,2,1);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(0));
    }

    @Test
    public void testResultIs5WhenAllNumbersAreTheSameAndTwos(){
        Line line = new Line(2,2,2);
        Outcome outcome = new Outcome(line);
        Assert.assertThat(outcome.getResult(), is(5));
    }
}
