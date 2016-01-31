package com.dermotherlihy.lottery.rest.v1.mapper;

import com.dermotherlihy.lottery.utils.TicketTestData;
import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.rest.v1.mapper.TicketResponseMapper;
import com.dermotherlihy.lottery.rest.v1.resource.response.LineResponse;
import com.dermotherlihy.lottery.rest.v1.resource.response.TicketResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class TicketResponseMapperUTest {

    private static final String NEW = "NEW";
    private TicketResponseMapper testObj = new TicketResponseMapper();

    @Test
    public void testTicketResponseCreation(){
        Ticket ticket = TicketTestData.generateRandomNewTicketWithId();
        TicketResponse response = testObj.mapTicketResponse(ticket);
        Assert.assertThat(response.getTicketId(), is(ticket.getId()));
        Assert.assertThat(response.getStatus(), is(NEW));
        assertLines(response.getLines(),ticket.getLines());
    }

    private void assertLines(List<LineResponse> responseLines, List<Line> ticketLines) {
        Assert.assertThat(responseLines.size(), is(ticketLines.size()));
        for(int i = 0; i < responseLines.size(); i++){
            assertLine(responseLines.get(0), ticketLines.get(0));
        }
    }

    private void assertLine(LineResponse responseLine, Line ticketLine) {
        Assert.assertThat(responseLine.getFirstNumber(), is(ticketLine.getFirstNumber()));
        Assert.assertThat(responseLine.getSecondNumber(), is(ticketLine.getSecondNumber()));
        Assert.assertThat(responseLine.getThirdNumber(), is(ticketLine.getThirdNumber()));
    }

}
