package com.dermotherlihy.lottery.domain;

import com.dermotherlihy.lottery.domain.factory.LineFactory;
import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Status;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.repository.TicketRepository;
import com.dermotherlihy.lottery.domain.service.impl.TicketServiceImpl;
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

    @Mock
    private TicketRepository ticketRepositoryMock;

    @Mock
    private LineFactory lineFactoryMock;

    @Mock
    private Line lineOneMock;

    @Mock
    private Line lineTwoMock;

    private TicketServiceImpl testObj;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        testObj = new TicketServiceImpl();
        testObj.setLineFactory(lineFactoryMock);
        testObj.setTicketRepository(ticketRepositoryMock);
    }

    @Test
    public void testCreateTicketGeneratesCorrectNumberOfLines(){

        Mockito.when(lineFactoryMock.createLine()).thenReturn(lineOneMock,lineTwoMock);

        testObj.createTicket(2);

        ArgumentCaptor<Ticket> argument = ArgumentCaptor.forClass(Ticket.class);

        Mockito.verify(lineFactoryMock,times(2)).createLine();
        Mockito.verify(ticketRepositoryMock).save(argument.capture());
        Assert.assertThat(argument.getValue().getStatus(), is(Status.NEW));
        Assert.assertThat(argument.getValue().getLines().size(), is(2));
        Assert.assertThat(argument.getValue().getLines(), containsInAnyOrder(lineOneMock, lineTwoMock));

    }



}
