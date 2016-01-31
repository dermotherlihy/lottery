package com.dermotherlihy.lottery.domain.service;

import com.dermotherlihy.lottery.domain.exception.MaxLinesExceededException;
import com.dermotherlihy.lottery.domain.exception.TicketExpiredException;
import com.dermotherlihy.lottery.domain.factory.LineFactory;
import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Status;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.repository.TicketsRepository;
import com.dermotherlihy.lottery.domain.service.impl.TicketServiceImpl;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class TicketServiceImplUTest {

    private static final long TICKET_ID = 1;
    private static final int TWO_LINES = 2;
    private static final int TWENTY_EIGHT = 28;

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

    @Mock
    private List<Line> linesMock;

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

        Mockito.when(lineFactoryMock.createUniqueLines(TWO_LINES)).
                thenReturn(Lists.newArrayList(lineOneMock, lineTwoMock));

        testObj.createTicket(TWO_LINES);

        ArgumentCaptor<Ticket> argument = ArgumentCaptor.forClass(Ticket.class);

        Mockito.verify(ticketsRepositoryMock).createTicket(argument.capture());
        Assert.assertThat(argument.getValue().getStatus(), is(Status.NEW));
        Assert.assertThat(argument.getValue().getLines().size(), is(TWO_LINES));
        Assert.assertThat(argument.getValue().getLines(), containsInAnyOrder(lineOneMock, lineTwoMock));

    }

    @Test(expected= MaxLinesExceededException.class)
    public void testCreateTicketThrowsMaxLinesExceededException(){

        Mockito.when(lineFactoryMock.createUniqueLines(TWENTY_EIGHT)).thenThrow(new MaxLinesExceededException());

        testObj.createTicket(TWENTY_EIGHT);

        fail();

    }

    @Test
    public void testGetTicketById(){

        Mockito.when(ticketsRepositoryMock.findTicket(TICKET_ID)).thenReturn(Optional.of(ticketMock));

        Optional<Ticket> returnedTicket = testObj.getTicket(TICKET_ID);

        Assert.assertThat(returnedTicket.get(), is(ticketMock));

    }


    @Test
    public void testAddLinesSuccess() {
        Mockito.when(ticketsRepositoryMock.findTicket(TICKET_ID)).thenReturn(Optional.of(ticketMock));
        Mockito.when(ticketMock.getLines()).thenReturn(linesMock);
        Mockito.when(ticketMock.getStatus()).thenReturn(Status.NEW);
        Mockito.when(lineFactoryMock.addLines(ticketMock.getLines(), TWO_LINES)).thenReturn(linesMock);

        ArgumentCaptor<Ticket> argument = ArgumentCaptor.forClass(Ticket.class);

        testObj.addLines(TICKET_ID, TWO_LINES);

        Mockito.verify(lineFactoryMock, times(1)).addLines(linesMock,TWO_LINES);
        Mockito.verify(ticketMock).setLines(linesMock);
        Mockito.verify(ticketsRepositoryMock).updateTicket(argument.capture());
        Assert.assertThat(argument.getValue().getStatus(), is(Status.NEW));
    }

    @Test(expected= TicketExpiredException.class)
    public void testAddLinesThrowsExceptionWhenTicketHasExpired(){
        Mockito.when(ticketsRepositoryMock.findTicket(TICKET_ID)).thenReturn(Optional.of(ticketMock));
        Mockito.when(ticketMock.getStatus()).thenReturn(Status.USED);
        testObj.addLines(TICKET_ID,2);
    }

    @Test(expected= MaxLinesExceededException.class)
    public void testAddLinesThrowsMaxLinesExceededException() {
        Mockito.when(ticketsRepositoryMock.findTicket(TICKET_ID)).thenReturn(Optional.of(ticketMock));
        Mockito.when(ticketMock.getStatus()).thenReturn(Status.NEW);
        Mockito.when(ticketMock.getLines()).thenReturn(linesMock);
        Mockito.when(linesMock.size()).thenReturn(26);

        testObj.addLines(TICKET_ID, TWO_LINES);

        fail(); //Test Fails if this line is reached.

    }

}
