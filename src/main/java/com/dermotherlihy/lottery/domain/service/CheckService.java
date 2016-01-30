package com.dermotherlihy.lottery.domain.service;

import com.dermotherlihy.lottery.domain.model.Check;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
public interface CheckService {

    Check createTicketCheck(long ticketId);
}
