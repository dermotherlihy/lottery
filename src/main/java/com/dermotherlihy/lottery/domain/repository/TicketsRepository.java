package com.dermotherlihy.lottery.domain.repository;

import com.dermotherlihy.lottery.domain.model.Ticket;
import com.google.common.base.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public interface TicketsRepository  {
    Optional<Ticket> findTicket(long ticketId);
    Ticket updateTicket(Ticket ticket);
    Ticket createTicket(Ticket ticket);
    Page<Ticket> findTickets(Pageable pageRequest);
}
