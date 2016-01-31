package com.dermotherlihy.lottery.infrastructure.persistance.dao;

import com.dermotherlihy.lottery.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public interface TicketsDAO extends JpaRepository<Ticket, Long> {
}
