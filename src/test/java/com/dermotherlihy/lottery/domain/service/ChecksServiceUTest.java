package com.dermotherlihy.lottery.domain.service;

import com.dermotherlihy.lottery.domain.model.Check;
import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Status;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.repository.ChecksRepository;
import com.dermotherlihy.lottery.domain.repository.TicketsRepository;
import com.dermotherlihy.lottery.domain.service.impl.CheckServiceImpl;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class ChecksServiceUTest {

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
        ArgumentCaptor<Check> checkCapture = ArgumentCaptor.forClass(Check.class);

        Mockito.when(ticketsRepositoryMock.findOne(TICKET_ID)).thenReturn(ticketMock);
        Mockito.when(ticketMock.getLines()).thenReturn(Lists.newArrayList(line));

        testObj.createCheck(TICKET_ID);

        Mockito.verify(ticketMock).setStatus(Status.USED);
        Mockito.verify(ticketsRepositoryMock).save(ticketMock);

    }

    @Test
    public void testCreateCheckGeneratesOutcomes(){
        Line line = new Line(0,1,2);
        ArgumentCaptor<Check> checkCapture = ArgumentCaptor.forClass(Check.class);

        Mockito.when(ticketsRepositoryMock.findOne(TICKET_ID)).thenReturn(ticketMock);
        Mockito.when(ticketMock.getLines()).thenReturn(Lists.newArrayList(line));

        testObj.createCheck(TICKET_ID);

        Mockito.verify(checksRepositoryMock).save(checkCapture.capture());
        Assert.assertThat(checkCapture.getValue().getTicketId(), is(TICKET_ID));
        Assert.assertThat(checkCapture.getValue().getOutcomes().get(0).getResult(), is(notNullValue()));
    }
}
