package com.dermotherlihy.lottery.domain.service.impl;

import com.dermotherlihy.lottery.domain.model.Check;
import com.dermotherlihy.lottery.domain.repository.ChecksRepository;
import com.dermotherlihy.lottery.domain.repository.TicketRepository;
import com.dermotherlihy.lottery.domain.service.CheckService;

import javax.annotation.Resource;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
public class CheckServiceImpl implements CheckService{

    @Resource
    private TicketRepository ticketRepository;

    @Resource
    private ChecksRepository checksRepository;

    @Override
    public Check createTicketCheck(long ticketId) {
        return null;
    }
}
