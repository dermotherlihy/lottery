package com.dermotherlihy.lottery.domain.service;

import com.dermotherlihy.lottery.domain.model.*;
import com.dermotherlihy.lottery.domain.repository.ChecksRepository;
import com.dermotherlihy.lottery.domain.repository.TicketsRepository;
import com.dermotherlihy.lottery.domain.service.impl.CheckServiceImpl;
import com.dermotherlihy.lottery.utils.LineTestData;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class ChecksServiceImplUTest {

    private static final long TICKET_ID = 1;

    @Mock
    private TicketsRepository ticketsRepositoryMock;

    @Mock
    private ChecksRepository checksRepositoryMock;

    @Mock
    private Ticket ticketMock;

    private CheckServiceImpl testObj;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        testObj = new CheckServiceImpl();
        testObj.setChecksRepository(checksRepositoryMock);
        testObj.setTicketsRepository(ticketsRepositoryMock);
    }

    @Test
    public void testCreateCheckUpdatesTicketToUsed() {
        Line line = new Line(0, 1, 2);

        Mockito.when(ticketsRepositoryMock.findTicket(TICKET_ID)).thenReturn(Optional.of(ticketMock));
        Mockito.when(ticketMock.getLines()).thenReturn(Lists.newArrayList(line));

        testObj.createCheck(TICKET_ID);

        Mockito.verify(ticketMock).setStatus(Status.USED);
        Mockito.verify(ticketsRepositoryMock).updateTicket(ticketMock);

    }

    @Test
    public void testCreateCheckGeneratesOutcomes(){
        Line line = new Line(0,1,2);
        ArgumentCaptor<Check> checkCapture = ArgumentCaptor.forClass(Check.class);

        Mockito.when(ticketsRepositoryMock.findTicket(TICKET_ID)).thenReturn(Optional.of(ticketMock));
        Mockito.when(ticketMock.getLines()).thenReturn(Lists.newArrayList(line));

        testObj.createCheck(TICKET_ID);

        Mockito.verify(checksRepositoryMock).createCheck(checkCapture.capture());
        Assert.assertThat(checkCapture.getValue().getTicketId(), is(TICKET_ID));
        Assert.assertThat(checkCapture.getValue().getOutcomes().iterator().next().getResult(), is(notNullValue()));
    }

    @Test
    public void testCreateCheckSortsOutcomes(){
        Line lineWith0 = LineTestData.getLineWorth0();
        Line lineWith10 = LineTestData.getLineWorth10();
        Line lineWith5 = LineTestData.getLineWorth5();
        List<Line> lines = Lists.newArrayList(lineWith0,lineWith10, lineWith5);

        ArgumentCaptor<Check> checkCapture = ArgumentCaptor.forClass(Check.class);
        Mockito.when(ticketsRepositoryMock.findTicket(TICKET_ID)).thenReturn(Optional.of(ticketMock));
        Mockito.when(ticketMock.getLines()).thenReturn(lines);

        testObj.createCheck(TICKET_ID);

        Mockito.verify(checksRepositoryMock).createCheck(checkCapture.capture());
        Assert.assertThat(checkCapture.getValue().getTicketId(), is(TICKET_ID));
        List<Outcome> outcomes  = checkCapture.getValue().getOutcomes();
        Iterator<Outcome> iterator = outcomes.iterator();
        Assert.assertThat(iterator.next().getLine(), is(lineWith10));
        Assert.assertThat(iterator.next().getLine(), is(lineWith5));
        Assert.assertThat(iterator.next().getLine(), is(lineWith0));
    }
}
