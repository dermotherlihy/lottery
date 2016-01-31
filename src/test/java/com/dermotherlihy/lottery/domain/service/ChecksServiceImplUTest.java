package com.dermotherlihy.lottery.domain.service;

import com.dermotherlihy.lottery.domain.factory.OutcomeFactory;
import com.dermotherlihy.lottery.domain.model.*;
import com.dermotherlihy.lottery.domain.repository.ChecksRepository;
import com.dermotherlihy.lottery.domain.repository.TicketsRepository;
import com.dermotherlihy.lottery.domain.service.impl.ChecksServiceImpl;
import com.dermotherlihy.lottery.utils.OutcomeTestData;
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
import static org.mockito.Mockito.never;

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
    private OutcomeFactory outcomeFactoryMock;

    @Mock
    private Ticket ticketMock;

    @Mock
    private Check checkMock;

    @Mock
    private Outcome outcomeMock;

    private ChecksServiceImpl testObj;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        testObj = new ChecksServiceImpl();
        testObj.setChecksRepository(checksRepositoryMock);
        testObj.setTicketsRepository(ticketsRepositoryMock);
        testObj.setOutcomeFactory(outcomeFactoryMock);
    }

    @Test
    public void testCreateReturnsPreviousCheckWhenUsed() {
        Mockito.when(ticketsRepositoryMock.findTicket(TICKET_ID)).thenReturn(Optional.of(ticketMock));
        Mockito.when(ticketMock.getStatus()).thenReturn(Status.USED);
        Mockito.when(ticketMock.getId()).thenReturn(TICKET_ID);

        Mockito.when(checksRepositoryMock.findCheckByTicketId(TICKET_ID)).thenReturn(checkMock);

        Check check = testObj.createCheck(TICKET_ID);

        Mockito.verify(ticketMock,never()).setStatus(Status.USED);
        Mockito.verify(ticketsRepositoryMock,never()).updateTicket(ticketMock);
        Assert.assertThat(check, is(checkMock));

    }


    @Test
    public void testCreateCheckUpdatesTicketToUsed() {
        Line line = new Line(0, 1, 2);

        Mockito.when(ticketsRepositoryMock.findTicket(TICKET_ID)).thenReturn(Optional.of(ticketMock));
        Mockito.when(ticketMock.getLines()).thenReturn(Lists.newArrayList(line));
        Mockito.when(ticketMock.getStatus()).thenReturn(Status.NEW);
        Mockito.when(outcomeFactoryMock.createOutcome(line)).thenReturn(outcomeMock);


        testObj.createCheck(TICKET_ID);

        Mockito.verify(ticketMock).setStatus(Status.USED);
        Mockito.verify(ticketsRepositoryMock).updateTicket(ticketMock);

    }

    @Test
    public void testCreateCheckGeneratesOutcomes(){
        Line line = new Line(0,1,2);
        ArgumentCaptor<Check> checkCapture = ArgumentCaptor.forClass(Check.class);
        Mockito.when(ticketMock.getStatus()).thenReturn(Status.NEW);
        Mockito.when(ticketsRepositoryMock.findTicket(TICKET_ID)).thenReturn(Optional.of(ticketMock));
        Mockito.when(ticketMock.getLines()).thenReturn(Lists.newArrayList(line));
        Mockito.when(outcomeFactoryMock.createOutcome(line)).thenReturn(outcomeMock);

        testObj.createCheck(TICKET_ID);

        Mockito.verify(checksRepositoryMock).createCheck(checkCapture.capture());
        Assert.assertThat(checkCapture.getValue().getTicketId(), is(TICKET_ID));
        Assert.assertThat(checkCapture.getValue().getOutcomes().iterator().next().getResult(), is(notNullValue()));
    }

    @Test
    public void testCreateCheckSortsOutcomes(){
        Outcome outcome0 = OutcomeTestData.generateOutcomeWith0();
        Outcome outcome10 = OutcomeTestData.generateOutcomeWith10();
        Outcome outcome5 = OutcomeTestData.generateOutcomeWith10();

        List<Line> lines = Lists.newArrayList(outcome0.getLine(),outcome10.getLine(), outcome5.getLine());

        ArgumentCaptor<Check> checkCapture = ArgumentCaptor.forClass(Check.class);
        Mockito.when(ticketsRepositoryMock.findTicket(TICKET_ID)).thenReturn(Optional.of(ticketMock));
        Mockito.when(ticketMock.getStatus()).thenReturn(Status.NEW);
        Mockito.when(ticketMock.getLines()).thenReturn(lines);
        Mockito.when(outcomeFactoryMock.createOutcome(outcome0.getLine())).thenReturn(outcome0);
        Mockito.when(outcomeFactoryMock.createOutcome(outcome10.getLine())).thenReturn(outcome10);
        Mockito.when(outcomeFactoryMock.createOutcome(outcome5.getLine())).thenReturn(outcome5);

        testObj.createCheck(TICKET_ID);

        Mockito.verify(checksRepositoryMock).createCheck(checkCapture.capture());
        Assert.assertThat(checkCapture.getValue().getTicketId(), is(TICKET_ID));
        List<Outcome> outcomes  = checkCapture.getValue().getOutcomes();
        Iterator<Outcome> iterator = outcomes.iterator();
        Assert.assertThat(iterator.next().getLine(), is(outcome10.getLine()));
        Assert.assertThat(iterator.next().getLine(), is(outcome5.getLine()));
        Assert.assertThat(iterator.next().getLine(), is(outcome0.getLine()));
    }
}
