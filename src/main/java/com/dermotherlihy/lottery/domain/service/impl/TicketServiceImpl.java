package com.dermotherlihy.lottery.domain.service.impl;

import com.dermotherlihy.lottery.domain.exception.TicketExpiredException;
import com.dermotherlihy.lottery.domain.factory.LineFactory;
import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Status;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.repository.TicketsRepository;
import com.dermotherlihy.lottery.domain.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private TicketsRepository ticketsRepository;
    @Resource
    private LineFactory lineFactory;


    @Override
    public Ticket createTicket(int numberOfLines) {
        List<Line> lines = generateLines(numberOfLines);
        Ticket ticket = new Ticket(lines, Status.NEW);
        return ticketsRepository.save(ticket);
    }


    @Override
    public Ticket getTicket(long ticketId) {
        return ticketsRepository.findOne(ticketId);
    }

    @Override
    public Page<Ticket> getTickets(Pageable pageRequest) {
        return ticketsRepository.findAll(pageRequest);
    }

    @Override
    public Ticket addLines(long ticketId, int numberOfLines) {
        Ticket ticket = ticketsRepository.findOne(ticketId);
        if(Status.NEW == ticket.getStatus()){
            List<Line> lines = generateLines(numberOfLines);
            ticket.getLines().addAll(lines);
            ticket=  ticketsRepository.save(ticket);
        }else{
            throw new TicketExpiredException();
        }
        return ticket;
    }

    public void setLineFactory(LineFactory lineFactory) {
        this.lineFactory = lineFactory;
    }

    public void setTicketsRepository(TicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    private List<Line> generateLines(int numberOfLines) {
        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i< numberOfLines; i++){
            lines.add(lineFactory.createLine());
        }
        return lines;
    }

}
