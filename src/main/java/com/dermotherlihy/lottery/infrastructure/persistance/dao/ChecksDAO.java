package com.dermotherlihy.lottery.infrastructure.persistance.dao;

import com.dermotherlihy.lottery.domain.model.Check;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public interface ChecksDAO extends CrudRepository<Check,Long>{

    Check findByTicketId(long ticketId);

}
