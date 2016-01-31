package com.dermotherlihy.lottery.domain.service.impl;

import com.dermotherlihy.lottery.domain.exception.NotFoundException;
import com.dermotherlihy.lottery.domain.factory.OutcomeFactory;
import com.dermotherlihy.lottery.domain.model.*;
import com.dermotherlihy.lottery.domain.repository.ChecksRepository;
import com.dermotherlihy.lottery.domain.repository.TicketsRepository;
import com.dermotherlihy.lottery.domain.service.CheckService;
import com.google.common.base.Optional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
@Service
public class CheckServiceImpl implements CheckService{

    @Resource
    private TicketsRepository ticketsRepository;

    @Resource
    private ChecksRepository checksRepository;

    @Resource
    private OutcomeFactory outcomeFactory;



    @Override
    public Check createCheck(long ticketId) {
        Optional<Ticket> optionalTicket = ticketsRepository.findTicket(ticketId);
        if(optionalTicket.isPresent()){
            Ticket ticket = optionalTicket.get();
            List<Outcome> outcomeList = createOutcomes(ticket);
            Collections.sort(outcomeList);
            Check check = new Check(ticketId, outcomeList);
            ticket.setStatus(Status.USED);
            ticketsRepository.updateTicket(ticket);
            checksRepository.createCheck(check);
            return check;
        }else{
            throw new NotFoundException("Please specify a valid ticket id");
        }
    }

    private List<Outcome> createOutcomes(Ticket ticket) {
        List<Outcome> outcomeList = new ArrayList<Outcome>();
        for(Line line : ticket.getLines()){
            Outcome outcome = outcomeFactory.createOutcome(line);
            outcomeList.add(outcome);
        }
        return outcomeList;
    }

    @Override
    public Optional<Check> getCheck(long id) {
        return checksRepository.findCheck(id);
    }

    public void setTicketsRepository(TicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    public void setChecksRepository(ChecksRepository checksRepository) {
        this.checksRepository = checksRepository;
    }

    public void setOutcomeFactory(OutcomeFactory outcomeFactory) {
        this.outcomeFactory = outcomeFactory;
    }
}
