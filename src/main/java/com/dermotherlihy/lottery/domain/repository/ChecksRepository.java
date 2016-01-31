package com.dermotherlihy.lottery.domain.repository;

import com.dermotherlihy.lottery.domain.model.Check;
import com.google.common.base.Optional;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public interface ChecksRepository  {
    Check createCheck(Check check);

    Optional<Check> findCheck(long id);

    Check findCheckByTicketId(long ticketId);
}
