package com.dermotherlihy.lottery.domain.service.impl;

import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.repository.TicketRepository;
import com.dermotherlihy.lottery.domain.service.TicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dermot.herlihy on 28/01/2016.
 */
@Service
public class TicketServiceImpl implements TicketService{

    @Resource
    private TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicket(long ticketId) {
        return ticketRepository.findOne(ticketId);
    }

    @Override
    public List<Ticket> getTickets(long ticketId) {
        return null;
    }

    @Override
    public Ticket addTicketLines(List<Line> lines) {
        return null;
    }

    @Override
    public Ticket playTicket(long ticketId) {
        return null;
    }
}
