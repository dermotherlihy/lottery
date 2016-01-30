package com.dermotherlihy.lottery.domain.service.impl;

import com.dermotherlihy.lottery.domain.model.*;
import com.dermotherlihy.lottery.domain.repository.ChecksRepository;
import com.dermotherlihy.lottery.domain.repository.TicketsRepository;
import com.dermotherlihy.lottery.domain.service.CheckService;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
public class CheckServiceImpl implements CheckService{

    @Resource
    private TicketsRepository ticketsRepository;

    @Resource
    private ChecksRepository checksRepository;

    @Override
    @Transactional
    public Check createCheck(long ticketId) {

        Ticket ticket = ticketsRepository.findOne(ticketId);
        List<Outcome> outcomeList = new ArrayList<Outcome>();

        for(Line line : ticket.getLines()){
            Outcome outcome = new Outcome(line);
            outcomeList.add(outcome);
        }
        Check check = new Check(ticketId, outcomeList);
        ticket.setStatus(Status.USED);
        ticketsRepository.save(ticket);
        checksRepository.save(check);
        return check;

    }

    public void setTicketsRepository(TicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    public void setChecksRepository(ChecksRepository checksRepository) {
        this.checksRepository = checksRepository;
    }
}
