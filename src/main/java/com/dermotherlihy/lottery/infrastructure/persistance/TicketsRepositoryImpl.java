package com.dermotherlihy.lottery.infrastructure.persistance;

import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.repository.TicketsRepository;
import com.dermotherlihy.lottery.infrastructure.persistance.dao.TicketsDAO;
import com.google.common.base.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
@Repository
public class TicketsRepositoryImpl implements TicketsRepository {

    @Resource
    private TicketsDAO ticketsDAO;

    @Override
    public Optional<Ticket> findTicket(long ticketId) {
        return Optional.fromNullable(ticketsDAO.findOne(ticketId));
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketsDAO.save(ticket);
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketsDAO.save(ticket);
    }

    @Override
    public Page<Ticket> findTickets(Pageable pageRequest) {
       return ticketsDAO.findAll(pageRequest);
    }
}
