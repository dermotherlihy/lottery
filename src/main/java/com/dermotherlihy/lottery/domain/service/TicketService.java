package com.dermotherlihy.lottery.domain.service;

import com.dermotherlihy.lottery.domain.model.Ticket;
import com.google.common.base.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by dermot.herlihy on 28/01/2016.
 */
public interface TicketService {

     Ticket createTicket(int numberOfLines);
     Optional<Ticket> getTicket(long ticketId);
     Page<Ticket> getTickets(Pageable pageRequest);
     Ticket addLines(long ticketId, int numberOfLines);


}
