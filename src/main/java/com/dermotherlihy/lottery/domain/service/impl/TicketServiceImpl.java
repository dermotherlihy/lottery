package com.dermotherlihy.lottery.domain.service.impl;

import com.dermotherlihy.lottery.domain.factory.LineFactory;
import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Status;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.repository.TicketRepository;
import com.dermotherlihy.lottery.domain.service.TicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dermot.herlihy on 28/01/2016.
 */
@Service
public class TicketServiceImpl implements TicketService{

    @Resource
    private TicketRepository ticketRepository;
    @Resource
    private LineFactory lineFactory;

    @Override
    public Ticket createTicket(int numberOfLines) {
        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i< numberOfLines; i++){
            lines.add(lineFactory.createLine());
        }
        Ticket ticket = new Ticket(lines, Status.NEW);
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

    public void setLineFactory(LineFactory lineFactory) {
        this.lineFactory = lineFactory;
    }

    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}
