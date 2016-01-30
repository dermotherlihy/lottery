package com.dermotherlihy.lottery.domain.factory;

import com.dermotherlihy.lottery.domain.model.Line;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class LineFactoryUTest {

    @Mock
    private Random randomMock;

    private LineFactory testObj;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        testObj = new LineFactory();
        testObj.setRandom(randomMock);
    }
    @Test
    public void testLineCreationUsesThreeDifferentRandomNumbers(){
        Mockito.when(randomMock.nextInt(3)).thenReturn(0,1,2);

        Line line = testObj.createLine();

        Mockito.verify(randomMock,times(3)).nextInt(3);

        Assert.assertThat(line.getFirstNumber(), is(0));
        Assert.assertThat(line.getSecondNumber(), is(1));
        Assert.assertThat(line.getThirdNumber(), is(2));
    }

}
