package com.dermotherlihy.lottery.rest.v1.mapper;

import com.dermotherlihy.lottery.domain.model.Check;
import com.dermotherlihy.lottery.domain.model.Outcome;
import com.dermotherlihy.lottery.rest.v1.resource.response.CheckResponse;
import com.dermotherlihy.lottery.rest.v1.resource.response.OutcomeResponse;
import com.dermotherlihy.lottery.utils.CheckTestData;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public class ChecksResponseMapperUTest {

    private static final int TICKET_ID = 5;

    @Test
    public void testMapCheckResponse(){
        Check check = CheckTestData.generateCheck(TICKET_ID);
        CheckResponse checkResponse = ChecksResponseMapper.mapCheckResponse(check);
        assertThat(checkResponse.getOutcomes().size(), is(check.getOutcomes().size()));
        assertThat(checkResponse.getCheckId(), is(check.getId()));
        assertThat(checkResponse.getTicketId(), is(check.getTicketId()));
        Iterator<Outcome> outcomeIterator = check.getOutcomes().iterator();
        for(OutcomeResponse outcomeResponse : checkResponse.getOutcomes()){
            Outcome outcome = outcomeIterator.next();
            assertThat(outcomeResponse.getResult(), is(outcome.getResult()));
            assertThat(outcomeResponse.getLineResponse().getFirstNumber(), is(outcome.getLine().getFirstNumber()));
            assertThat(outcomeResponse.getLineResponse().getSecondNumber(), is(outcome.getLine().getSecondNumber()));
            assertThat(outcomeResponse.getLineResponse().getThirdNumber(), is(outcome.getLine().getThirdNumber()));
        }
    }

}
