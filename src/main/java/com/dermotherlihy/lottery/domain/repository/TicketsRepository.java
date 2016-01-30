package com.dermotherlihy.lottery.domain.repository;

import com.dermotherlihy.lottery.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public interface TicketsRepository extends JpaRepository<Ticket, Long> {
}
