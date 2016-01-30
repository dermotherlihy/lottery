package com.dermotherlihy.lottery.domain.service;

import com.dermotherlihy.lottery.domain.exception.TicketExpiredException;
import com.dermotherlihy.lottery.domain.factory.LineFactory;
import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Status;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.repository.TicketsRepository;
import com.dermotherlihy.lottery.domain.service.impl.TicketServiceImpl;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class TicketServiceUTest {

    private static final long TICKET_ID = 1;

    @Mock
    private TicketsRepository ticketsRepositoryMock;

    @Mock
    private LineFactory lineFactoryMock;

    @Mock
    private Ticket ticketMock;

    @Mock
    private Line lineOneMock;

    @Mock
    private Line lineTwoMock;

    @Mock
    private Line lineThreeMock;

    private TicketServiceImpl testObj;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        testObj = new TicketServiceImpl();
        testObj.setLineFactory(lineFactoryMock);
        testObj.setTicketsRepository(ticketsRepositoryMock);
    }

    @Test
    public void testCreateTicketGeneratesCorrectNumberOfLines(){

        Mockito.when(lineFactoryMock.createLine()).thenReturn(lineOneMock,lineTwoMock);

        testObj.createTicket(2);

        ArgumentCaptor<Ticket> argument = ArgumentCaptor.forClass(Ticket.class);

        Mockito.verify(lineFactoryMock,times(2)).createLine();
        Mockito.verify(ticketsRepositoryMock).save(argument.capture());
        Assert.assertThat(argument.getValue().getStatus(), is(Status.NEW));
        Assert.assertThat(argument.getValue().getLines().size(), is(2));
        Assert.assertThat(argument.getValue().getLines(), containsInAnyOrder(lineOneMock, lineTwoMock));

    }

    @Test
    public void testGetTicketById(){

        Mockito.when(ticketsRepositoryMock.findOne(TICKET_ID)).thenReturn(ticketMock);

        Ticket returnedTicket = testObj.getTicket(TICKET_ID);

        Assert.assertThat(returnedTicket, is(ticketMock));

    }


    @Test
    public void testAddLinesSuccess() {
        Mockito.when(ticketsRepositoryMock.findOne(TICKET_ID)).thenReturn(ticketMock);
        Mockito.when(ticketMock.getLines()).thenReturn(Lists.newArrayList(lineOneMock));
        Mockito.when(ticketMock.getStatus()).thenReturn(Status.NEW);
        ArgumentCaptor<Ticket> argument = ArgumentCaptor.forClass(Ticket.class);

        testObj.addLines(TICKET_ID, 2);

        Mockito.verify(lineFactoryMock, times(2)).createLine();
        Mockito.verify(ticketsRepositoryMock).save(argument.capture());
        Assert.assertThat(argument.getValue().getStatus(), is(Status.NEW));
        Assert.assertThat(argument.getValue().getLines().size(), is(3));
    }

    @Test(expected= TicketExpiredException.class)
    public void testAddLinesThrowsExceptionWhenTicketHasExpired(){
        Mockito.when(ticketsRepositoryMock.findOne(TICKET_ID)).thenReturn(ticketMock);
        Mockito.when(ticketMock.getStatus()).thenReturn(Status.USED);
        testObj.addLines(TICKET_ID,2);
    }

}
