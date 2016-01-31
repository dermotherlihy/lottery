package com.dermotherlihy.lottery.domain.factory;

import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class LineFactoryUTest {

    @Mock
    private Random randomMock;

    @Mock
    private Ticket ticketMock;


    private LineFactory testObj;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        testObj = new LineFactory();
        testObj.setRandom(randomMock);
    }

    @Test
    public void testAddLinesWillNotAddDuplicates(){
        Mockito.when(randomMock.nextInt(3)).thenReturn(0,1,2,0,2,1);
        List<Line> existingLines = Lists.newArrayList(new Line(0, 1, 2));
        List<Line> lines = testObj.addLines(existingLines, 1);
        Line addedLine = lines.get(1);
        assertThat(addedLine.getFirstNumber(), is(0));
        assertThat(addedLine.getSecondNumber(), is(2));
        assertThat(addedLine.getThirdNumber(), is(1));

    }


    @Test
    public void testCreateUniqueLinesUsesThreeDifferentRandomNumbers(){
        Mockito.when(randomMock.nextInt(3)).thenReturn(0,1,2);

        List<Line> lines = testObj.createUniqueLines(1);

        Mockito.verify(randomMock,times(3)).nextInt(3);

        Line line = lines.get(0);

        assertThat(line.getFirstNumber(), is(0));
        assertThat(line.getSecondNumber(), is(1));
        assertThat(line.getThirdNumber(), is(2));
    }

    @Test
    public void testCreateUniqueLinesWillNotAddDuplicates(){
        Mockito.when(randomMock.nextInt(3)).thenReturn(0,1,2,0,1,2,0,2,1);

        List<Line> lines = testObj.createUniqueLines(2);

        Mockito.verify(randomMock,times(9)).nextInt(3);

        Line lineOne = lines.get(0);

        assertThat(lineOne.getFirstNumber(), is(0));
        assertThat(lineOne.getSecondNumber(), is(1));
        assertThat(lineOne.getThirdNumber(), is(2));

        Line lineTwo = lines.get(1);
        assertThat(lineTwo.getFirstNumber(), is(0));
        assertThat(lineTwo.getSecondNumber(), is(2));
        assertThat(lineTwo.getThirdNumber(), is(1));
    }

}
