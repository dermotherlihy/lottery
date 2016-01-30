package com.dermotherlihy.lottery.domain.service;

import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Ticket;

import java.util.List;

/**
 * Created by dermot.herlihy on 28/01/2016.
 */
public interface TicketService {

    public Ticket createTicket(Ticket ticket);
    public Ticket getTicket(long ticketId);
    public List<Ticket> getTickets(long ticketId);
    public Ticket addTicketLines(List<Line> lines);
    public Ticket playTicket(long ticketId);
}
