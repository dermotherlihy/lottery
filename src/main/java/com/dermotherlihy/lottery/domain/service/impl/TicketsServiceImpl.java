package com.dermotherlihy.lottery.domain.service.impl;

import com.dermotherlihy.lottery.domain.exception.MaxLinesExceededException;
import com.dermotherlihy.lottery.domain.exception.NotFoundException;
import com.dermotherlihy.lottery.domain.exception.TicketExpiredException;
import com.dermotherlihy.lottery.domain.factory.LineFactory;
import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Status;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.repository.TicketsRepository;
import com.dermotherlihy.lottery.domain.service.TicketsService;
import com.google.common.base.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dermot.herlihy on 28/01/2016.
 */
@Service
public class TicketsServiceImpl implements TicketsService {

    private static final int MAX_LINES = 27;
    @Resource
    private TicketsRepository ticketsRepository;
    @Resource
    private LineFactory lineFactory;


    @Override
    public Ticket createTicket(int numberOfLines) {
       if(numberOfLines > MAX_LINES){
           throw new MaxLinesExceededException("Max Lines Allowed is 27");
       }else{
           List<Line> lines = lineFactory.createUniqueLines(numberOfLines);
           Ticket ticket = new Ticket(lines, Status.NEW);
           return ticketsRepository.createTicket(ticket);
       }
    }


    @Override
    public Optional<Ticket> getTicket(long ticketId) {
        return ticketsRepository.findTicket(ticketId);
    }

    @Override
    public Page<Ticket> getTickets(Pageable pageRequest) {
        return ticketsRepository.findTickets(pageRequest);
    }

    @Override
    public Ticket addLines(long ticketId, int numberOfLines) {
        Optional<Ticket> optionalTicket = ticketsRepository.findTicket(ticketId);
        if(optionalTicket.isPresent()){
            Ticket ticket = optionalTicket.get();
            if(Status.NEW == ticket.getStatus()){
                if(ticket.getLines().size() + numberOfLines > MAX_LINES){
                    throw new MaxLinesExceededException("Max Lines Allowed is 27");
                }else{
                    List<Line> lines = lineFactory.addLines(ticket.getLines(), numberOfLines);
                    ticket.setLines(lines);
                    ticket= ticketsRepository.updateTicket(ticket);
                }
            }else{
                throw new TicketExpiredException("New Ticket Required");
            }
            return ticket;
        }else{
            throw new NotFoundException("Please specify a valid ticket id");
        }

    }

    public void setLineFactory(LineFactory lineFactory) {
        this.lineFactory = lineFactory;
    }

    public void setTicketsRepository(TicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }
}
