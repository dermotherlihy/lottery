package com.dermotherlihy.lottery.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dermot.herlihy on 28/01/2016.
 */
public class NumberUTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNumberThrowsIllegalArgumentWhenNumberIsGreaterThan3(){
        new com.dermotherlihy.lottery.domain.model.Number(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNumberThrowsIllegalArgumentWhenNumberIsLessThan0(){
        new Number(-1);
    }

    @Test
    public void testNumberZeroSuccessfullyCreated(){
        Number zero = new Number(0);
        Assert.assertTrue(0 == zero.getValue());
    }

    @Test
    public void testNumberOneSuccessfullyCreated(){
        Number zero = new Number(1);
        Assert.assertTrue(1 == zero.getValue());
    }

    @Test
    public void testNumberTwoSuccessfullyCreated(){
        Number zero = new Number(2);
        Assert.assertTrue(2 == zero.getValue());
    }


}
